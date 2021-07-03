package com.dev.confapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth = FirebaseAuth.getInstance()

    }

    fun up(view: View){
        val mail = email.text.toString()
        val pass = password.text.toString()
        val name = Nom.text.toString()
        mAuth!!.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val myRef = database.getReference("Users")
                    val uniqueID = FirebaseAuth.getInstance().currentUser!!.uid
                    myRef.child(uniqueID).child("name").setValue(name)
                    myRef.child(uniqueID).child("email").setValue(mail)
                    myRef.child(uniqueID).child("type").setValue("audience")
                    myRef.child(uniqueID).child("uid").setValue(uniqueID)
                    val intent = Intent(applicationContext,test::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "compte créé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
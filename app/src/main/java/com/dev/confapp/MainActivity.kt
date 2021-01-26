package com.dev.confapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun EnterRoom(view : View){
        /*val tkn = token.text.toString()
        val generatedToken : String ? = null
        if (tkn == generatedToken){
            val intent = Intent(applicationContext,ChooseRoom::class.java)
            startActivity(intent)
        }*/
        val intent = Intent(applicationContext,ChooseRoom::class.java)
        startActivity(intent)

    }
}
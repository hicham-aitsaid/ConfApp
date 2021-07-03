package com.dev.confapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun EnterRoom(view : View){

        val token = findViewById<EditText>(R.id.token).toString()

        val intent = Intent(this,ChooseRoom::class.java)
        intent.putExtra("token",token)
        startActivity(intent)
    }
}

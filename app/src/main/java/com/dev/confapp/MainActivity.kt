package com.dev.confapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions


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
       /* val intent = Intent(applicationContext,ChooseRoom::class.java)
        startActivity(intent)*/


        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("testtest")
            .build()
        JitsiMeetActivity.launch(this, options)

    }
}
package com.dev.confapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetFragment
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun EnterRoom(view : View){

        val uuid : UUID = UUID.randomUUID()
        /*println(" UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU" + uuid)*/

        /*val tkn = token.text.toString()
        val generatedToken : String ? = null
        if (tkn == generatedToken){
            val intent = Intent(applicationContext,ChooseRoom::class.java)
            startActivity(intent)
        }*/
       /* val intent = Intent(applicationContext,ChooseRoom::class.java)
        startActivity(intent)*/


        /*val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("testtest")
            .setVideoMuted(false)
            .build()
        JitsiMeetActivity.launch(this, options)*/


        val options1 = JitsiMeetConferenceOptions.Builder()
            .setRoom("testtest")
            .setVideoMuted(true)
            .build()
        JitsiMeetActivity.launch(this, options1)

       /* val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("test2")
            .setAudioMuted(true)
            .build()
        JitsiMeetActivity.launch(this,options)
*/

    }
}
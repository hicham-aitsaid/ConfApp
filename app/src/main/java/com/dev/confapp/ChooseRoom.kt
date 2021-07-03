package com.dev.confapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView
import java.net.URL


class ChooseRoom : AppCompatActivity(),JitsiMeetActivityInterface {

    var extra : String ?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_room)
        extra = intent.getStringExtra("token").toString()


    }

    fun EnterRoomSource(view : View){
        val view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setServerURL(URL("https://meet.jit.si/"))
            .setRoom(extra)
            .setAudioMuted(true)
            .build()
        JitsiMeetActivity.launch(this, options)
    }

    fun EnterRoomENG(view : View){
        val view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setServerURL(URL("https://meet.jit.si/"))
            .setRoom(extra+"ENG")
            .setAudioMuted(true)
            .build()
        JitsiMeetActivity.launch(this, options)
    }

    fun EnterRoomFR(view : View){
        val view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setServerURL(URL("https://meet.jit.si/"))
            .setRoom(extra+"FR")
            .setAudioMuted(true)
            .build()
        JitsiMeetActivity.launch(this, options)
    }

    fun EnterRoomAR(view : View){
        val view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setServerURL(URL("https://meet.jit.si/"))
            .setRoom(extra+"AR")
            .setAudioMuted(true)
            .build()
        JitsiMeetActivity.launch(this, options)
    }

    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        TODO("Not yet implemented")
    }

}
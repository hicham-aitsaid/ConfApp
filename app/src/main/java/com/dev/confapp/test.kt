package com.dev.confapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView
import java.net.MalformedURLException
import java.net.URL

class test : AppCompatActivity(), JitsiMeetActivityInterface {

    var extra: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_room)
        extra = intent.getStringExtra("token")



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

    override fun requestPermissions(
        strings: Array<String>,
        i: Int,
        permissionListener: PermissionListener
    ) {
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {}
}
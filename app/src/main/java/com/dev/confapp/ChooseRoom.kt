package com.dev.confapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
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



    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        TODO("Not yet implemented")
    }

}
package com.dev.confapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.facebook.react.modules.core.PermissionListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView
import java.net.URL


class test : AppCompatActivity(), JitsiMeetActivityInterface {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Users")


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
            .setAudioMuted(false)
            .build()
        JitsiMeetActivity.launch(this, options)

    }

    fun EnterRoomENG(view : View){

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var bool = false
                for(snapshot in dataSnapshot.children){
                    if( snapshot.child("type").getValue().toString() == "interprete" ){
                        bool = true
                    }
                }
                if ( bool ){
                    val view = JitsiMeetView(this@test)
                    val options = JitsiMeetConferenceOptions.Builder()
                        .setServerURL(URL("https://meet.jit.si/"))
                        .setRoom(extra)
                        .setVideoMuted(true)
                        .setAudioMuted(true)
                        .build()
                    JitsiMeetActivity.launch(this@test, options)
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.jit.si/"+extra+"ENG"))
                    startActivity(browserIntent)

                }else{
                    val view = JitsiMeetView(this@test)
                    val options = JitsiMeetConferenceOptions.Builder()
                        .setServerURL(URL("https://meet.jit.si/"))
                        .setRoom(extra+"ENG")
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .build()
                    JitsiMeetActivity.launch(this@test, options)
                }

            }

            override fun onCancelled(error: DatabaseError) {}
        })


    }

    fun EnterRoomFR(view : View){
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var bool = false
                for(snapshot in dataSnapshot.children){
                    if( snapshot.child("type").getValue().toString() == "interprete" ){
                        bool = true
                    }
                }
                if ( bool ){
                    val view = JitsiMeetView(this@test)
                    val options = JitsiMeetConferenceOptions.Builder()
                        .setServerURL(URL("https://meet.jit.si/"))
                        .setRoom(extra)
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .build()
                    JitsiMeetActivity.launch(this@test, options)
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.jit.si/"+extra+"FR"))
                    startActivity(browserIntent)

                }else{
                    val view = JitsiMeetView(this@test)
                    val options = JitsiMeetConferenceOptions.Builder()
                        .setServerURL(URL("https://meet.jit.si/"))
                        .setRoom(extra+"FR")
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .build()
                    JitsiMeetActivity.launch(this@test, options)
                }

            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun EnterRoomAR(view : View){
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var bool = false
                for(snapshot in dataSnapshot.children){
                    if( snapshot.child("type").getValue().toString() == "interprete" ){
                        bool = true
                    }
                }
                if ( bool ){
                    val view = JitsiMeetView(this@test)
                    val options = JitsiMeetConferenceOptions.Builder()
                        .setServerURL(URL("https://meet.jit.si/"))
                        .setRoom(extra)
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .build()
                    JitsiMeetActivity.launch(this@test, options)
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.jit.si/"+extra+"AR"))
                    startActivity(browserIntent)

                }else{
                    val view = JitsiMeetView(this@test)
                    val options = JitsiMeetConferenceOptions.Builder()
                        .setServerURL(URL("https://meet.jit.si/"))
                        .setRoom(extra+"AR")
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .build()
                    JitsiMeetActivity.launch(this@test , options)
                }

            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun requestPermissions(
        strings: Array<String>,
        i: Int,
        permissionListener: PermissionListener
    ) {
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {}
}
package com.dev.confapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.jitsi.meet.sdk.JitsiMeetActivity;


public class SourceRoom extends JitsiMeetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_room);
    }
}
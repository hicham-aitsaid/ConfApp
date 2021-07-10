package com.dev.confapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CreerRenuion extends AppCompatActivity {

    EditText name,token,speaker,inter1,inter2,inter3;
    Button add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef,rootref;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_renuion);

        name=findViewById(R.id.name);
        token=findViewById(R.id.token);
        add=findViewById(R.id.add);
        speaker=findViewById(R.id.speaker);
        inter1=findViewById(R.id.interpreteur1);
        inter2=findViewById(R.id.interpreteur2);
        inter3=findViewById(R.id.interpreteur3);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lenom=name.getText().toString();
                String letoken=token.getText().toString();
                String lespeaker= speaker.getText().toString();
                String interone= inter1.getText().toString();
                String intertwo= inter2.getText().toString();
                String interthree= inter3.getText().toString();

                if (lenom.length()<5){
                    Toast.makeText(getApplicationContext(), "Entrer le nom de la réunion", Toast.LENGTH_LONG).show();
                }else if (!(letoken.length()==6)){
                    Toast.makeText(getApplicationContext(), "le Token doit être sur six chiffres", Toast.LENGTH_LONG).show();
                }else {
                    addRenuion(lenom,letoken,interone,intertwo,interthree,lespeaker);
                }
            }
        });
    }

    private void addRenuion(final String lenom, final String letoken,final String interone ,final String intertwo,final String interthree,final String lespeaker) {
        final DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();
        rootref.keepSynced(true);
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("reunion").child(letoken).exists())){
                    final HashMap<String,Object> userDataMap =new HashMap<>();
                    userDataMap.put("name",lenom);
                    userDataMap.put("token",letoken);
                    userDataMap.put("speaker",lespeaker);
                    userDataMap.put("inter1",interone);
                    userDataMap.put("inter2",intertwo);
                    userDataMap.put("inter3",interthree);

                    rootref.child("reunion").child(letoken).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                addrole(lespeaker,interone,intertwo,interthree);

                                Toast.makeText(getApplicationContext(), "la réunion est ajouté avec succes", Toast.LENGTH_LONG).show();
                                 Intent intent =new Intent(getApplicationContext(),admin.class);
                                 startActivity(intent);
                            }
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        try {
            JitsiMeetConferenceOptions SRC = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setRoom(letoken)
                    .setAudioMuted(true)
                    .setVideoMuted(true)
                    .setAudioOnly(false)
                    .setWelcomePageEnabled(false)
                    .build();
                          /*          JitsiMeetConferenceOptions AR = new JitsiMeetConferenceOptions.Builder()
                                            .setServerURL(new URL("https://meet.jit.si"))
                                            .setRoom(letoken+"AR")
                                            .setAudioMuted(true)
                                            .setVideoMuted(true)
                                            .setAudioOnly(false)
                                            .setWelcomePageEnabled(false)
                                            .build();
                                    JitsiMeetConferenceOptions FR = new JitsiMeetConferenceOptions.Builder()
                                            .setServerURL(new URL("https://meet.jit.si"))
                                            .setRoom(letoken+"FR")
                                            .setAudioMuted(true)
                                            .setVideoMuted(true)
                                            .setAudioOnly(false)
                                            .setWelcomePageEnabled(false)
                                            .build();
                                    JitsiMeetConferenceOptions ENG = new JitsiMeetConferenceOptions.Builder()
                                            .setServerURL(new URL("https://meet.jit.si"))
                                            .setRoom(letoken+"ENG")
                                            .setAudioMuted(true)
                                            .setVideoMuted(true)
                                            .setAudioOnly(false)
                                            .setWelcomePageEnabled(false)
                                            .build();*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void addrole(String lespeaker, String interone, String intertwo, String interthree) {
        FirebaseDatabase database =firebaseDatabase.getInstance();
        userRef=database.getReference("Users");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firebaseAuth=FirebaseAuth.getInstance();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    if (dataSnapshot1.child("email").getValue().toString().equalsIgnoreCase(lespeaker)) {
                        String uid= dataSnapshot1.child("uid").getValue().toString();
                        final HashMap<String,Object> userDataMap =new HashMap<>();
                        userDataMap.put("type","speaker");
                        userRef.child(uid).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"le sepaker est ajouté",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if ((dataSnapshot1.child("email").getValue().toString().equalsIgnoreCase(interone))|(dataSnapshot1.child("email").getValue().toString().equalsIgnoreCase(intertwo))|(dataSnapshot1.child("email").getValue().toString().equalsIgnoreCase(interthree))){
                        String uid= dataSnapshot1.child("uid").getValue().toString();
                        final HashMap<String,Object> userDataMap1 =new HashMap<>();
                        userDataMap1.put("type","interprete");
                        userRef.child(uid).updateChildren(userDataMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"interprete est ajouté",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        final HashMap<String,Object> userDataMap2 =new HashMap<>();
                        String uid= dataSnapshot1.child("uid").getValue().toString();
                        userDataMap2.put("type","audience");
                        userRef.child(uid).updateChildren(userDataMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"audience est ajouté",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    Intent intent =new Intent(getApplicationContext(),admin.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
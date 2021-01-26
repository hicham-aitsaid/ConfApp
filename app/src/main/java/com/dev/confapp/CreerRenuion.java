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

    EditText name,token;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_renuion);

        name=findViewById(R.id.name);
        token=findViewById(R.id.token);
        add=findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lenom=name.getText().toString();
                String letoken=token.getText().toString();

                if (lenom.length()<5){
                    Toast.makeText(getApplicationContext(), "Entrer le nom de la réunion", Toast.LENGTH_LONG).show();
                }else if (!(letoken.length()==6)){
                    Toast.makeText(getApplicationContext(), "le Token doit être sur six chiffres", Toast.LENGTH_LONG).show();
                }else {
                    addRenuion(lenom,letoken);
                }
            }
        });


    }

    private void addRenuion(final String lenom, final String letoken) {
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
                    rootref.child("reunion").child(letoken).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                try {
                                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                            .setServerURL(new URL("https://meet.jit.si"))
                                            .setRoom("testtest")
                                            .setAudioMuted(false)
                                            .setVideoMuted(false)
                                            .setAudioOnly(false)
                                            .setWelcomePageEnabled(false)
                                            .build();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), "la réunion est ajouté avec succes", Toast.LENGTH_LONG).show();
                                Intent intent =new Intent(getApplicationContext(),admin.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
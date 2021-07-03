package com.dev.confapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.util.ArrayList;
import java.util.HashMap;

public class CreerRenuion extends AppCompatActivity {

    EditText name,token;
    Button add;
    FirebaseDatabase database;
    DatabaseReference userRef;
    AutoCompleteTextView autoCompleteTextView;
    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_renuion);

        name=findViewById(R.id.name);
        token=findViewById(R.id.token);
        add=findViewById(R.id.add);


        final ArrayList<String> listechantier = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");
        userRef.keepSynced(true);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (!(listechantier.contains(dataSnapshot.child("email").getValue().toString()))) {
                        listechantier.add(dataSnapshot.child("email" +
                                "" +
                                "").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        String[] lista1 = new String[listechantier.size()];
        lista1 =listechantier.toArray(lista1);
        autoCompleteTextView =findViewById(R.id.admin);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,lista1);
        autoCompleteTextView.setAdapter(adapter);



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
                    Intent intent =new Intent(getApplicationContext(),role.class);
                    intent.putExtra("token",letoken);
                    intent.putExtra("nom",lenom);

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
                                            .setRoom(lenom)
                                            .setAudioMuted(true)
                                            .setVideoMuted(true)
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
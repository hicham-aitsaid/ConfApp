package com.dev.confapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignIn extends AppCompatActivity {
    TextView inscreption;
    Button connecter;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inscreption=findViewById(R.id.inscreption);
        String mystring=new String("inscription");
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        inscreption.setText(content);
        connecter=findViewById(R.id.connecter);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pswrd = password.getText().toString();
                if (!isEmailValid(mail)) {
                    Toast.makeText(getApplicationContext(), "verifier votre email", Toast.LENGTH_LONG).show(); }
                else if (pswrd.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Entrer votre mot de passe", Toast.LENGTH_LONG).show();
                }
                else {
                    connect(mail,pswrd);
                }
            }
        });

        inscreption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
    private void connect(final String mail, String pswrd){
        FirebaseAuth firebaseAuth;
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(mail,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (mail.equalsIgnoreCase("admin@gmail.com")){
                        Intent intent =new Intent(getApplicationContext(),admin.class);
                        startActivity(intent);
                    }else {
                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(getApplicationContext(), "Vous avez connecté", Toast.LENGTH_LONG).show();
                }else  {
                    Toast.makeText(getApplicationContext(), "Connexion est echoué", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isEmailValid(String mail) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher((CharSequence) mail).matches();

    }
}
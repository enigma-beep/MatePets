//Reg Act
package com.example.matepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matepets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegActivity extends AppCompatActivity {
    EditText e,p;
    Button btn;
    private FirebaseAuth mAuth;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        e = (EditText) findViewById(R.id.editText);
        p = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    public void register() {
        email = e.getText().toString().trim();
//         passEmail = email;
        String pass = p.getText().toString().trim();
        if (email.isEmpty()) {
            e.setError("Email Required!");
            e.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e.setError("Please Enter a Valid Email!");
            e.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            p.setError("Password Required!");
            p.requestFocus();
            return;
        }
        if (p.length() < 6) {
            p.setError("Please Enter a valid Password!");
            p.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"Registration successful, please check your email for verfication!",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegActivity.this,RegActivity.class);
//                                i.putExtra("passEmail",email);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else{

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"This email is already registered!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

package com.example.matepets;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    Button mLogin;
    EditText mPass,mEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mPass = findViewById(R.id.etPass);
        mEmail = findViewById(R.id.etEmail);
        mAuth = FirebaseAuth.getInstance();

        mLogin=findViewById(R.id.loginbtn);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(SigninActivity.this);
                pd.setMessage("Loading...");
                pd.show();
                logIn();
            }
        });


    }
    public void logIn() {
        String email = mEmail.getText().toString().trim();
        String pass = mPass.getText().toString().trim();
        if (email.isEmpty()) {
            mEmail.setError("Email Required!");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please Enter a Valid Email!");
            mEmail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            mPass.setError("Password Required!");
            mPass.requestFocus();
            return;
        }
        if (mPass.length() < 6) {
            mPass.setError("Please Enter a valid Password!");
            mPass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(mAuth.getCurrentUser().isEmailVerified()){
                        //Go to HomePage
                        String userid = ""+mAuth.getCurrentUser();
                        String uid = mAuth.getUid();
                        //Toast.makeText(getApplicationContext(),"CurrentUser :"+userid+"\nUid :"+uid,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), FilterActivity.class);
                        finish();
                        startActivity(i);
                        //clears previous activities
                        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please verify your email address first!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

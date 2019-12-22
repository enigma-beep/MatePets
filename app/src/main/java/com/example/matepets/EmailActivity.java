package com.example.matepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailActivity extends AppCompatActivity {
    TextView mEmail;
    Button mCont;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        mEmail=findViewById(R.id.tvEmail);
        mCont=findViewById(R.id.btCont);
        mAuth = FirebaseAuth.getInstance();

        final String email=getIntent().getStringExtra("Email");
        final String pass=getIntent().getStringExtra("Pass");
        mEmail.setText(email);

        mCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                //Go to HomePage
                                String userid = ""+mAuth.getCurrentUser();
                                String uid = mAuth.getUid();
                               // Toast.makeText(getApplicationContext(),"CurrentUser :"+userid+"\nUid :"+uid,Toast.LENGTH_LONG).show();
                                Intent i = new Intent(EmailActivity.this,AddPetsActivity.class);
                                startActivity(i);
                                finish();
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
        });
    }
}

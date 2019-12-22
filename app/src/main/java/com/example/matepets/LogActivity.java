package com.example.matepets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.TimeUnit;

public class LogActivity extends AppCompatActivity {
    EditText vericode;
    TextView viewphone;
    FirebaseAuth mAuth;
    Uri uriProfileImage;
    String codeSent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("owners");
    StorageReference storageReference;
    private static final int CHOOSE_IMAGE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);



        mAuth=FirebaseAuth.getInstance();
//        phone=findViewById(R.id.phone);
        vericode=findViewById(R.id.vericode);
        viewphone=findViewById(R.id.textView7);

        final String phnew=getIntent().getStringExtra("phon");
        final String mName=getIntent().getStringExtra("name");
        final String mEmail=getIntent().getStringExtra("email");
        final String mPass=getIntent().getStringExtra("pass");
        final String mCity=getIntent().getStringExtra("city");
        final String ownimg=getIntent().getStringExtra("ownimg");
        viewphone.setText(phnew);

//        PhoneAuthProvider.getInstance().verifyPhoneNumber(phnew, 60, TimeUnit.SECONDS, this, mCallbacks);
        sendVerificationCode();


        findViewById(R.id.getcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();



            }
        });
        findViewById(R.id.submit).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifySigninCode();

            }
        }));
    }
    private void VerifySigninCode(){
        String code=vericode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"OTP VERIFIED",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(LogActivity.this,AddPetsActivity.class);
                            finish();
                            startActivity(i);

                        }
                        else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),"Wrong OTP",Toast.LENGTH_LONG).show();
                            }




                        }
                    }
                });
    }


    private void sendVerificationCode() {

        String phno = viewphone.getText().toString();
        Toast.makeText(getApplicationContext(),"OTP sent to "+phno,Toast.LENGTH_LONG).show();
//        if (phno.isEmpty()) {
//            phone.setError("Phone no is empty");
//            phone.requestFocus();
//            return;
//        }
//        if (phno.length() < 10) {
//            phone.setError("Enter valid phone number");
//            phone.requestFocus();
//            return;
//        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phno, 60, TimeUnit.SECONDS, this, mCallbacks);

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent=s;
        }
    };
}

package com.example.matepets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {
    TextView mEmail;
    Button mCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        mEmail=findViewById(R.id.tvEmail);
        mCont=findViewById(R.id.btCont);

        final String email=getIntent().getStringExtra("email");
        mEmail.setText(email);
        mCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EmailActivity.this,AddPetsActivity.class);
                startActivity(i);
            }
        });
    }
}

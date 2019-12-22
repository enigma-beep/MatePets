package com.example.matepets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class test1Activity extends AppCompatActivity {
    EditText mCity,mPhone,mEmail,mName;
    Button mOTP,mEmailbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        mPhone=(EditText) findViewById(R.id.etPhone);
        mEmail=(EditText) findViewById(R.id.etEmail);

        String[] CITIES = new String[] {"Guwahati", "Tezpur", "Jorhat", "Dibrugarh","Tinsukia"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        test1Activity.this,
                        R.layout.dropdown_menu_popup_item,
                        CITIES);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.etCity);
        editTextFilledExposedDropdown.setAdapter(adapter);

        mOTP=findViewById(R.id.btnOTP);
        mEmailbtn=findViewById(R.id.btnEMAIL);
        mOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone=mPhone.getText().toString();
                Intent i =new Intent(test1Activity.this,LogActivity.class);
                i.putExtra("phon",phone);
                startActivity(i);

            }
        });
        mEmailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=mEmail.getText().toString().trim();
                Intent intent=new Intent(test1Activity.this,EmailActivity.class);


                intent.putExtra("email",email);
                startActivity(intent);
            }
        });







    }
}

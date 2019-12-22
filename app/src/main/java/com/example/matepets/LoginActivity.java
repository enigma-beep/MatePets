package com.example.matepets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    EditText mCity,mPhone,mEmail,mName,mPass,mRePass;
    Button cCity;
    Button mOTP,mEmailbtn,mProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProfilePic=findViewById(R.id.btProfilePic);
        mName=findViewById(R.id.etName);
        mPass=findViewById(R.id.etPass);
        mRePass=findViewById(R.id.etRePass);
        mPhone=(EditText) findViewById(R.id.etPhone);
        mEmail=(EditText) findViewById(R.id.etEmail);
        cCity=findViewById(R.id.chCity);
        mCity=findViewById(R.id.etCity);

        mOTP=findViewById(R.id.btnOTP);
        mEmailbtn=findViewById(R.id.btnEMAIL);
        mOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mName.getText().toString().equals("")){
                    mName.setError("Name can't be blank");
                }
                else if(mCity.getText().toString().equals("")){
                    mCity.setError("choose City");
                }
                else if(mPhone.getText().toString().equals("")){
                    mPhone.setError("Phone number can't be blank");
                    mPhone.requestFocus();
                }
                else if(mPhone.getText().toString().length()<10){
                    mPhone.setError("Enter a valid Phone number");
                    mPhone.requestFocus();
                }
                else if(mPass.getText().toString().equals("")){
                    mPass.setError("Password can't be blank");
                }
                else if(mRePass.getText().toString().equals("")){
                    mRePass.setError("Re-enter Password");
                }
                else if(!mRePass.getText().toString().equals(mPass.getText().toString())){
                    mRePass.setError("Password doesn't match with above Password");
                }
                else{
                    final String phone=mPhone.getText().toString();
                    Intent i =new Intent(LoginActivity.this,LogActivity.class);
                    i.putExtra("phon",phone);
                    startActivity(i);
                }





            }
        });
        mEmailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mName.getText().toString().equals("")){
                    mName.setError("Name can't be blank");
                }
                else if(mCity.getText().toString().equals("")){
                    mCity.setError("choose City");
                }
                else if(mEmail.getText().toString().equals("")){
                    mEmail.setError("Email can't be blank");
                }
                else if(mPass.getText().toString().equals("")){
                    mPass.setError("Password can't be blank");
                }
                else if(mRePass.getText().toString().equals("")){
                    mRePass.setError("Re-enter Password");
                }
                else if(!mRePass.getText().toString().equals(mPass.getText().toString())){
                    mRePass.setError("Password doesn't match with above Password");
                }
                else{
                    final String email=mEmail.getText().toString();
                    Intent i =new Intent(LoginActivity.this,EmailActivity.class);
                    i.putExtra("email",email);
                    startActivity(i);
                }
            }
        });

        cCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu=new PopupMenu(LoginActivity.this,cCity);
                popupMenu.getMenuInflater().inflate(R.menu.citymenu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                        mCity.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
//        mCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                final PopupMenu popupMenu=new PopupMenu(LoginActivity.this,mCity);
//                popupMenu.getMenuInflater().inflate(R.menu.citymenu,popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
//                        mCity.setText(menuItem.getTitle());
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        });




    }
}

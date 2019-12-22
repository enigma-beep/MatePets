package com.example.matepets;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.matepets.design.InstagramBottomBar;

public class Test2Activity extends AppCompatActivity {
    private InstagramBottomBar instagramBottomBar;
    private final int HOME = 0;
    private final int SEARCH = 1;
    private final int UPLOAD = 2;
    private final int LIKES = 3;
    private final int PROFILE = 4;

    @SuppressLint("ResourceType")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

//        instagramBottomBar=new InstagramBottomBar(findViewById(R.id.InstagramBottomBar),this,this);



    }
}

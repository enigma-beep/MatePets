package com.example.matepets;

import androidx.appcompat.app.AppCompatActivity;;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.matepets.R;
import com.example.matepets.design.InstagramBottomBar;
import com.example.matepets.models.BottomItem;
import com.example.matepets.*;
import com.example.matepets.design.adapter.viewholder.BottomBarViewHolder;
import com.example.matepets.design.adapter.BottomAdapter;

public class HomeTest2Activity extends AppCompatActivity implements BottomAdapter.BottomItemClickInterface {
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
        setContentView(R.layout.activity_home_test2);

        instagramBottomBar = new InstagramBottomBar(findViewById(R.id.InstagramBottomBar),  HomeTest2Activity.this,HomeTest2Activity.this);
        initBottomItems();

    }
    private void initBottomItems() {
        BottomItem home = new BottomItem(HOME, R.drawable.home, R.drawable.homefill, false);
        BottomItem search = new BottomItem(SEARCH, R.drawable.search, R.drawable.searchfill, false);
        BottomItem upload = new BottomItem(UPLOAD, R.drawable.upload, R.drawable.upload, false);
        BottomItem likes = new BottomItem(LIKES, R.drawable.likes, R.drawable.likesfill, true);
        BottomItem profile = new BottomItem(PROFILE, R.drawable.profile, R.drawable.profilefill, false);

        instagramBottomBar.addBottomItem(home);
        instagramBottomBar.addBottomItem(search);
        instagramBottomBar.addBottomItem(upload);
        instagramBottomBar.addBottomItem(likes);
        instagramBottomBar.addBottomItem(profile);
        instagramBottomBar.addBottomItem(profile);

        instagramBottomBar.apply(HOME);
    }

    public void itemSelect(int itemId) {
        switch (itemId) {
            case HOME:
//                Intent i=new Intent(this,SigninActivity.class);
//                startActivity(i);
                break;
            case SEARCH:
                break;
            case UPLOAD:
                Toast.makeText(HomeTest2Activity.this, "Upload a photo", Toast.LENGTH_LONG).show();
                break;
            case LIKES:
                break;
            case PROFILE:
                break;
        }
    }
}



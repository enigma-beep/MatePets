//package com.example.matepets;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class HomeTestActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_test);
//    }
//}
package com.example.matepets;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeTestActivity extends AppCompatActivity {

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
//
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private ProfileSliderAdapter sliderAdapter;
    //    private Button nextBtn;
//    private Button prevBtn;
    private int currentPage;
    private int visitCount = 0;
//    //fetching data
    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
    DatabaseReference owner_ref=fdb.getReference("owners");
    DatabaseReference pet_ref;
    FirebaseAuth mAuth;
    BottomNavigationView navigation;
    public ArrayList<String> slide_image = new ArrayList<String>();
    public ArrayList<String> slide_pet_name = new ArrayList<String>();
    public ArrayList<String> slide_type = new ArrayList<String>();
    int petCount;
    int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test);


        navigation = findViewById(R.id.bottomNavBar);
        viewPager = findViewById(R.id.profileViewPager);

        slide_image = getIntent().getStringArrayListExtra("slide_image");
        slide_pet_name = getIntent().getStringArrayListExtra("slide_pet_name");
        slide_type = getIntent().getStringArrayListExtra("slide_type");

//        dotsLayout=findViewById(R.id.dotsLayout);

//        nextBtn=findViewById(R.id.buttonnext);
//        prevBtn=findViewById(R.id.buttonprevious);


            sliderAdapter = new ProfileSliderAdapter(HomeTestActivity.this,slide_image,slide_pet_name,slide_type);

            Menu menu = navigation.getMenu();
            MenuItem menuItem = menu.getItem(0);
            menuItem.setChecked(true);




        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
//                        Intent a = new Intent(HomeTestActivity.this, HomeTestActivity.class);
//                        startActivity(a);
                        break;
                    case R.id.action_shop:
                        break;
                    case R.id.vet:
                        break;
                    case R.id.action_profile:
                        Intent i = new Intent(HomeTestActivity.this, AccountActivity.class);
                        i.putStringArrayListExtra("slide_image",slide_image);
                        i.putStringArrayListExtra("slide_pet_name",slide_pet_name);
                        i.putStringArrayListExtra("slide_type",slide_type);
                        startActivity(i);
                        finish();
                        break;
                }
                return false;
            }
        });
        viewPager.setAdapter(sliderAdapter);
//        addDotesInd(0);
        viewPager.addOnPageChangeListener(viewListener);

//        nextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(currentPage+1);
//            }
//        });
//        prevBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(currentPage-1);
//            }
//        });
    }

//    public void addDotesInd(int position) {
//        dots = new TextView[3];
//        dotsLayout.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(getResources().getColor(R.color.transparentwhite));
//            dotsLayout.addView(dots[i]);
//
//        }
//        if (dots.length > 0) {
//            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
//        }
//    }
}


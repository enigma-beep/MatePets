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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
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


    private HorizontalInfiniteCycleViewPager viewPager;
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
    public ArrayList<String> slide_breed = new ArrayList<String>();
    public ArrayList<String> slide_color = new ArrayList<String>();
    public ArrayList<String> slide_age = new ArrayList<String>();

    int petCount;
    int i;
    String found;
    String type,breed,gender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test);


        navigation = findViewById(R.id.bottomNavBar);
        viewPager = findViewById(R.id.profileViewPager);

        slide_image = getIntent().getStringArrayListExtra("slide_image");
        slide_pet_name = getIntent().getStringArrayListExtra("slide_pet_name");
        slide_type = getIntent().getStringArrayListExtra("slide_type");
        slide_breed = getIntent().getStringArrayListExtra("slide_breed");
        slide_color = getIntent().getStringArrayListExtra("slide_color");
        slide_age = getIntent().getStringArrayListExtra("slide_age");

        //fetching filters
        found = getIntent().getStringExtra("found");
        type = getIntent().getStringExtra("type");
        breed = getIntent().getStringExtra("breed");
        gender = getIntent().getStringExtra("gender");


        if(found.equals("true")){
            sliderAdapter = new ProfileSliderAdapter(HomeTestActivity.this,slide_image,slide_pet_name,slide_type,slide_breed,slide_color,slide_age);
        }
        else{
            sliderAdapter = new ProfileSliderAdapter(HomeTestActivity.this,slide_image,slide_pet_name,slide_type,slide_breed,slide_color,slide_age);
            Toast.makeText(getApplicationContext(),"OOPS! No matches found for your filters",Toast.LENGTH_LONG).show();
        }

        viewPager.setAdapter(sliderAdapter);
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
                        i.putStringArrayListExtra("slide_breed",slide_breed);
                        i.putStringArrayListExtra("slide_color",slide_color);
                        i.putStringArrayListExtra("slide_age",slide_age);
                        //passing filters
                        i.putExtra("breed",breed);
                        i.putExtra("gender",gender);
                        i.putExtra("type",type);
                        i.putExtra("found",found);
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


    }

    final public Object instantiateItem(View collection, final int pos) { //have to make final so we can see it inside of onClick()
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View page = inflater.inflate(R.layout.profile_slide_layout, null);

        page.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this will log the page number that was click
                Toast.makeText(getApplicationContext(), "this page was selected", Toast.LENGTH_SHORT).show();
            }
        });


        ((ViewPager) collection).addView(page, 0);
        return page;
    }
}


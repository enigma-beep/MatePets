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
import android.text.Html;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeTestActivity extends AppCompatActivity {

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
//            addDotesInd(i);
//            currentPage=i;
//            if(i==0){
//                nextBtn.setEnabled(true);
//                prevBtn.setEnabled(false);
//                prevBtn.setVisibility(View.INVISIBLE);
//                nextBtn.setText("NEXT");
//                prevBtn.setText("");
//            }
//            else if(i==dots.length-1){
//                nextBtn.setEnabled(true);
//                prevBtn.setEnabled(true);
//                prevBtn.setVisibility(View.VISIBLE);
//                nextBtn.setText("FINISH");
//                prevBtn.setText("BACK");
//                nextBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent i=new Intent(HomeTestActivity.this,HomeTestActivity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                });
//            }
//            else{
//                nextBtn.setEnabled(true);
//                prevBtn.setEnabled(true);
//                prevBtn.setVisibility(View.VISIBLE);
//                nextBtn.setText("NEXT");
//                prevBtn.setText("BACK");
//            }
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
    public ArrayList<Integer> slide_image = new ArrayList<Integer>();
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

        slide_image = getIntent().getIntegerArrayListExtra("slide_image");
        slide_pet_name = getIntent().getStringArrayListExtra("slide_pet_name");
        slide_type = getIntent().getStringArrayListExtra("slide_type");
        petCount = Integer.parseInt(getIntent().getStringExtra("petCount"));

//        dotsLayout=findViewById(R.id.dotsLayout);

//        nextBtn=findViewById(R.id.buttonnext);
//        prevBtn=findViewById(R.id.buttonprevious);

        //fetching data from database(YourPets)
        /*mAuth = FirebaseAuth.getInstance();
        pet_ref=owner_ref.child(mAuth.getCurrentUser().getUid()).child("pets");

        for ( i=1;i<=3;i++){
            String count = String.valueOf(i);
            pet_ref.child(count).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String pet_name = dataSnapshot.child("name").getValue().toString();
                    String pet_type = dataSnapshot.child("type").getValue().toString();
                    String pet_img = dataSnapshot.child("imageId").getValue().toString();
                    //Toast.makeText(getApplicationContext(),pet_name,Toast.LENGTH_SHORT).show();
                    slide_pet_name.add(pet_name);
                    slide_type.add(pet_type);
                    slide_image.add(R.drawable.dog1);
                    wait = 1;

                    Toast.makeText(getApplicationContext(),"Fetched! Wait = "+wait+" i = "+i,Toast.LENGTH_SHORT).show();

                    // Toast.makeText(getApplicationContext(),"Constructor!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        */

            sliderAdapter = new ProfileSliderAdapter(HomeTestActivity.this,slide_image,slide_pet_name,slide_type);
            Toast.makeText(getApplicationContext(),"Done!",Toast.LENGTH_SHORT).show();



            Menu menu = navigation.getMenu();
            MenuItem menuItem = menu.getItem(0);
            menuItem.setChecked(true);




        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(HomeTestActivity.this, HomeTestActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_shop:
                        break;
                    case R.id.vet:
                        break;
                    case R.id.action_profile:
                        Intent b = new Intent(HomeTestActivity.this, AccountActivity.class);
                        startActivity(b);
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


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

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test);

        viewPager = findViewById(R.id.profileViewPager);
//        dotsLayout=findViewById(R.id.dotsLayout);

//        nextBtn=findViewById(R.id.buttonnext);
//        prevBtn=findViewById(R.id.buttonprevious);

        sliderAdapter = new ProfileSliderAdapter(this);
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

    public void addDotesInd(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentwhite));
            dotsLayout.addView(dots[i]);

        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
}


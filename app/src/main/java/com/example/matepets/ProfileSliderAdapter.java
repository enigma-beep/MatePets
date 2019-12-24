package com.example.matepets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileSliderAdapter extends PagerAdapter {

//    public int[] slide_image = {
//            R.drawable.dog1,
//            R.drawable.dog2,
//            R.drawable.dog3
//
//    };
//    public String[] slide_heading = {
//            "Ellie", "Ben", "Python"
//    };
//    public String[] slide_desc = {
//            "Golden Retriever", "Pug", "German Shepherd"
//    };
    public ArrayList<Integer> slide_image = new ArrayList<Integer>();
    public ArrayList<String> slide_heading = new ArrayList<String>();
    public ArrayList<String> slide_desc = new ArrayList<String>();

    Context context;
    LayoutInflater layoutInflater;

    public ProfileSliderAdapter(){}

    public ProfileSliderAdapter(Context context,ArrayList<Integer> slide_image, ArrayList<String> slide_heading, ArrayList<String> slide_desc) {
        this.slide_image = slide_image;
        this.slide_heading = slide_heading;
        this.slide_desc = slide_desc;
        this.context = context;
    }

    public ArrayList<Integer> getSlide_image() {
        return slide_image;
    }

    public void setSlide_image(ArrayList<Integer> slide_image) {
        this.slide_image = slide_image;
    }

    public ArrayList<String> getSlide_heading() {
        return slide_heading;
    }

    public void setSlide_heading(ArrayList<String> slide_heading) {
        this.slide_heading = slide_heading;
    }

    public ArrayList<String> getSlide_desc() {
        return slide_desc;
    }

    public void setSlide_desc(ArrayList<String> slide_desc) {
        this.slide_desc = slide_desc;
    }

//    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
//    DatabaseReference owner_ref=fdb.getReference("owners");
//    DatabaseReference pet_ref;
//    FirebaseAuth mAuth;



    public ProfileSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_heading.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        slide_image.add( R.drawable.dog1);
//        slide_image.add( R.drawable.dog2);
//        slide_image.add( R.drawable.dog3);

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.profile_slide_layout, container, false);
        ImageView slideImageView = view.findViewById(R.id.imageView);
        TextView slideTextView = view.findViewById(R.id.textView);
        TextView slideTextView2 = view.findViewById(R.id.textView2);

        //slideImageView.setImageResource(slide_image[position]);

        slideImageView.setImageResource(slide_image.get(position));
        slideTextView.setText(slide_heading.get(position));
        slideTextView2.setText(slide_desc.get(position));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }


}

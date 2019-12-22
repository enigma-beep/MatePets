package com.example.matepets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slide_image={
            R.drawable.a,
            R.drawable.b,
            R.drawable.c

    };
    public String[] slide_heading={
            "Welcome","to","Matepet"
    };
    public String[] slide_desc={
            "Description 1","Description 2","Description 3"
    };
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=view.findViewById(R.id.imageView);
        TextView slideTextView=view.findViewById(R.id.textView);
        TextView slideTextView2=view.findViewById(R.id.textView2);

        slideImageView.setImageResource(slide_image[position]);
        slideTextView.setText(slide_heading[position]);
        slideTextView2.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

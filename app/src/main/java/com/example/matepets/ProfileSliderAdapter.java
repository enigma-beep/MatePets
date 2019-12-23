package com.example.matepets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ProfileSliderAdapter extends PagerAdapter {

    public int[] slide_image = {
            R.drawable.dog1,
            R.drawable.dog2,
            R.drawable.dog3

    };
    public String[] slide_heading = {
            "Ellie", "Ben", "Python"
    };
    public String[] slide_desc = {
            "Golden Retriever", "Pug", "German Shepherd"
    };
    Context context;
    LayoutInflater layoutInflater;

    public ProfileSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.profile_slide_layout, container, false);
        ImageView slideImageView = view.findViewById(R.id.imageView);
        TextView slideTextView = view.findViewById(R.id.textView);
        TextView slideTextView2 = view.findViewById(R.id.textView2);

        slideImageView.setImageResource(slide_image[position]);
        slideTextView.setText(slide_heading[position]);
        slideTextView2.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

}

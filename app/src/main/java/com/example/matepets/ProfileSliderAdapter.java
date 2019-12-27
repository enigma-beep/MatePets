package com.example.matepets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileSliderAdapter extends PagerAdapter {


    public ArrayList<String> slide_image = new ArrayList<String>();
    public ArrayList<String> slide_heading = new ArrayList<String>();
    public ArrayList<String> slide_desc = new ArrayList<String>();
    public ArrayList<String> slide_breed = new ArrayList<String>();
    public ArrayList<String> slide_color = new ArrayList<String>();
    public ArrayList<String> slide_age = new ArrayList<String>();

    Context context;
    LayoutInflater layoutInflater;

    public ProfileSliderAdapter(){}

    public ProfileSliderAdapter(Context context,ArrayList<String> slide_image, ArrayList<String> slide_heading, ArrayList<String> slide_desc,ArrayList<String> slide_breed,ArrayList<String> slide_color,ArrayList<String> slide_age) {
        this.slide_image = slide_image;
        this.slide_heading = slide_heading;
        this.slide_desc = slide_desc;
        this.slide_breed = slide_breed;
        this.slide_color = slide_color;
        this.slide_age = slide_age;
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

    Dialog myDialog;

    public void ShowPopup(View v, Drawable pic, String name, String type,String breed, String color, String age) {
        Button dismiss;
        ImageView profilepic;
        TextView mName, mType,mBreed,mColor,mAge;

        myDialog.setContentView(R.layout.profilepopup);
        dismiss = myDialog.findViewById(R.id.pDismiss);
        profilepic = myDialog.findViewById(R.id.pPetImg);
        mName = myDialog.findViewById(R.id.pName);
        mType = myDialog.findViewById(R.id.pType);
        mBreed= myDialog.findViewById(R.id.pBreed);
        mColor=myDialog.findViewById(R.id.pColor);
        mAge=myDialog.findViewById(R.id.pAge);
        mName.setText(name);
        mType.setText(type);
        mBreed.setText(breed);
        mColor.setText(color);
        mAge.setText(age);
        profilepic.setImageDrawable(pic);

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
//        slide_image.add( R.drawable.dog1);
//        slide_image.add( R.drawable.dog2);
//        slide_image.add( R.drawable.dog3);

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.profile_slide_layout, container, false);
        final ImageView slideImageView = view.findViewById(R.id.imageView);
        final TextView slideTextView = view.findViewById(R.id.textView);
        final TextView slideTextView2 = view.findViewById(R.id.textView2);
        final TextView textViewBreed=view.findViewById(R.id.textviewBreed);
        final TextView textViewColor=view.findViewById(R.id.textviewColor);
        final TextView textViewAge=view.findViewById(R.id.textviewAge);
        final Button show = view.findViewById(R.id.showbtn);
        myDialog = new Dialog(context);



        //slideImageView.setImageResource(slide_image[position]);

        //slideImageView.setImageBitmap(slide_image.get(position));
        Picasso.get().load(slide_image.get(position)).into(slideImageView);
        slideTextView.setText(slide_heading.get(position));
        slideTextView2.setText(slide_desc.get(position));

//        textViewAge.setText(slide_age.get(position));
//        textViewBreed.setText(slide_breed.get(position));
//        textViewColor.setText(slide_color.get(position));
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(view, slideImageView.getDrawable(), slideTextView.getText().toString(), slideTextView2.getText().toString(),textViewBreed.getText().toString(),textViewColor.getText().toString(),textViewAge.getText().toString());
//              Toast.makeText(context,slide_age.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + slide_heading.get(position), Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(context,ProfileInfoActivity.class);
//                ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(context,ViewCompat.getTransitionName(slideImageView));
//                context.startActivity(i,options.toBundle());
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }


}

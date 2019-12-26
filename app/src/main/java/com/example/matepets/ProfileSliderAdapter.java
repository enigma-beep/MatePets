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

    Context context;
    LayoutInflater layoutInflater;

    public ProfileSliderAdapter(){}

    public ProfileSliderAdapter(Context context,ArrayList<String> slide_image, ArrayList<String> slide_heading, ArrayList<String> slide_desc) {
        this.slide_image = slide_image;
        this.slide_heading = slide_heading;
        this.slide_desc = slide_desc;
        this.context = context;
    }

    public ArrayList<String> getSlide_image() {
        return slide_image;
    }

    public void setSlide_image(ArrayList<String> slide_image) {
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

    Dialog myDialog;

    public void ShowPopup(View v, Drawable pic, String name, String type) {
        Button dismiss;
        ImageView profilepic;
        TextView mName, mType;

        myDialog.setContentView(R.layout.profilepopup);
        dismiss = myDialog.findViewById(R.id.pDismiss);
        profilepic = myDialog.findViewById(R.id.pPetImg);
        mName = myDialog.findViewById(R.id.pName);
        mType = myDialog.findViewById(R.id.pType);
        mName.setText(name);
        mType.setText(type);
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
        final Button show = view.findViewById(R.id.showbtn);
        myDialog = new Dialog(context);



        //slideImageView.setImageResource(slide_image[position]);

        //slideImageView.setImageBitmap(slide_image.get(position));
        Picasso.get().load(slide_image.get(position)).into(slideImageView);
        slideTextView.setText(slide_heading.get(position));
        slideTextView2.setText(slide_desc.get(position));
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(view, slideImageView.getDrawable(), slideTextView.getText().toString(), slideTextView2.getText().toString());
//
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

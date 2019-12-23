package com.example.matepets.models;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matepets.R;
import com.squareup.picasso.Picasso;

public class rc extends RecyclerView.ViewHolder {
    View mview;

    public rc(@NonNull View itemView) {
        super(itemView);
        mview = itemView;

    }

    public void setDetails(Context ctx, String name, String type, String breed, String age, String gender, String imageId) {
        TextView mtitle1 = mview.findViewById(R.id.title1);
        mtitle1.setText(name);
        TextView mtitle2 = mview.findViewById(R.id.title2);
        mtitle2.setText(breed);
        TextView mtitle3 = mview.findViewById(R.id.title3);
        mtitle3.setText(type);
        TextView mtitle4 = mview.findViewById(R.id.title4);
        mtitle4.setText(age);
        TextView mtitle5 = mview.findViewById(R.id.title5);
        mtitle5.setText(gender);
        ImageView mimgv = mview.findViewById(R.id.imgv);
        Picasso.get().load(imageId).into(mimgv);
    }

}

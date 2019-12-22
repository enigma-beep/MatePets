package com.uigitdev.instagrambottombar.uigitdev.design.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uigitdev.instagrambottombar.R;

public class BottomBarViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout bottom_item_parent;
    public RelativeLayout bottom_parent;
    public ImageView bottom_icon;
    public CardView bottom_notification;

    public BottomBarViewHolder(@NonNull View itemView) {
        super(itemView);
        bottom_item_parent = itemView.findViewById(R.id.bottom_item_parent);
        bottom_parent = itemView.findViewById(R.id.bottom_parent);
        bottom_icon = itemView.findViewById(R.id.bottom_icon);
        bottom_notification = itemView.findViewById(R.id.bottom_notification);
    }
}

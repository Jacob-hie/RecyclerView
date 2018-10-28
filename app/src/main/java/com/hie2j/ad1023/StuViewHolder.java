package com.hie2j.ad1023;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StuViewHolder extends RecyclerView.ViewHolder{

    ImageView headView;
    ImageView delView;
    TextView nameView;
    TextView ageView;
    TextView numView;
    View view;


    public StuViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;

        headView = view.findViewById(R.id.iv_head);
        delView = view.findViewById(R.id.iv_del);
        nameView = view.findViewById(R.id.tv_name);
        ageView = view.findViewById(R.id.tv_age);
        numView = view.findViewById(R.id.tv_num);
    }
}

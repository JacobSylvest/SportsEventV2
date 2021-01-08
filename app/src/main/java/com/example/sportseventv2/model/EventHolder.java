package com.example.sportseventv2.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportseventv2.R;

public class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView;
    TextView eventTitle, eventText;
    ItemClickListener itemClickListener;

    EventHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView = itemView.findViewById(R.id.eventImage);
        this.eventTitle = itemView.findViewById(R.id.eventTitle);
        this.eventText = itemView.findViewById(R.id.eventText);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
}

package com.example.sportseventv2.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportseventv2.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventHolder> {

    Context context;
    ArrayList<EventModel> eventModels;

    public EventAdapter(Context context, ArrayList<EventModel> eventModels) {
        this.context = context;
        this.eventModels = eventModels;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,null);
        return new EventHolder(view); // returnerer view til Holder klassen
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.eventTitle.setText(eventModels.get(position).getEventTitle());
        holder.eventText.setText(eventModels.get(position).getEventText());
        holder.imageView.setImageResource(eventModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }
}

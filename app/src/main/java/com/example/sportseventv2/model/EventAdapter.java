package com.example.sportseventv2.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportseventv2.Event;
import com.example.sportseventv2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter til at læse tekst og billeder fra  online excel fil.
 * Smider tekst og billeder i en viewholder så recycler view kan bruge det.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private static final String TAG = "EventAdapter";
    LayoutInflater inflater;
    List<String> titles, descriptions, imageUrls;
    Context mContext;


    public EventAdapter(Context context, List<String> titles, List<String> descriptions, List<String> imageUrls){
        mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.titles = titles;
        this.descriptions = descriptions;
        this.imageUrls = imageUrls;

        Log.d(TAG, "Adapter: " + titles); // Bruges til debugging
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = titles.get(position);
        String desc = descriptions.get(position);
        String img = imageUrls.get(position);

        holder.title.setText(title);
        holder.content.setText(desc);
        //bruger picasso til at downloade event billede
        Picasso.get().load(img).into(holder.listImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: der er trykket på et Event");

                Intent intent = new Intent(mContext, Event.class);
                intent.putExtra("image_event",imageUrls.get(position));
                intent.putExtra("title_event",titles.get(position));
                intent.putExtra("description_event",descriptions.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listImg;
        TextView title,content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder: Billeder og tekst sat ind i cardview");
            listImg = itemView.findViewById(R.id.eventImage);
            title = itemView.findViewById(R.id.eventTitle);
            content = itemView.findViewById(R.id.eventText);
        }
    }
}
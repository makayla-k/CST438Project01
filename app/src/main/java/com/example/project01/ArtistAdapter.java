package com.example.project01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private List<Artist> artistInfoList;
    private int rowLayout;
    private Context context;

    public ArtistAdapter(List<Artist> artistInfoList, int list_item_artist, Context applicationContext) {

        this.artistInfoList = artistInfoList;
        rowLayout = list_item_artist;
        context = applicationContext;
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        LinearLayout artistLayout;
        ImageView artistImage;
        TextView name;
        TextView eventCount;

        public ArtistViewHolder(@NonNull View v) {
            super(v);

            artistLayout = (LinearLayout) v.findViewById(R.id.artist_layout);
            artistImage = (ImageView) v.findViewById(R.id.artist_image);
            name = (TextView) v.findViewById(R.id.name);
            eventCount = (TextView) v.findViewById(R.id.event_count);

        }
    }

    @Override
    public ArtistAdapter.ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ArtistViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistViewHolder holder, int position) {
        String image_url = artistInfoList.get(position).getImgageUrl();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.artistImage);

        holder.name.setText(artistInfoList.get(position).getName());
        holder.eventCount.setText("Number of upcoming events: " + String.valueOf(artistInfoList.get(position).getUpcomingEventCount()));
    }

    @Override
    public int getItemCount() {
        return artistInfoList.size();
    }
}

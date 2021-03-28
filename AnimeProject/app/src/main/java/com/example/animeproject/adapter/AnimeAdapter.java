package com.example.animeproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.animeproject.Adventure;
import com.example.animeproject.Models.Anime;
import com.example.animeproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnimeAdapter extends ArrayAdapter<Anime> {

    int Bcid;
    public AnimeAdapter(Context context, ArrayList<Anime>AnimeList,int Backgroundcolours) {
        super(context,0,AnimeList);
        this.Bcid=Backgroundcolours;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Anime currentAnime=getItem(position);


        TextView Title= listItemView.findViewById(R.id.title);
        Title.setText(currentAnime.getTitle());
        TextView episode=listItemView.findViewById(R.id.episodes);
        episode.setText(currentAnime.getNoe());
       ImageView coverImageView=listItemView.findViewById(R.id.cover_image);
        Picasso.with(getContext()).load(currentAnime.getImage_url()).into(coverImageView);
        View textcontainer=listItemView.findViewById(R.id.text_layout);
        int colour= ContextCompat.getColor(getContext(),Bcid);
        textcontainer.setBackgroundColor(colour);



        return listItemView;
    }
}

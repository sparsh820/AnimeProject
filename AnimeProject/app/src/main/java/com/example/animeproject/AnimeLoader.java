package com.example.animeproject;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.example.animeproject.Models.Anime;

import java.util.ArrayList;

public class AnimeLoader extends AsyncTaskLoader<ArrayList<Anime>> {

    String url;
    public AnimeLoader(@NonNull Context context,String url) {
        super(context);
        Log.d("InfoURl",url);
        this.url=url;
    }

    protected void onStartLoading() {
        forceLoad();
    }



    @Nullable
    @Override
    public ArrayList<Anime> loadInBackground() {
        if (url==null) {
            return null;
        }


        ArrayList<Anime> animes = QueryUtils.extractAnimes(url);
        url="";
        return animes;
    }
}

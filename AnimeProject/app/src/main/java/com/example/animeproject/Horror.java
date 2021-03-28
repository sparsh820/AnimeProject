package com.example.animeproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.animeproject.Models.Anime;
import com.example.animeproject.adapter.AnimeAdapter;

import java.util.ArrayList;

public class Horror extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Anime>> {

    private static final String HORROR_REQUEST_URL =
            "https://api.jikan.moe/v3/search/anime?q=horror&limit=50";
    private static final String LOG_TAG=Horror.class.getName();
    private TextView mEmptyStateTextView;


    private static final int HORROR_LOADER_ID = 2;
    private AnimeAdapter horrorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horror);

        ListView horrorListView = (ListView) findViewById(R.id.Horrorlist);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_horror);
        horrorListView.setEmptyView(mEmptyStateTextView);

        horrorAdapter=new AnimeAdapter(this,new ArrayList<Anime>(),R.color.Horror);
        if(isNetworkConnected()){
            LoaderManager.getInstance(this).initLoader(HORROR_LOADER_ID,null,this);
            horrorListView.setAdapter(horrorAdapter);
        }else{

            View loadingIndicator = findViewById(R.id.progressbar);
            loadingIndicator.setVisibility(View.GONE);


            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        horrorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Anime currentAnime = horrorAdapter.getItem(position);

                Intent intent=new Intent(Horror.this,AnimeItem.class);
                Bundle extras=new Bundle();
                extras.putString("image", currentAnime.getImage_url());
                extras.putString("title", currentAnime.getTitle());
                extras.putString("desc", currentAnime.getDesc());
                extras.putString("url",currentAnime.getURL());
                intent.putExtras(extras);

                startActivity(intent);
            }
        });


    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @NonNull
    @Override
    public Loader<ArrayList<Anime>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AnimeLoader(this,HORROR_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Anime>> loader, ArrayList<Anime> animes) {


        View loadingIndicator = findViewById(R.id.progressbar);
        loadingIndicator.setVisibility(View.GONE);


        mEmptyStateTextView.setText("Nothing");
        horrorAdapter.clear();

        if ( animes!= null && !animes.isEmpty()) {
            horrorAdapter.addAll(animes);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Anime>> loader) {

        horrorAdapter.clear();

    }
}
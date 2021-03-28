package com.example.animeproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.animeproject.Models.Anime;
import com.example.animeproject.adapter.AnimeAdapter;

import java.util.ArrayList;

public class Adventure extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Anime>>{

    private static final String ADVENTURE_REQUEST_URL =
            "https://api.jikan.moe/v3/search/anime?q=adventure&limit=50";
    private static final String LOG_TAG=Adventure.class.getName();
    private TextView mEmptyStateTextView;


    private static final int ADVENTURE_LOADER_ID = 1;
    private AnimeAdapter adventureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        // Find a reference to the {@link ListView} in the layout
        ListView adventureListView = (ListView) findViewById(R.id.Adventurelist);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_adventure);
        adventureListView.setEmptyView(mEmptyStateTextView);

        adventureAdapter=new AnimeAdapter(this,new ArrayList<Anime>(),R.color.Adventure);

        if(isNetworkConnected()){
            LoaderManager.getInstance(this).initLoader(ADVENTURE_LOADER_ID,null,this);
            adventureListView.setAdapter(adventureAdapter);
        }else{
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
             View loadingIndicator = findViewById(R.id.progressbar);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        adventureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Anime currentAnime = adventureAdapter.getItem(position);

                Intent intent=new Intent(Adventure.this,AnimeItem.class);
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
        return new AnimeLoader(this,ADVENTURE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Anime>> loader, ArrayList<Anime>animes) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.progressbar);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText("Nothing");
        adventureAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if ( animes!= null && !animes.isEmpty()) {
            adventureAdapter.addAll(animes);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Anime>> loader) {

        adventureAdapter.clear();

    }
}
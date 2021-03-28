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

public class Game extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Anime>> {

    private static final String GAME_REQUEST_URL =
            "https://api.jikan.moe/v3/search/anime?q=game&limit=50";
    private TextView mEmptyStateTextView;
    private static final int GAME_LOADER_ID = 0;
    private AnimeAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ListView gameListView = (ListView) findViewById(R.id.gamelist);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_game);
        gameListView.setEmptyView(mEmptyStateTextView);

        gameAdapter=new AnimeAdapter(this,new ArrayList<Anime>(),R.color.Game);
        if(isNetworkConnected()){
            LoaderManager.getInstance(this).initLoader(GAME_LOADER_ID,null,this);
            gameListView.setAdapter(gameAdapter);
        }else{

            View loadingIndicator = findViewById(R.id.progressbar);
            loadingIndicator.setVisibility(View.GONE);


            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        gameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Anime currentAnime = gameAdapter.getItem(position);

                Intent intent=new Intent(Game.this,AnimeItem.class);
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
        return new AnimeLoader(this,GAME_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Anime>> loader, ArrayList<Anime>animes) {

        View loadingIndicator = findViewById(R.id.progressbar);
        loadingIndicator.setVisibility(View.GONE);


        mEmptyStateTextView.setText("Nothing");
        gameAdapter.clear();

        if ( animes!= null && !animes.isEmpty()) {
            gameAdapter.addAll(animes);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Anime>> loader) {

        gameAdapter.clear();

    }
}
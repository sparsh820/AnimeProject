package com.example.animeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AnimeItem extends AppCompatActivity {

    private ImageView imageView;
    private TextView title,desc,url;
    private String descA,titleA,image_url,urlA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_item);

        if (getIntent().getExtras() != null) {
            //get all the extras from login Activity
            image_url = getIntent().getExtras().getString("image");
            descA = getIntent().getExtras().getString("desc");
            titleA = getIntent().getExtras().getString("title");
            urlA=getIntent().getExtras().getString("url");

        }

        imageView=findViewById(R.id.image_anime);
        Picasso.with(this).load(image_url).into(imageView);
        title=findViewById(R.id.txtTitle);
        title.setText(titleA);
        desc=findViewById(R.id.txtDescription);
        desc.setText(descA);
        url=findViewById(R.id.txturl);
        url.setText(urlA);

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri animeUri = Uri.parse(urlA);

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, animeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);

            }
        });



    }
}
package com.example.animeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnimeGenere extends AppCompatActivity {


    TextView Adventure,Horror,Game,Romance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_genere);
        Adventure=findViewById(R.id.adventure);
        Adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AnimeGenere.this,Adventure.class);
                startActivity(intent);


            }
        });

        Horror=findViewById(R.id.horror);
        Horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AnimeGenere.this,Horror.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivity(intent);




            }
        });

      Romance=findViewById(R.id.romance);
      Romance.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent=new Intent(AnimeGenere.this,Romance.class);
              startActivity(intent);



          }
      });

        Game=findViewById(R.id.game);
        Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AnimeGenere.this,Game.class);
                startActivity(intent);



            }
        });




    }
}
 package com.example.animeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

 public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView anime = findViewById(R.id.anime);
        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animationclockwise);
        anime.startAnimation(aniRotate);


        TextView world = findViewById(R.id.world);
        Animation antiRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animationanticlockwise);
        world.startAnimation(antiRotate);


        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {


                Intent i = new Intent(SplashActivity.this, AnimeGenere.class);

                startActivity(i);


                finish();

            }

        }, 8*1000);



    }
}
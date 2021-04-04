package com.example.angelbiker.ui.utilitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.angelbiker.R;
import com.example.angelbiker.ui.motos.ListaMarcasActivity;

public class IntroApplicationActivity extends AppCompatActivity {
    ImageView imageView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_starting_activity);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.motorbikesarting);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(3500);
        fadeIn.setStartOffset(3500);
        fadeIn.setFillAfter(true);

        imageView = findViewById(R.id.imagenentrada);

        AlphaAnimation fadeOun = new AlphaAnimation(1.0f, 0.0f);
        fadeIn.setDuration(3500);
        fadeIn.setStartOffset(3500);
        fadeIn.setFillAfter(true);


        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mediaPlayer.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(fadeOun);
                Intent intent = new Intent(context, ListaMarcasActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imageView.startAnimation(fadeIn);
    }
}
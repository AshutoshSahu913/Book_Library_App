package com.example.sqlitebooklibraryapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplshscreenActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2500; // 4 seconds
    ImageView backImg1,backImg2,backImg3,backImg4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splshscreen);

        backImg1 = findViewById(R.id.background1);


        // first 1
        // Create a scale animation to increase the size
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                -1f, 10f, // Start and end scale X
                -1f, 10f, // Start and end scale Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X relative to view width
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point Y relative to view height

        scaleAnimation.setDuration(2000); // Animation duration in milliseconds
        scaleAnimation.setFillAfter(true); // Maintain the final state of the animation

        // Apply the animation to the ImageView
        backImg1.startAnimation(scaleAnimation);

        // Use a Handler to delay the launch of the main activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplshscreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
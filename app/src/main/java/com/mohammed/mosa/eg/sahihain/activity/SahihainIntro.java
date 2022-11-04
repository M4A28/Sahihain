package com.mohammed.mosa.eg.sahihain.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.mohammed.mosa.eg.sahihain.R;

public class SahihainIntro extends AppCompatActivity {
    ProgressBar splashProgress;
    int SPLASH_TIME = 3000; //This is 3 seconds
    int PROGRESS_TIME = 4000; //This is 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahihain_intro);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        splashProgress = findViewById(R.id.splashProgress);
        playProgress();

        //Code to start timer and take action after the timer ends
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(SahihainIntro.this, MainActivity.class);
                startActivity(mySuperIntent);
                finish();

            }
        }, SPLASH_TIME);
    }

    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(PROGRESS_TIME)
                .start();
    }
}
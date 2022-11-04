package com.mohammed.mosa.eg.sahihain.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.utilty.Values;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }

    public void facebook(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Values.MY_FACEBOOK));
            intent.setPackage(Values.FACEBOOK_PACKAGE);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, Values.FACEBOOK_UNAVAILABLE, Toast.LENGTH_SHORT).show();
        }
    }

    public void twitter(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Values.MY_TWITTER));
            intent.setPackage(Values.TWITTER_PACKAGE);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, Values.TWITTER_UNAVAILABLE, Toast.LENGTH_SHORT).show();
        }
    }

    public void github(View view) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(Intent.EXTRA_TEXT, Values.MY_GITHUB);
        startActivity(intent);
    }
}
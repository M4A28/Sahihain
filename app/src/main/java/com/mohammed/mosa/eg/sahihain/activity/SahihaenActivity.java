package com.mohammed.mosa.eg.sahihain.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.adapter.HadithAdapter;
import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;

import java.util.ArrayList;

public class SahihaenActivity extends AppCompatActivity {

    RecyclerView rv_sahihaen;
    HadithAdapter hadithAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadithes);

        rv_sahihaen = findViewById(R.id.recyclerView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<Hadith> hadiths = databaseAccess.getAllHadith();
        databaseAccess.close();
        hadithAdapter = new HadithAdapter(hadiths, this);

        rv_sahihaen.setAdapter(hadithAdapter);
        rv_sahihaen.setLayoutManager(new LinearLayoutManager(this));

    }
}
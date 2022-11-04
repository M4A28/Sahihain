package com.mohammed.mosa.eg.sahihain.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.adapter.HadithAdapter;
import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;

import java.util.ArrayList;
import java.util.Objects;

public class ArbaeenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbaeen);

        RecyclerView rv_arbaeen = findViewById(R.id.rv_arbaeen);
        HadithAdapter hadithAdapter;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<Hadith> hadiths = databaseAccess.getArbaeen();
        databaseAccess.close();
        hadithAdapter = new HadithAdapter(hadiths, this);
        rv_arbaeen.setAdapter(hadithAdapter);
        rv_arbaeen.setLayoutManager(new LinearLayoutManager(this));

    }
}
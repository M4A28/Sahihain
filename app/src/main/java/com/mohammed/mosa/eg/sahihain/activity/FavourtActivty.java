package com.mohammed.mosa.eg.sahihain.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.adapter.HadithAdapter;
import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;

import java.util.ArrayList;

public class FavourtActivty extends AppCompatActivity {
    RecyclerView recyclerView;
    HadithAdapter adabter;
    TextView tv_fava_count;
    DatabaseAccess databaseAccess;
    ArrayList<Hadith> hadithsFave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourt_activty);


        tv_fava_count = findViewById(R.id.tv_count);
        databaseAccess = DatabaseAccess.getInstance(this);
        recyclerView = findViewById(R.id.rv_fave);

        databaseAccess.open();
        hadithsFave = databaseAccess.getAllFavert();
        tv_fava_count.setText((hadithsFave.size() > 0) ?
                "عدد الاحاديث في المفضلة: "+ hadithsFave.size(): "لا يوجد احاديث في المفضلة");
        databaseAccess.close();

        adabter = new HadithAdapter(hadithsFave, this);
        recyclerView.setAdapter(adabter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
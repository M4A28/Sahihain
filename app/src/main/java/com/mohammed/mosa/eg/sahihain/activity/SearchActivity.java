package com.mohammed.mosa.eg.sahihain.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
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

public class SearchActivity extends AppCompatActivity {

    RecyclerView rv_search;
    ArrayList<Hadith> hadithResult = new ArrayList<>();
    SearchView searchView;
    TextView tv_search;
    HadithAdapter hadithAdapter;
    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rv_search = findViewById(R.id.rv_search);
        searchView = findViewById(R.id.search_main);
        tv_search = findViewById(R.id.tv_result_count);
        databaseAccess = DatabaseAccess.getInstance(this);
        hadithAdapter = new HadithAdapter(hadithResult,this);
        rv_search.setAdapter(hadithAdapter);
        rv_search.setLayoutManager(new LinearLayoutManager(this));

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                databaseAccess.open();
                hadithResult = databaseAccess.search(s);
                databaseAccess.close();
                hadithAdapter.setHadiths(hadithResult);
                tv_search.setText("عدد النتائج: " + hadithResult.size());
//                hadithAdabter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });



    }
}
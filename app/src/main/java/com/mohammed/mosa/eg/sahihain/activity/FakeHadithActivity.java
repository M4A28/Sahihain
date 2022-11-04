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
import com.mohammed.mosa.eg.sahihain.adapter.FakeHadithAdapter;
import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.FakeHadith;

import java.util.ArrayList;

public class FakeHadithActivity extends AppCompatActivity {
    RecyclerView rv_search;
    ArrayList<FakeHadith> hadithResult = new ArrayList<>();
    SearchView searchView;
    TextView tv_search;
    FakeHadithAdapter fakeHadithAdapter;
    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feke_hadith);

        rv_search = findViewById(R.id.rv_search);
        searchView = findViewById(R.id.search_main);
        tv_search = findViewById(R.id.tv_result_count);
        tv_search.setVisibility(View.GONE);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        hadithResult = databaseAccess.getAllFake();
        databaseAccess.close();

        fakeHadithAdapter = new FakeHadithAdapter(hadithResult,this);
        rv_search.setAdapter(fakeHadithAdapter);
        rv_search.setLayoutManager(new LinearLayoutManager(this));

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                databaseAccess.open();
                hadithResult = databaseAccess.searchFake(s);
                databaseAccess.close();
                fakeHadithAdapter.setFakeHadithArrayList(hadithResult);
                tv_search.setVisibility(View.VISIBLE);
                tv_search.setText("عدد النتائج: " + hadithResult.size());
//                fakeHaditAdabter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                databaseAccess.open();
                hadithResult = databaseAccess.getAllFake();
                databaseAccess.close();
                fakeHadithAdapter.setFakeHadithArrayList(hadithResult);
                fakeHadithAdapter.notifyDataSetChanged();
                tv_search.setVisibility(View.GONE);
                return false;
            }
        });

    }
}
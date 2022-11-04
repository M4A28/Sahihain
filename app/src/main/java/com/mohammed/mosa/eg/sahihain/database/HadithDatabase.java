package com.mohammed.mosa.eg.sahihain.database;

import android.content.Context;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class HadithDatabase extends SQLiteAssetHelper {
    public static final int DB_VERSION = 1;
    public static final String DATABASE = "hadith.db";

    public HadithDatabase(@Nullable Context context) {
        super(context, DATABASE, null, DB_VERSION);
    }


}

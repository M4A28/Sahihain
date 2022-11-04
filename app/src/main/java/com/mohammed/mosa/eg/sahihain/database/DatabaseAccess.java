package com.mohammed.mosa.eg.sahihain.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mohammed.mosa.eg.sahihain.utilty.FakeHadith;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;

import java.security.SecureRandom;
import java.util.ArrayList;

public class DatabaseAccess {

    public static final String HADITH_TABLE_ID = "id";
    public static final String HADITH_TABLE_SAHIHAIN = "sahihain"; // الصحيحين
    public static final String HADITH_TABLE_FAVOURITE = "favourite"; // المفضلة
    public static final String HADITH_TABLE_FAKE = "hadith_fake"; // الاحاديث المكذوبة
    public static final String HADITH_TABLE_ARBEEN = "arbaeen"; // الاربعين النووية
    public static final String HADITH_TABLE_HADITH = "hadith";
    public static final String HADITH_TABLE_RAWI =  "rawi";
    public static final String HADITH_TABLE_DEGREE = "degree";
    public static final String HADITH_TABLE_MOHAITH = "mohadith";
    public static final String HADITH_TABLE_SOURCE = "source";
    public static final String HADITH_TABLE_TAKHRIJ = "takhrij";
    public static final String HADITH_TABLE_SHARH = "sharh";
    public static final String HADITH_TABLE_UHADITH = "uhadith";

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.sqLiteOpenHelper = new HadithDatabase(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null)
            instance = new DatabaseAccess(context);
        return instance;
    }

    public void open(){
        this.database = this.sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        if(this.database != null)
            this.database.close();
    }

    // to get all count;
    public long getHadithCount(){
        return DatabaseUtils.queryNumEntries(database, HADITH_TABLE_SAHIHAIN);
    }

    public ArrayList<Hadith> getCustomNumberOfHadith(int number){
        ArrayList<Hadith> hadiths = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + HADITH_TABLE_SAHIHAIN, null);
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
                @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
                @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
                @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
                @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
                @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
                @SuppressLint("Range") String fava = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_FAVOURITE));
                Hadith hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_, fava);
                hadiths.add(hadith);
            }while (cursor.moveToNext() && hadiths.size() <= number);
            cursor.close();
        }
        return hadiths;
    }

    public ArrayList<Hadith> getAllHadith(){
        ArrayList<Hadith> hadiths = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + HADITH_TABLE_SAHIHAIN, null);
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
                @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
                @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
                @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
                @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
                @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
                @SuppressLint("Range") String fava = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_FAVOURITE));
                Hadith hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_, fava);
                hadiths.add(hadith);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return hadiths;
    }

    public ArrayList<Hadith>  search(String text){
        String[] values = {"%" + text + "%"};
        String query = "SELECT * FROM " + HADITH_TABLE_SAHIHAIN + " WHERE " + HADITH_TABLE_UHADITH +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Hadith> hadiths = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
                @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
                @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
                @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
                @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
                @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
                @SuppressLint("Range") String fava = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_FAVOURITE));
                Hadith hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_, fava);
                hadiths.add(hadith);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return hadiths;
    }

    public ArrayList<Hadith> getArbaeen(){
        ArrayList<Hadith> hadiths = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + HADITH_TABLE_ARBEEN, null);
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
                @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
                @SuppressLint("Range") String fava = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_FAVOURITE));
                Hadith hadith = new Hadith(id, maten, rawi, "", mohadith, "", "", "", "", fava);
                hadiths.add(hadith);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return hadiths;
    }

    public Hadith randomHadith(){
        SecureRandom random = new SecureRandom();
        int random_h = random.nextInt(12831) + 1;
        String query = "SELECT * FROM " + HADITH_TABLE_SAHIHAIN + " WHERE " + HADITH_TABLE_ID + " = " + random_h ;
        Cursor cursor = database.rawQuery(query  , null);
        Hadith hadith = null;
        cursor.moveToFirst();
        @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
        @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
        @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
        @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
        @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
        @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
        @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
        @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
        @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
        @SuppressLint("Range") String fava = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_FAVOURITE));
        hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_, fava);
        cursor.close();
        return hadith;
    }

    public Hadith getHadithById(int id_){
        String[] values = {""+id_};
        String query = "SELECT * FROM " + HADITH_TABLE_SAHIHAIN + " WHERE " + HADITH_TABLE_ID + " =? " ;
        Cursor cursor = database.rawQuery(query  , values);
        Hadith hadith = null;
        cursor.moveToFirst();
        @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
        @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
        @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
        @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
        @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
        @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
        @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
        @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
        @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
        hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_);
        cursor.close();
        return hadith;
    }

    public boolean addHadithToFavourite(Hadith hadith){
        ContentValues values = new ContentValues();
        values.put(HADITH_TABLE_ID, hadith.getId());
        values.put(HADITH_TABLE_HADITH, hadith.getMaten());
        values.put(HADITH_TABLE_RAWI, hadith.getRawi());
        values.put(HADITH_TABLE_DEGREE, hadith.getDegree());
        values.put(HADITH_TABLE_MOHAITH, hadith.getMohadith());
        values.put(HADITH_TABLE_SOURCE, hadith.getSource());
        values.put(HADITH_TABLE_TAKHRIJ, hadith.getTakhrij());
        values.put(HADITH_TABLE_SHARH, hadith.getSharh());
        values.put(HADITH_TABLE_UHADITH, hadith.getMaten_());
        values.put(HADITH_TABLE_FAVOURITE, "true");
        ContentValues values1 = new ContentValues();
        values1.put(HADITH_TABLE_FAVOURITE, "true");

        long result = database.insert(HADITH_TABLE_FAVOURITE, null, values);

        int u = database.update(HADITH_TABLE_SAHIHAIN,
                values1,
                "id = ?",
                new String[]{hadith.getId()+""});

        return result != -1;
    }
// this for debuging
    public Hadith getFavouriteHadithById(int id_){
        String[] values = {""+id_};
        String query = "SELECT * FROM " + HADITH_TABLE_FAVOURITE + " WHERE " + HADITH_TABLE_ID + " =? " ;
        Cursor cursor = database.rawQuery(query  , values);
        Hadith hadith = null;
        cursor.moveToFirst();
        @SuppressLint("Range") int  id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
        @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
        @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
        @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
        @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
        @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
        @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
        @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
        @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
        hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_ );
        cursor.close();
        return hadith;
    }

    public void removeFromFavaert(Hadith hadith){
        String[] id = {hadith.getId()+""};
        ContentValues values1 = new ContentValues();
        values1.put(HADITH_TABLE_FAVOURITE, "false");
        database.update(HADITH_TABLE_SAHIHAIN,
                values1,
                "id = ?",
                id);
        database.delete(HADITH_TABLE_FAVOURITE,
                "id = ?",
                id);
    }

    public ArrayList<Hadith> getAllFavert(){
        ArrayList<Hadith> hadiths = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + HADITH_TABLE_FAVOURITE, null);

        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String rawi = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_RAWI));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
                @SuppressLint("Range") String mohadith = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_MOHAITH));
                @SuppressLint("Range") String source = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SOURCE));
                @SuppressLint("Range") String takhrij = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_TAKHRIJ));
                @SuppressLint("Range") String sharh = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_SHARH));
                @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
                @SuppressLint("Range") String fava = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_FAVOURITE));
                Hadith hadith = new Hadith(id, maten, rawi, degree, mohadith, source, takhrij, sharh, maten_, fava);
                hadiths.add(hadith);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return hadiths;
    }

    public ArrayList<FakeHadith> getAllFake(){
        ArrayList<FakeHadith> fakeHadiths = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + HADITH_TABLE_FAKE, null);

        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
                @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
                FakeHadith hadith = new FakeHadith(id, maten, degree, maten_);
                fakeHadiths.add(hadith);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return fakeHadiths;
    }

    public ArrayList<FakeHadith> searchFake(String text){
        String[] values = {"%" + text + "%"};
        String query = "SELECT * FROM " + HADITH_TABLE_FAKE + " WHERE " + HADITH_TABLE_UHADITH +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<FakeHadith> fakeHadiths = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(HADITH_TABLE_ID));
                @SuppressLint("Range") String maten = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_HADITH));
                @SuppressLint("Range") String degree = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_DEGREE));
                @SuppressLint("Range") String maten_ = cursor.getString(cursor.getColumnIndex(HADITH_TABLE_UHADITH));
                FakeHadith hadith = new FakeHadith(id, maten, degree, maten_);
                fakeHadiths.add(hadith);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return fakeHadiths;
    }



}

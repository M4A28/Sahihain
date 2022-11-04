package com.mohammed.mosa.eg.sahihain.utilty;

import android.content.Context;

import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;

public class Hadith {
    public static Hadith hadith;
    private int id;
    private String maten;
    private String rawi;
    private String degree;
    private String mohadith;
    private String source;
    private String takhrij;
    private String sharh;
    private String maten_;
    private String isFavaet;

    public Hadith() {

    }

    public static Hadith getInstance(Context context){
        if(hadith == null ){
            hadith = random(context);
        }
        return hadith;
    }

    public static  Hadith random(Context context){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        Hadith hadith = databaseAccess.randomHadith();
        databaseAccess.close();
        return hadith;
    }
    // الصحيحين فقط
    public Hadith(int id, String maten, String rawi, String degree, String mohadith, String source, String takhrij, String sharh, String maten_) {
        this.id = id;
        this.maten = maten;
        this.rawi = rawi;
        this.degree = degree;
        this.mohadith = mohadith;
        this.source = source;
        this.takhrij = (takhrij == null || takhrij.equals("null"))? "" : takhrij;
        this.sharh = (!isNumber(takhrij)) ? "" : sharh;
        this.maten_ = maten_;
        this.setIsFavaet("false");
    }


    public Hadith(String maten, String rawi, String degree, String mohadith, String source, String takhrij, String sharh, String maten_) {
        this.maten = maten;
        this.rawi = rawi;
        this.degree = degree;
        this.mohadith = mohadith;
        this.source = source;
        this.takhrij = (takhrij == null || takhrij.equals("null"))? "" : takhrij;
        this.sharh = (!isNumber(takhrij)) ? "" : sharh;
        this.maten_ = maten_;
        this.setIsFavaet("false");
    }

    public Hadith(int id, String maten, String rawi, String degree, String mohadith, String source, String takhrij, String sharh, String maten_, String isFavaet) {
        this.id = id;
        this.maten = maten;
        this.rawi = rawi;
        this.degree = degree;
        this.mohadith = mohadith;
        this.source = source;
        this.takhrij = takhrij;
        this.sharh = sharh;
        this.maten_ = maten_;
        this.setIsFavaet(isFavaet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaten() {
        return maten;
    }

    public void setMaten(String maten) {
        this.maten = maten;
    }

    public String getRawi() {
        return rawi;
    }

    public void setRawi(String rawi) {
        this.rawi = rawi;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMohadith() {
        return mohadith;
    }

    public void setMohadith(String mohadith) {
        this.mohadith = mohadith;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTakhrij() {
        if (this.takhrij == null || takhrij.equals("null"))
            return "";
        else return takhrij;

    }

    public void setTakhrij(String takhrij) {
        this.takhrij = takhrij;
    }

    public String getSharh() {
        if(isNumber(sharh)){
            return "";
        }
        else return sharh;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public String getMaten_() {
        return maten_;
    }

    public void setMaten_(String maten_) {
        this.maten_ = maten_;
    }

    public String getIsFavaet() {
        if(isFavaet == null || isFavaet.equals("null"))
            return "false";
        return isFavaet;
    }

    public void setIsFavaet(String isFavaet) {
        this.isFavaet = isFavaet;
    }

    @Override
    public String toString() {
        String result = "'" + maten + '\'' + "\n" +
                " الراوي: " + rawi + " رضي الله عنه " + "\n" +
                "المحدث: الامام " + mohadith + " رحمه الله" + "\n" +
                "المصدر: " + source + "\n" +
                "خلاصة حكم المحدث: " + degree;
        return result;
    }
//
//    public String[] toArray(){
//        String[] data = new String[9];
//        data[0] = ""+id;
//        data[1] = maten;
//        data[2] = rawi;
//        data[3] = degree;
//        data[4] = mohadith;
//        data[5] = source;
//        data[6] = takhrij;
//        data[7] = sharh;
//        data[8] = maten_;
//        return data;
//    }

    public boolean isNumber(String value){
        if(value == null || value.equals("null")){
            return true;
        }
        else{
            try {
                int a = Integer.parseInt(value);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }

    }


}

package com.mohammed.mosa.eg.sahihain.utilty;

public class FakeHadith {

    private int id;
    private String hadith;
    private String degree;
    private String uhadith;

    public FakeHadith() {
    }

    public FakeHadith(int id, String hadith, String degree, String uhadith) {
        this.id = id;
        this.hadith = hadith;
        this.degree = degree;
        this.uhadith = uhadith;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHadith() {
        return hadith;
    }

    public void setHadith(String hadith) {
        this.hadith = hadith;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUhadith() {
        return uhadith;
    }

    public void setUhadith(String uhadith) {
        this.uhadith = uhadith;
    }

    @Override
    public String toString() {
        String result = "'" + hadith + '\'' + "\n" +
                "خلاصة حكم المحدث: " + degree;
        return result;
    }
}

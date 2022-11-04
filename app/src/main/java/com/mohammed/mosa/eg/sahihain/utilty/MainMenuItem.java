package com.mohammed.mosa.eg.sahihain.utilty;

import android.view.View;

public class MainMenuItem {
    private String title;
    private String description;
    private int image;
    private View.OnClickListener listener;

    public MainMenuItem() {

    }

    public MainMenuItem(String title, String description, int image, View.OnClickListener listener) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.listener = listener;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


}

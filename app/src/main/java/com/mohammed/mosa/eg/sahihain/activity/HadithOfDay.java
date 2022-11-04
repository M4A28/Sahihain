package com.mohammed.mosa.eg.sahihain.activity;

import static com.mohammed.mosa.eg.sahihain.R.drawable.ic_star;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;
import com.mohammed.mosa.eg.sahihain.utilty.Values;

import java.io.File;
import java.io.FileOutputStream;

public class HadithOfDay extends AppCompatActivity {
    Hadith hadith;
    TextView tv_maten;
    TextView tv_rawi;
    TextView tv_mohadith;
    TextView tv_source;
    TextView tv_degree;
    TextView tv_tkarej;
    TextView tv_sharh;
    ImageButton btn_fav;
    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_of_day);
        hadith = Hadith.random(this);

        tv_maten = findViewById(R.id.tv_maten);
        tv_rawi = findViewById(R.id.tv_rawi);
        tv_mohadith = findViewById(R.id.tv_mohadth);
        tv_source = findViewById(R.id.tv_source);
        tv_degree = findViewById(R.id.tv_degree);
        tv_tkarej = findViewById(R.id.tv_takhrej);
        tv_sharh = findViewById(R.id.tv_sharh);
        btn_fav = findViewById(R.id.btn_fav);
        tv_maten.setText(hadith.getMaten());
        tv_rawi.setText(" الراوي: " + hadith.getRawi() + " رضي الله عنه ");
        tv_mohadith.setText("المحدث: الامام " + hadith.getMohadith() + " رحمه الله");
        tv_source.setText("المصدر: " + hadith.getSource());
        tv_degree.setText("خلاصة حكم المحدث: " + hadith.getDegree());
        tv_tkarej.setVisibility(hadith.getTakhrij().isEmpty() ? View.GONE:View.VISIBLE);
        tv_tkarej.setText(hadith.getTakhrij());

        if(hadith.getIsFavaet().equals("true"))
            btn_fav.setImageDrawable(this.getDrawable(R.drawable.ic_star));

        tv_sharh.setOnClickListener(K ->{
            if(!hadith.getSharh().isEmpty()){
                tv_sharh.setText("شرح الحديث:"+ hadith.getSharh());
                tv_sharh.setTextColor(getResources().getColor(R.color.black));
            }
            else
                Toast.makeText(this, Values.NO_SHARE_AVAILABLE, Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    public void renew(View view) {
        hadith = Hadith.random(this);
        tv_maten.setText(hadith.getMaten());
        tv_rawi.setText(" الراوي: " + hadith.getRawi());
        tv_mohadith.setText("المحدث: الامام " + hadith.getMohadith() + " رحمه الله");
        tv_source.setText("المصدر: " + hadith.getSource());
        tv_degree.setText("خلاصة حكم المحدث: " + hadith.getDegree());
        tv_tkarej.setText(hadith.getTakhrij());
        tv_tkarej.setVisibility(hadith.getTakhrij().isEmpty() ? View.GONE:View.VISIBLE);
        tv_sharh.setText(Values.SHOW_SHARH);
        tv_sharh.setTextColor(getResources().getColor(R.color.secondary_text));
        if(hadith.getIsFavaet().equals("true"))
            btn_fav.setImageDrawable(this.getDrawable(R.drawable.ic_star));
        else
            btn_fav.setImageDrawable(this.getDrawable(R.drawable.ic_star_holo));
    }

    public void addToFavaert(View view) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        try {
            if(hadith.getIsFavaet().equals("true")){
                databaseAccess.removeFromFavaert(hadith);
                btn_fav.setImageDrawable(this.getDrawable(R.drawable.ic_star_holo));
                hadith.setIsFavaet("false");
                Toast.makeText(this, Values.REMOV_FROM_FAVAERT, Toast.LENGTH_SHORT).show();
            }
            else {
                databaseAccess.addHadithToFavourite(hadith);
                btn_fav.setImageResource(ic_star);
                hadith.setIsFavaet("true");
                Toast.makeText(this, Values.ADD_TO_FAVAERT, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, Values.PRESENT_IN_FAFERT, Toast.LENGTH_SHORT).show();
            databaseAccess.close();
        }
        databaseAccess.close();
    }


    public void genralShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, hadith.toString() + Values.FOOTER);
        startActivity(Intent.createChooser(intent, Values.SHARE_USING));
    }

    public void sendTelegram(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage(Values.TELEGRAM_PACKAGE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, hadith.toString() + Values.FOOTER);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, Values.TELEGRAM_UNAVAILABLE, Toast.LENGTH_SHORT).show();
        }
    }

    public void sendWhatsapp(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage(Values.WHATSAPP_PACKAGE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, hadith.toString() + Values.FOOTER);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, Values.WHATSAPP_UNAVAILABLE, Toast.LENGTH_SHORT).show();
        }
    }

    public void sendTwitter(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage(Values.TWITTER_PACKAGE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, hadith.toString() + Values.FOOTER);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, Values.TWITTER_UNAVAILABLE, Toast.LENGTH_SHORT).show();
        }
    }

    public void sendFacebook(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage(Values.FACEBOOK_PACKAGE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, hadith.toString() + Values.FOOTER);
            startActivity(intent);
            Toast.makeText(this, Values.FACEBOOK_TRUE, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, Values.FACEBOOK_UNAVAILABLE, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, Values.FACEBOOK_TRUE, Toast.LENGTH_SHORT).show();
        }
    }


    public void camera(View view) {
        try{
            View v1 = findViewById(R.id.hadith_of_day);
            Bitmap bitmap = Bitmap.createBitmap(v1.getWidth(),
                    v1.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Drawable bgDrawable = v1.getBackground();

            if (bgDrawable != null) {
                bgDrawable.draw(canvas);
            } else
                canvas.drawColor(Color.TRANSPARENT);

            v1.draw(canvas);
            File file = new File(this.getExternalCacheDir(),"Hadith.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, Values.SHARE_USING));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
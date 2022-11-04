package com.mohammed.mosa.eg.sahihain.adapter;

import static com.mohammed.mosa.eg.sahihain.R.drawable.ic_star;
import static com.mohammed.mosa.eg.sahihain.R.drawable.ic_star_holo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;
import com.mohammed.mosa.eg.sahihain.utilty.Values;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class HadithAdapter extends RecyclerView.Adapter<HadithAdapter.HadithViewHolder> {

    private ArrayList<Hadith> hadiths;
    private Context context;
    public HadithAdapter(ArrayList<Hadith> hadiths, Context context){
        this.hadiths = hadiths;
        this.context = context;
    }

    @NonNull
    @Override
    public HadithViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hadith_layout, parent, false);
        return new HadithViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HadithViewHolder holder, int position) {
        Hadith hadith = hadiths.get(position);
        holder.bind(hadith);
    }

    @Override
    public int getItemCount() {
        return hadiths.size();
    }

    public ArrayList<Hadith> getHadiths() {
        return hadiths;
    }

    public void setHadiths(ArrayList<Hadith> hadiths) {
        this.hadiths = hadiths;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    class HadithViewHolder extends RecyclerView.ViewHolder {
        TextView tv_maten;
        TextView tv_rawi;
        TextView tv_mohadith;
        TextView tv_source;
        TextView tv_degree;
        TextView tv_tkarej;
        TextView tv_sharh;
        TextView tv_more;
        ImageButton btn_facebook;
        ImageButton btn_camera;
        ImageButton btn_twitter;
        ImageButton btn_whatsapp;
        ImageButton btn_telegram;
        ImageButton btn_share;
        ImageButton btn_more_info;
        ImageButton btn_fave;

        public HadithViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maten = itemView.findViewById(R.id.tv_maten);
            tv_rawi = itemView.findViewById(R.id.tv_rawi);
            tv_mohadith = itemView.findViewById(R.id.tv_mohadth);
            tv_source = itemView.findViewById(R.id.tv_source);
            tv_degree = itemView.findViewById(R.id.tv_degree);
            tv_tkarej = itemView.findViewById(R.id.tv_takhrej);
            tv_sharh = itemView.findViewById(R.id.tv_sharh);
            tv_more = itemView.findViewById(R.id.tv_more);
            btn_facebook = itemView.findViewById(R.id.facebook);
            btn_camera = itemView.findViewById(R.id.camera);
            btn_twitter = itemView.findViewById(R.id.twitter);
            btn_whatsapp = itemView.findViewById(R.id.whatsapp);
            btn_telegram = itemView.findViewById(R.id.telegram);
            btn_share = itemView.findViewById(R.id.share_all);
            btn_more_info = itemView.findViewById(R.id.more_info);
            btn_fave = itemView.findViewById(R.id.fafaert);

            btn_camera.setOnClickListener(K -> {
                takeShot(itemView);
            });
        }

        @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
        public void bind(Hadith hadith){
            boolean hadithLength = hadith.getMaten().length() >= 256;
            String hadithString = hadith.toString();

            tv_maten.setText((hadithLength? hadith.getMaten().substring(0, 256) + "...": hadith.getMaten()));
            tv_rawi.setText( " الراوي: " +  hadith.getRawi());
            tv_mohadith.setText( "المحدث: الامام "+ hadith.getMohadith() + " رحمه الله");
            tv_source.setText( "المصدر: " + hadith.getSource() );
            tv_degree.setText("خلاصة حكم المحدث: " + hadith.getDegree());
            if(hadith.getTakhrij().length() == 0
                    && hadith.getSharh().length() == 0
                    && hadith.getSource().length() == 0
                    && hadith.getDegree().length() == 0){
                tv_degree.setVisibility(View.GONE);
                tv_source.setVisibility(View.GONE);
                tv_tkarej.setVisibility(View.GONE);
                tv_mohadith.setVisibility(View.GONE);
            }

            if(hadith.getTakhrij().length() > 0){
                tv_tkarej.setText(hadith.getTakhrij());
                tv_tkarej.setVisibility(View.VISIBLE);
            }
            else
                tv_tkarej.setVisibility(View.GONE);

            tv_sharh.setText("شرح الحديث: "+hadith.getSharh());
            tv_sharh.setVisibility(View.GONE);
            tv_more.setVisibility((hadithLength? View.VISIBLE: View.GONE));

            tv_more.setOnClickListener(K ->{
                tv_maten.setText(hadith.getMaten());
                tv_more.setVisibility(View.GONE);
            });

            if(hadith.getIsFavaet().equals("true"))
                btn_fave.setImageResource(ic_star);
            else
                btn_fave.setImageResource(ic_star_holo);

            btn_fave.setOnClickListener(K ->{
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.open();
                try {

                    if(hadith.getIsFavaet().equals("true")){
                        databaseAccess.removeFromFavaert(hadith);
                        btn_fave.setImageDrawable(context.getDrawable(R.drawable.ic_star_holo));
                        hadith.setIsFavaet("false");
                        Toast.makeText(context, Values.REMOV_FROM_FAVAERT, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        databaseAccess.addHadithToFavourite(hadith);
                        btn_fave.setImageResource(ic_star);
                        hadith.setIsFavaet("true");
                        Toast.makeText(context, Values.ADD_TO_FAVAERT, Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(context, Values.PRESENT_IN_FAFERT, Toast.LENGTH_SHORT).show();
                }
                databaseAccess.close();
            });


            btn_more_info.setOnClickListener(K->{
                if(!hadith.getSharh().isEmpty())
                    tv_sharh.setVisibility(View.VISIBLE);
                else
                    Toast.makeText(context, Values.NO_SHARE_AVAILABLE, Toast.LENGTH_SHORT).show();
            });

            btn_share.setOnClickListener(K ->{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, hadithString + Values.FOOTER);
                context.startActivity(Intent.createChooser(intent, Values.SHARE_USING));
            });

            btn_twitter.setOnClickListener(K ->{
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setPackage(Values.TWITTER_PACKAGE);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, hadithString + Values.FOOTER);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, Values.TWITTER_UNAVAILABLE, Toast.LENGTH_SHORT).show();
                }
            });

            btn_facebook.setOnClickListener(K ->{
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setPackage(Values.FACEBOOK_PACKAGE);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, hadithString + Values.FOOTER);
                    Toast.makeText(context,  Values.FACEBOOK_TRUE, Toast.LENGTH_LONG).show();
                    context.startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(context, Values.FACEBOOK_UNAVAILABLE, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, Values.FACEBOOK_TRUE, Toast.LENGTH_SHORT).show();
                }
            });

            btn_telegram.setOnClickListener(K ->{
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setPackage(Values.TELEGRAM_PACKAGE);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, hadithString + Values.FOOTER);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, Values.TELEGRAM_UNAVAILABLE, Toast.LENGTH_SHORT).show();
                }
            });

            btn_whatsapp.setOnClickListener(k ->{
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setPackage(Values.WHATSAPP_PACKAGE);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, hadithString + Values.FOOTER);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, Values.WHATSAPP_UNAVAILABLE, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void takeShot(View itemView){
            Bitmap bitmap = Bitmap.createBitmap(itemView.getWidth(),
                    itemView.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Drawable bgDrawable = itemView.getBackground();
            if (bgDrawable != null) {
                bgDrawable.draw(canvas);
            } else {
                canvas.drawColor(Color.TRANSPARENT);
            }
            itemView.draw(canvas);
            try{
                File file = new File(context.getExternalCacheDir(), "Hadith.png");
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("image/png");
                context.startActivity(Intent.createChooser(intent, Values.SHARE_USING));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

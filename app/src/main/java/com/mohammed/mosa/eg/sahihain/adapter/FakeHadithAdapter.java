package com.mohammed.mosa.eg.sahihain.adapter;

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
import com.mohammed.mosa.eg.sahihain.utilty.FakeHadith;
import com.mohammed.mosa.eg.sahihain.utilty.Values;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FakeHadithAdapter extends RecyclerView.Adapter<FakeHadithAdapter.FakeHolder> {

    private ArrayList<FakeHadith> fakeHadithArrayList;
    private Context context;

    public FakeHadithAdapter(ArrayList<FakeHadith> fakeHadithArrayList, Context context) {
        this.fakeHadithArrayList = fakeHadithArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FakeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fake_hadith_layout, parent, false);
        return new FakeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeHolder holder, int position) {
        FakeHadith fakeHadith = fakeHadithArrayList.get(position);
        holder.bind(fakeHadith);
    }

    @Override
    public int getItemCount() {
        return fakeHadithArrayList.size();
    }
    public void setFakeHadithArrayList(ArrayList<FakeHadith> hadiths) {
        this.fakeHadithArrayList = hadiths;
        notifyDataSetChanged();
    }

    public class FakeHolder extends RecyclerView.ViewHolder {
        TextView tv_maten;
        TextView tv_degree;
        TextView tv_more;
        ImageButton btn_facebook;
        ImageButton btn_camera;
        ImageButton btn_twitter;
        ImageButton btn_whatsapp;
        ImageButton btn_telegram;
        ImageButton btn_share;

        public FakeHolder(@NonNull View itemView) {
            super(itemView);
            tv_maten = itemView.findViewById(R.id.tv_maten);
            tv_degree = itemView.findViewById(R.id.tv_degree);
            tv_more = itemView.findViewById(R.id.tv_more);
            btn_facebook = itemView.findViewById(R.id.facebook);
            btn_camera = itemView.findViewById(R.id.camera);
            btn_twitter = itemView.findViewById(R.id.twitter);
            btn_whatsapp = itemView.findViewById(R.id.whatsapp);
            btn_telegram = itemView.findViewById(R.id.telegram);
            btn_share = itemView.findViewById(R.id.share_all);
            btn_camera.setOnClickListener(K -> takeShot(itemView));
        }

        @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
        public void bind(FakeHadith fakeHadith){
            boolean hadithLength = fakeHadith.getHadith().length() >= 256;
            String hadithString = fakeHadith.toString();
            tv_maten.setText((hadithLength? fakeHadith.getHadith().substring(0, 256) + "...": fakeHadith.getHadith()));
            tv_degree.setText("الحكم: " + fakeHadith.getDegree()  );
            tv_more.setVisibility((hadithLength? View.VISIBLE: View.GONE));
            tv_more.setOnClickListener(K ->{
                tv_maten.setText(fakeHadith.getHadith());
                tv_more.setVisibility(View.GONE);
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

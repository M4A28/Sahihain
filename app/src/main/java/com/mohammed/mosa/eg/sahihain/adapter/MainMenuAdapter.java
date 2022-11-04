package com.mohammed.mosa.eg.sahihain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.utilty.MainMenuItem;

import java.util.ArrayList;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MenuHolder> {
    private ArrayList<MainMenuItem> mainMenuItems;
    private Context context;

    public MainMenuAdapter(ArrayList<MainMenuItem> mainMenuItems, Context context) {
        this.mainMenuItems = mainMenuItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MainMenuAdapter.MenuHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        MainMenuItem menuItem = mainMenuItems.get(position);
        holder.bind(menuItem);
    }

    @Override
    public int getItemCount() {
        return mainMenuItems.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_description;
        ImageView iv_side_image;
        CardView cardView;

        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            iv_side_image = itemView.findViewById(R.id.iv_side_image);
            cardView = itemView.findViewById(R.id.main_card);
        }

        public void bind(MainMenuItem menuItem){
            tv_title.setText(menuItem.getTitle());
            tv_description.setText(menuItem.getDescription());
            iv_side_image.setImageDrawable(context.getDrawable(menuItem.getImage()));
            cardView.setOnClickListener(menuItem.getListener());

        }
    }

}

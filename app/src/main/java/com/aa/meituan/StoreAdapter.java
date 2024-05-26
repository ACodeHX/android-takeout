package com.aa.meituan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private List<StoreMinute> itemList;

    public StoreAdapter(List<StoreMinute> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreMinute item = itemList.get(position);
        holder.store_name.setText(item.getStoreName());
        holder.store_price.setText(item.getSubtitle());
        holder.image.setImageResource(item.getStoreImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView store_name;
        public TextView store_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.store_img);
            store_name = itemView.findViewById(R.id.store_name);
            store_price = itemView.findViewById(R.id.store_price);
        }
    }
}

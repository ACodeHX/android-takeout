package com.aa.meituan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private List<StoreMinute> itemList;
    //接受List<StoreMinute>,初始化itemList
    public StoreAdapter(List<StoreMinute> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storeitem, parent, false);
        return new MyViewHolder(view);
    }
    //绑定数据到视图
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreMinute item = itemList.get(position);
        holder.store_name.setText(item.getStoreName());
        holder.store_price.setText(item.getSubtitle());
        holder.image.setImageResource(item.getStoreImage());
        holder.evaluate.setText(item.getStoreEvaluate());
        holder.deliveryTime.setText(item.getDeliveryTime());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView store_name;
        public TextView store_price;
        public TextView evaluate;
        public TextView deliveryTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.store_img);
            store_name = itemView.findViewById(R.id.store_name);
            store_price = itemView.findViewById(R.id.store_price);
            evaluate = itemView.findViewById(R.id.store_evaluate);
            deliveryTime = itemView.findViewById(R.id.deliveryTime);
        }
    }
}

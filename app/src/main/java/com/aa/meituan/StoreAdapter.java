package com.aa.meituan;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.aa.meituan.StoreMinute;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private List<StoreMinute> itemList;
    private Context context;

    public StoreAdapter(Context context, List<StoreMinute> itemList) {
        this.context = context;     //访问应用程序的环境
        this.itemList = itemList;   //显示RecyclerView的数据列表
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storeitem, parent, false);    //加载布局文件
        return new MyViewHolder(view);
    }
    //绑定数据到视图
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreMinute item = itemList.get(position);
        holder.storeID.setText(item.getStoreID());
        holder.store_name.setText(item.getStoreName());
        holder.store_price.setText(item.getSubtitle());
        //获取图像
        holder.image.setImageResource(context.getResources().getIdentifier(item.getStoreImage(), "drawable", context.getPackageName()));
        holder.evaluate.setText(item.getStoreEvaluate());
        holder.deliveryTime.setText(item.getDeliveryTime());
        holder.deliverFee.setText("配送费:" + String.valueOf(item.getDeliveryFee()) + "元");

        //将storeID隐藏
        holder.storeID.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将store的一些数据传值到TakeOut
                String storeID = item.getStoreID();
                String storeName = item.getStoreName();
                String storeImage = item.getStoreImage();
                String storeEvaluate = item.getStoreEvaluate();
                String deliveryTime = item.getDeliveryTime();
                int deliveryFee = item.getDeliveryFee();
                Intent intent = new Intent(context, TakeOut.class);
                intent.putExtra("STORE_ID", storeID);
                intent.putExtra("DELIVERY", deliveryFee);
                intent.putExtra("STORENAME", storeName);
                intent.putExtra("STOREIMAGE", storeImage);
                intent.putExtra("STOREEVALUATE", storeEvaluate);
                intent.putExtra("DELIVERYTIME", deliveryTime);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView storeID;
        public ImageView image;
        public TextView store_name;
        public TextView store_price;
        public TextView evaluate;
        public TextView deliveryTime;
        public TextView deliverFee;


        public MyViewHolder(View itemView) {
            super(itemView);
            storeID = itemView.findViewById(R.id.storeID);
            image = itemView.findViewById(R.id.store_img);
            store_name = itemView.findViewById(R.id.store_name);
            store_price = itemView.findViewById(R.id.store_price);
            evaluate = itemView.findViewById(R.id.store_evaluate);
            deliveryTime = itemView.findViewById(R.id.deliveryTime);
            deliverFee = itemView.findViewById(R.id.deliveryFee);
        }
    }
}

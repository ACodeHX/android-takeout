package com.aa.takeout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdPagerAdapter extends RecyclerView.Adapter<AdPagerAdapter.AdViewHolder> {
    private List<String> adImageUrls; // 广告图片URL列表

    public AdPagerAdapter(List<String> adImageUrls) {
        this.adImageUrls = adImageUrls;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item, parent, false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        // 加载广告图片
        loadImage(holder.adImage, adImageUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return adImageUrls.size();
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        public ImageView adImage;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            adImage = itemView.findViewById(R.id.adImage);
        }
    }

    // 实现图片加载逻辑的 loadImage 方法
    private void loadImage(ImageView adImage, String s) {
    }
}
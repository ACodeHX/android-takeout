package com.aa.meituan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PayCarAdapter extends RecyclerView.Adapter<PayCarAdapter.CartViewHolder> {
    private List<TakeOutValue> carItems;
    private Context context;

    public PayCarAdapter(List<TakeOutValue> carItems, Context context) {
        this.carItems = carItems != null ? carItems : new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payitem, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TakeOutValue item = carItems.get(position);
        holder.foodName.setText(item.getName());
        holder.foodPrice.setText(String.format("$%.2f", item.getPrice()));
        holder.foodCount.setText(String.valueOf(item.getQuantity()));

        String imageName = item.getImage();
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.foodImage.setImageResource(imageResId);

        if (position >= 0 && position < carItems.size()) {
            TakeOutValue item1 = carItems.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return carItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView foodPrice;
        TextView foodCount;
        ImageView foodImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.cart_food_name);
            foodPrice = itemView.findViewById(R.id.cart_food_price);
            foodCount = itemView.findViewById(R.id.cart_food_count);
            foodImage = itemView.findViewById(R.id.cart_food_image);
        }
    }
}

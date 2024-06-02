package com.aa.meituan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PayCarAdapter extends RecyclerView.Adapter<PayCarAdapter.CartViewHolder> {
    private List<TakeOutValue> cartItems;

    public PayCarAdapter(List<TakeOutValue> cartItems) {
        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paylist, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TakeOutValue item = cartItems.get(position);
        holder.foodName.setText(item.getName());
        holder.foodPrice.setText(String.format("$%.2f", item.getPrice()));
        holder.foodCount.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
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

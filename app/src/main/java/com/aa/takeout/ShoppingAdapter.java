package com.aa.takeout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder> {
    private List<TakeOutValue> selectedItems;
    private Context context;

    public ShoppingAdapter(List<TakeOutValue> selectedItems, Context context) {
        this.selectedItems = selectedItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingitem, parent, false);
        return new ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {
        TakeOutValue item = selectedItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(String.format("$%.2f", item.getPrice()));
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

        holder.minusValue.setOnClickListener(v -> {
            int quantity = item.getQuantity();
            if (quantity > 0) {
                item.setQuantity(quantity - 1);
                notifyDataSetChanged();
                double price = item.getPrice();
                ((TakeOut) context).updateTotalPrice(-price);
            }
        });

        holder.addValue.setImageResource(R.drawable.car_add);
        holder.addValue.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyDataSetChanged();
            double price = item.getPrice();
            ((TakeOut) context).updateTotalPrice(price);
        });
    }

    @Override
    public int getItemCount() {
        return selectedItems.size();
    }

    static class ShoppingViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView addValue, minusValue;

        ShoppingViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.shopping_item_name);
            priceTextView = itemView.findViewById(R.id.shopping_item_price);
            quantityTextView = itemView.findViewById(R.id.shopping_item_quantity);
            addValue = itemView.findViewById(R.id.addvalue);
            minusValue = itemView.findViewById(R.id.minusvalue);
        }
    }
}

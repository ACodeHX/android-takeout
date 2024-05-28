package com.aa.meituan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class TakeOutAdapter extends RecyclerView.Adapter<TakeOutAdapter.MealViewHolder> {
    private List<TakeOutValue> takeOutValues;
    private Context context;

    public TakeOutAdapter(List<TakeOutValue> takeOutValues) {
        this.takeOutValues = takeOutValues;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.take_out_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        TakeOutValue takeOutValue = takeOutValues.get(position);
        holder.nameTextView.setText(takeOutValue.getName());
        holder.priceTextView.setText(String.format("$%.2f", takeOutValue.getPrice()));
        holder.quantityTextView.setText(String.valueOf(takeOutValue.getQuantity()));


    }

    @Override
    public int getItemCount() {
        return takeOutValues.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;

        MealViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.meal_name);
            priceTextView = itemView.findViewById(R.id.meal_price);
            quantityTextView = itemView.findViewById(R.id.meal_quantity);
        }
    }
}

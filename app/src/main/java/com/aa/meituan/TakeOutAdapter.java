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

    public TakeOutAdapter(List<TakeOutValue> takeOutValues, Context context) {
        this.takeOutValues = takeOutValues;
        this.context = context;
    }

    @NonNull
    @Override
    //这个变量名重构一下
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

        // 使用 Glide 加载图像
        Glide.with(context).load(takeOutValue.getImageUrl()).into(holder.imageView);

        holder.addButton.setOnClickListener(v -> {
            // 处理加入购物车逻辑
            // 例如更新数量，通知适配器数据集变化等
            takeOutValue.setQuantity(takeOutValue.getQuantity() + 1);
            notifyItemChanged(position);
            // 如果需要更新总价，可以调用 updateTotalPrice()
        });
    }

    @Override
    public int getItemCount() {
        return takeOutValues.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView imageView;
        Button addButton;

        MealViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.meal_name);
            priceTextView = itemView.findViewById(R.id.meal_price);
            quantityTextView = itemView.findViewById(R.id.meal_quantity);
            imageView = itemView.findViewById(R.id.meal_image);
            addButton = itemView.findViewById(R.id.meal_add_button);
        }
    }
}

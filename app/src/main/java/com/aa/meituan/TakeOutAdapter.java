package com.aa.meituan;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.takeoutitem, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        TakeOutValue takeOutValue = takeOutValues.get(position);
        holder.nameTextView.setText(takeOutValue.getName());
        holder.priceTextView.setText(String.format("$%.2f", takeOutValue.getPrice()));
        holder.quantityTextView.setText(String.valueOf(takeOutValue.getQuantity()));

        // 使用资源 ID 加载图像
        int imageResId = getImageResourceId(takeOutValue.getImage());
        holder.imageView.setImageResource(imageResId);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog(imageResId);
            }
        });

        holder.addButton.setOnClickListener(v -> {
            // 处理加入购物车逻辑
            // 例如更新数量，通知适配器数据集变化等
            takeOutValue.setQuantity(takeOutValue.getQuantity() + 1);
            double priceToAdd = takeOutValue.getPrice();
            //((TakeOut) context).totalPrice += priceToAdd;
            ((TakeOut) context).updateTotalPrice(takeOutValue.getPrice());
            //updateTotalPrice(totalPrice);
            notifyItemChanged(position);
            // 如果需要更新总价，可以调用 updateTotalPrice()
        });
    }

    private void showImageDialog(int imageResId) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.showtakeoutimage);
        ImageView largeImageView = dialog.findViewById(R.id.meal_image);
        largeImageView.setImageResource(imageResId);
        dialog.show();
    }

    //购买商品的数量
    @Override
    public int getItemCount() {
        return takeOutValues.size();
    }

    //根据图片名称获取id
    private int getImageResourceId(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView imageView;
        Button addButton;

        //绑定数据
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

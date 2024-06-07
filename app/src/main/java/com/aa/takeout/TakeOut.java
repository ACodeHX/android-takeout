package com.aa.takeout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TakeOut extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView backStore;
    private Button judgePay;
    private Button payMinute;
    private double totalPrice = 0.0; // 设置总价
    private List<TakeOutValue> takeOutValueList;
    private List<TakeOutValue> filteredList;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeout2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //返回按钮
        backStore = findViewById(R.id.backstore);
        backStore.setImageResource(R.drawable.back_takeout);
        backStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        takeOutValueList = new ArrayList<>();       //存储所有的外卖菜品数据
        filteredList = new ArrayList<>();           //用来存储经过筛选的外卖菜品数据
        //获得Store传过来的storeID值
        Intent intent = getIntent();
        String storeId = intent.getStringExtra("STORE_ID");
        String storeName = intent.getStringExtra("STORENAME");
        String storeImage = intent.getStringExtra("STOREIMAGE");
        String storeEvaluate = intent.getStringExtra("STOREEVALUATE");
        String deliveryTime = intent.getStringExtra("DELIVERYTIME");

        TextView storeNameText = findViewById(R.id.store_name);
        ImageView storeImageText = findViewById(R.id.store_img);
        TextView storeEvaluateText = findViewById(R.id.store_evaluate);
        TextView deliveryTimeText = findViewById(R.id.deliveryTime);
        storeNameText.setText(storeName);
        int imageInt = getImageResourceId(storeImage);//将图片转为int
        storeImageText.setImageResource(imageInt);
        storeEvaluateText.setText(storeEvaluate);
        deliveryTimeText.setText(deliveryTime);

        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList, this);
        recyclerView.setAdapter(adapter);

        // 加载 JSON 的storeId的数据
        loadMealsFromJson(storeId);
        for (TakeOutValue value : takeOutValueList) {
            if (value.getFoodID() == storeId) {     //比较两个id的值
                filteredList.add(value);
            }
        }
        // 更新适配器数据
        adapter.notifyDataSetChanged();

        //购物详细
        payMinute = findViewById(R.id.select_store);
        payMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        //跳转支付界面
        judgePay = findViewById(R.id.payFood);
        judgePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TakeOutValue> filteredPayItem = new ArrayList<>();
                for (TakeOutValue item : takeOutValueList) {
                    // 只有当数量大于0时才添加到filteredPayItem列表
                    if (item.getQuantity() > 0) {
                        filteredPayItem.add(item);
                    }
                }

                if (!filteredPayItem.isEmpty()) { // 检查filteredPayItem是否为空
                    Intent intent = new Intent(TakeOut.this, Pay.class);
                    intent.putExtra("totalPrice", totalPrice);
                    int deliveryFee = getIntent().getIntExtra("DELIVERY", 0);
                    intent.putExtra("DELIVERY", deliveryFee);

                    // 将购物车数据转换为json字符串
                    Gson gson = new Gson();
                    String carJson = gson.toJson(filteredPayItem);
                    intent.putExtra("carItems", carJson);   //将carItems传到Pay
                    startActivity(intent);
                } else {
                    Toast.makeText(TakeOut.this, "购物车为空,请先选择菜品", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //底部出弹窗
    private void showBottomSheetDialog() {
            // 获取当前上下文
            final Context context = this;

            // 创建一个新的 BottomSheetDialog 对象
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

            // 从布局文件 shoppinglist.xml 中膨胀出一个视图对象
            final View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.shoppinglist, null);

            // 获取清空按钮的引用
            final Button clearButton = bottomSheetView.findViewById(R.id.clear);

            // 获取 RecyclerView 的引用，并设置其布局管理器为 LinearLayoutManager
            RecyclerView recyclerView = bottomSheetView.findViewById(R.id.shopping_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // 创建并设置适配器，以显示经过过滤的商品列表
            ShoppingAdapter adapter = new ShoppingAdapter(filteredList, this);
            recyclerView.setAdapter(adapter);

            // 为清空按钮设置点击监听器
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 清空 filteredList 中每个商品的数量
                    for (TakeOutValue item : filteredList) {
                        item.setQuantity(0);
                    }
                    // 通知适配器数据已更改，以刷新 RecyclerView
                    adapter.notifyDataSetChanged();
                }
            });

            // 设置 BottomSheetDialog 的内容视图为上面膨胀出的视图
            bottomSheetDialog.setContentView(bottomSheetView);

            // 显示 BottomSheetDialog
            bottomSheetDialog.show();
        }



    private void loadMealsFromJson(String storeId) {
        if (storeId != null) { // 添加空引用检查
            try {
                InputStream is = getResources().openRawResource(R.raw.takeout);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                is.close();
                String json = sb.toString();

                Gson gson = new Gson();
                Type mealListType = new TypeToken<List<TakeOutValue>>() {}.getType();
                List<TakeOutValue> meals = gson.fromJson(json, mealListType);

                if (meals != null) {
                    takeOutValueList.addAll(meals);
                    filteredList.clear();
                    //过滤列表
                    for (TakeOutValue value : takeOutValueList) {
                        if (storeId.equals(value.getFoodID())) { // 修正此处比较的顺序
                            filteredList.add(value);
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            TakeOutAdapter adapter = new TakeOutAdapter(filteredList, this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    //根据图片名称获取图片ID
    private int getImageResourceId(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
    //更新总价
    public void updateTotalPrice(double price) {
        totalPrice += price;

        Button selectStore = findViewById(R.id.select_store);
        judgePay.setText("去结算");
        selectStore.setText("店铺详细,小计 (" + String.format("%.2f", totalPrice) + "元)"); // 更新支付按钮上的文本显示
    }
}

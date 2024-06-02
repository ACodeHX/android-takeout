package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private TextView totalPriceTextView;
    private ImageView backStore;
    private Button judgePay;
    private Button selectStore;
    private double totalPrice = 0.0; // 跟踪总价
    private List<TakeOutValue> takeOutValueList;
    private List<TakeOutValue> filteredList;

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
        takeOutValueList = new ArrayList<>();
        filteredList = new ArrayList<>();

        Intent intent = getIntent();
        String storeId = intent.getStringExtra("STORE_ID");
        Log.d("DEBUG", "Received storeID: " + storeId);

        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList, this);//未修改
        recyclerView.setAdapter(adapter);

        // 加载 JSON 数据
        loadMealsFromJson(storeId);
        for (TakeOutValue value : takeOutValueList) {
            if (value.getFoodID() == storeId) {
                filteredList.add(value);
            }
        }
        // 更新适配器数据
        adapter.notifyDataSetChanged();

        judgePay = findViewById(R.id.payFood);
        judgePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalPrice > 0) {
                    Intent intent = new Intent(TakeOut.this, Pay.class);
                    intent.putExtra("totalPrice", totalPrice);
                    startActivity(intent);
                } else {
                    Toast.makeText(TakeOut.this, "购物车为空,请先选择菜品", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    private int getImageResourceId(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
    public void updateTotalPrice(double price) {
        totalPrice += price;

        Button selectStore = findViewById(R.id.select_store);
        judgePay.setText("去结算");
        selectStore.setText("支付 (" + String.format("%.2f", totalPrice) + "元)"); // 更新支付按钮上的文本显示
    }
}

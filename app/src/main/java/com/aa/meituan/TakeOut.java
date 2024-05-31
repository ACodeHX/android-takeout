package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    private Button judgePay;
    private double totalPrice = 0.0; // 跟踪总价
    private List<TakeOutValue> takeOutValueList;
    private List<TakeOutValue> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_out2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        takeOutValueList = new ArrayList<>();
        filteredList = new ArrayList<>();
        Intent intent = getIntent();
        int storeId = intent.getIntExtra("STORE_ID", -1);

        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList, this);//未修改
        recyclerView.setAdapter(adapter);

        // 加载 JSON 数据
        loadMealsFromJson(storeId);

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

    private void loadMealsFromJson(int storeId) {
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
                //过滤列表
                for (TakeOutValue value : takeOutValueList) {
                    if (value.getID() == storeId) {
                        filteredList.add(value);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int getImageResourceId(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
    public void updateTotalPrice(double price) {
        totalPrice += price;
        judgePay.setText("支付 (" + String.format("%.2f", totalPrice) + "元)"); // 更新支付按钮上的文本显示
    }
}

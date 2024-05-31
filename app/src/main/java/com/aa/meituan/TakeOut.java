package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private List<TakeOutValue> takeOutValueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_out2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        takeOutValueList = new ArrayList<>();

        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList, this);
        recyclerView.setAdapter(adapter);

        // 加载 JSON 数据
        loadMealsFromJson();

        // 更新适配器数据
        adapter.notifyDataSetChanged();

        judgePay = findViewById(R.id.payFood);
        judgePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakeOut.this, Pay.class);
                startActivity(intent);
            }
        });
    }

    private void loadMealsFromJson() {
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
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int getImageResourceId(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
}

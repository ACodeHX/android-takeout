package com.aa.meituan;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TakeOut extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView totalPriceTextView;
    private Button checkoutButton;
    private TextView footerTextView;
    private Button footerButton;
    private List<TakeOutValue> takeOutValueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_out2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageView storeImage = findViewById(R.id.store_img1);
        int i = R.drawable.telegram;
        takeOutValueList = new ArrayList<>();

        // 添加商店信息
        takeOutValueList.add(new TakeOutValue("ks", 5.0, 5, "telegram", i));

        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList, this);
        recyclerView.setAdapter(adapter);
    }


    private void loadMealsFromJson() {
        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open("takeout.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

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

}

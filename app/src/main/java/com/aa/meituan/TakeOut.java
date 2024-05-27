package com.aa.meituan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class TakeOut extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView totalPriceTextView;
    private Button checkoutButton;
    private List<TakeOutValue> takeOutValueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_out2);

        recyclerView = findViewById(R.id.recycler_view);
        totalPriceTextView = findViewById(R.id.total_price);
        checkoutButton = findViewById(R.id.checkout_button);

        takeOutValueList = loadMealsFromJson();
        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateTotalPrice();
    }

    private List<TakeOutValue> loadMealsFromJson() {
        String json = null;
        try {
            InputStream is = getAssets().open("meals.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Gson gson = new Gson();
        Type mealListType = new TypeToken<List<TakeOutValue>>() {}.getType();
        return gson.fromJson(json, mealListType);
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (TakeOutValue takeOutValue : takeOutValueList) {
            totalPrice += takeOutValue.getPrice() * takeOutValue.getQuantity();
        }
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }
}

package com.aa.meituan;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
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
        totalPriceTextView = findViewById(R.id.Store3);
        checkoutButton = findViewById(R.id.checkout_button);
        footerTextView = findViewById(R.id.footer_text);
        footerButton = findViewById(R.id.footer_button);

        takeOutValueList = loadMealsFromJson();

        if (takeOutValueList == null) {
            takeOutValueList = new ArrayList<>();
        }

        TakeOutAdapter adapter = new TakeOutAdapter(takeOutValueList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateFooter();
    }

    private List<TakeOutValue> loadMealsFromJson() {
        String json = null;
        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open("takeout.json");
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

    private void updateFooter() {
        if (takeOutValueList == null || takeOutValueList.isEmpty()) {
            footerTextView.setText("未选商品");
            footerButton.setText("$20起送");
            footerButton.setEnabled(false);
            return;
        }

        double totalPrice = 0;
        for (TakeOutValue takeOutValue : takeOutValueList) {
            totalPrice += takeOutValue.getPrice() * takeOutValue.getQuantity();
        }

        if (totalPrice > 0) {
            footerTextView.setText(String.format("Total: $%.2f\n配送费 $5", totalPrice));
            footerButton.setText("去结算");
            footerButton.setEnabled(true);
        } else {
            footerTextView.setText("未选商品");
            footerButton.setText("$20起送");
            footerButton.setEnabled(false);
        }
    }
}

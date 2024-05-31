package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class Store extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;
    private List<StoreMinute> itemList;
    private TextView showStore;
    private ImageView showImage;
    private Button skipTakeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        showStore = findViewById(R.id.textView2);
        showStore.setText("店铺");
        showImage = findViewById(R.id.imageView2);
        showImage.setImageResource(R.drawable.microsoft);

        recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();

        skipTakeout = findViewById(R.id.judgetake1);
        skipTakeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int storeId = 1;    //传递id
                Intent intent = new Intent(Store.this, TakeOut.class);
                intent.putExtra("STORE_ID", storeId);
                startActivity(intent);
            }
        });

        // 修正此处调用正确的 JSON 加载方法
        String jsonString = loadJSONFromRaw(R.raw.store);
        if (jsonString != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<StoreMinute>>() {}.getType();
            itemList = gson.fromJson(jsonString, listType);

            if (itemList == null) {
                itemList = new ArrayList<>();
                Log.e("Store", "JSON parsing failed, initializing empty list");
            } else {
                // 更新 itemList 中的 storeImage 字段为资源 ID
                for (StoreMinute item : itemList) {
                    item.setStoreImageId(getImageResourceId(item.getStoreImage()));
                }
            }
        } else {
            Log.e("Store", "Failed to load JSON string, initializing empty list");
            itemList = new ArrayList<>();
        }

        storeAdapter = new StoreAdapter(this, itemList);
        recyclerView.setAdapter(storeAdapter);
    }

    // 从 raw 资源加载 JSON
    private String loadJSONFromRaw(int rawResourceId) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(rawResourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            is.close();
            json = sb.toString();
        } catch (IOException ex) {
            Log.e("Store", "Error reading JSON file from raw resource", ex);
            return null;
        }
        return json;
    }

    // 获取图像资源 ID
    private int getImageResourceId(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
}

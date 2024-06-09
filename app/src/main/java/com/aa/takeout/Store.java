package com.aa.takeout;

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
    private ImageView showImage, backLogin;
    private Button skipTakeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        showStore = findViewById(R.id.textView2);
        showStore.setText("店铺");
        //广告图片
        showImage = findViewById(R.id.imageView2);
        showImage.setImageResource(R.drawable.ad);
        showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Store.this, "这是广告!!!", Toast.LENGTH_SHORT).show();
            }
        });

        //返回登陆
        backLogin = findViewById(R.id.backlogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //设置条目列表布局
        recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();

        //调用loadJSONFromRaw读取store.json的数据，使用Gson解析为itemList
        String jsonString = loadJSONFromRaw(R.raw.store);
        if (jsonString != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<StoreMinute>>() {}.getType();
            itemList = gson.fromJson(jsonString, listType);

            if (itemList != null) {
                // 更新 itemList 中的 storeImage 字段为资源 ID
                for (StoreMinute item : itemList) {
                    item.setStoreImageId(getImageResourceId(item.getStoreImage()));
                }
            } else {
                Log.e("Store", "JSON parsing failed");
            }
        } else {
            Log.e("Store", "Failed to load JSON string");
        }

        storeAdapter = new StoreAdapter(this, itemList);
        recyclerView.setAdapter(storeAdapter);
    }

    // 加载json数据
    private String loadJSONFromRaw(int rawResourceId) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(rawResourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));     //使用UFT-8
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            is.close();
            json = sb.toString();       //返回字符串
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

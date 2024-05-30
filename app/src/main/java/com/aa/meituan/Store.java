package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Store extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;
    private List<StoreMinute> itemList;
    private TextView showStore;
    private ImageView showImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        //获得框架的引用
        //FrameLayout frameLayout = findViewById(R.id.Frame1);
        /*View clickableView = findViewById(R.id.click1);     //点击区域
        clickableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Store.this, TakeOut.class);
                startActivity(intent);
            }
        });*/
        showStore = findViewById(R.id.textView2);
        showStore.setText("店铺");
        showImage = findViewById(R.id.imageView2);
        showImage.setImageResource(R.drawable.microsoft);

        recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();


        itemList.add(new StoreMinute("快乐柠檬", "起送100|配送50", R.drawable.must_buy_two, "好吃", "平日送"));
        itemList.add(new StoreMinute("烤鸭脖", "起送30|配送90", R.drawable.recom_three, "好吃", "平日送"));
        itemList.add(new StoreMinute("烧鸭","40",R.drawable.recom_two, "好吃", "平日送"));
        itemList.add(new StoreMinute("飞机", "起送80|配送99", R.drawable.telegram, "好吃", "平日送"));
        itemList.add(new StoreMinute("炸鸡", "起送100|配送120", R.drawable.twitter, "好吃", "平日送"));
        itemList.add(new StoreMinute("快乐柠檬", "起送100|配送50", R.drawable.must_buy_two, "好吃", "平日送"));
        itemList.add(new StoreMinute("烤鸭脖", "起送30|配送90", R.drawable.recom_three, "好吃", "平日送"));
        itemList.add(new StoreMinute("烧鸭","40",R.drawable.recom_two, "好吃", "平日送"));
        itemList.add(new StoreMinute("飞机", "起送80|配送99", R.drawable.telegram, "好吃", "平日送"));
        itemList.add(new StoreMinute("炸鸡", "起送100|配送120", R.drawable.twitter, "好吃", "平日送"));


        storeAdapter = new StoreAdapter(itemList);
        recyclerView.setAdapter(storeAdapter);
    }
}
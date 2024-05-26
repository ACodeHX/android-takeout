package com.aa.meituan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();

        itemList.add(new StoreMinute("快乐柠檬", "起送100|配送50", R.drawable.must_buy_two, "好吃"));
        itemList.add(new StoreMinute("烤鸭脖", "起送30|配送90", R.drawable.recom_three, "好吃"));
        itemList.add(new StoreMinute("烧鸭","40",R.drawable.recom_two, "好吃"));
        itemList.add(new StoreMinute("飞机", "起送80|配送99", R.drawable.telegram, "好吃"));
        itemList.add(new StoreMinute("炸鸡", "起送100|配送120", R.drawable.twitter, "好吃"));
        itemList.add(new StoreMinute("快乐柠檬", "起送100|配送50", R.drawable.must_buy_two, "好吃"));
        itemList.add(new StoreMinute("烤鸭脖", "起送30|配送90", R.drawable.recom_three, "好吃"));
        itemList.add(new StoreMinute("烧鸭","40",R.drawable.recom_two, "好吃"));
        itemList.add(new StoreMinute("飞机", "起送80|配送99", R.drawable.telegram, "好吃"));
        itemList.add(new StoreMinute("炸鸡", "起送100|配送120", R.drawable.twitter, "好吃"));


        storeAdapter = new StoreAdapter(itemList);
        recyclerView.setAdapter(storeAdapter);
    }
}
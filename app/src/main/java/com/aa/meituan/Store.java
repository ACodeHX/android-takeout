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

        itemList.add(new StoreMinute("美味柠檬", "饮料", R.drawable.must_buy_two));
        itemList.add(new StoreMinute("烤鸭脖", "菜", R.drawable.recom_three));
        itemList.add(new StoreMinute("烧鸭","菜",R.drawable.recom_two));

        storeAdapter = new StoreAdapter(itemList);
        recyclerView.setAdapter(storeAdapter);
    }
}
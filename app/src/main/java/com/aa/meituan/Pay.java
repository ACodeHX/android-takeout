package com.aa.meituan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Pay extends AppCompatActivity {
    private PayCat payCat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        payCat = PayCat.getInstance();

    }
}

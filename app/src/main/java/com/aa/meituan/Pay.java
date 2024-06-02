package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Pay extends AppCompatActivity {
    private PayCat payCat;
    private Button CheckButton;
    private ImageView backTakeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        //设置返回布局
        backTakeOut = findViewById(R.id.backtakeout);
        backTakeOut.setImageResource(R.drawable.back_takeout);
        backTakeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        payCat = PayCat.getInstance();
        CheckButton = findViewById(R.id.Check);
        CheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("支付确认")
                .setMessage("你确定要进行支付吗？")
                .setPositiveButton("确认", (dialog, which) -> {
                    //我想在这里添加图片
                    showImage();
                    Toast.makeText(Pay.this, "支付成功", Toast.LENGTH_SHORT).show();
                    // 添加支付逻辑
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    // 取消按钮点击事件
                    Toast.makeText(Pay.this, "支付取消", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

        // 显示对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.paysucceed);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(layoutParams);

        builder.setView(imageView).setPositiveButton("关闭", (dialog, which) -> dialog.dismiss());
        AlertDialog imageDialog = builder.create();
        imageDialog.show();
    }
}

package com.aa.meituan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Pay extends AppCompatActivity {
    private PayCar payCar;
    private Button CheckButton;
    private ImageView backTakeOut;
    private RecyclerView recyclerView;
    private PayCarAdapter carAdapter;
    private List<TakeOutValue> carItems;

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

        // 初始化购物车数据为一个空列表
        carItems = new ArrayList<>();

        // 获取传递的购物车数据
        Intent intent = getIntent();
        String carJson = intent.getStringExtra("carItems");
        //获取配送费的数据
        Intent intentFee = getIntent();
        int deliveryFee = intentFee.getIntExtra("DELIVERY", 0);
        TextView deliveryfeeText = findViewById(R.id.deliveryfee);
        deliveryfeeText.setText("配送费: " + deliveryFee + "元");

        //显示小结
        double subtotal = intent.getDoubleExtra("totalPrice", 0.0);
        TextView subtotalText = findViewById(R.id.subtotal);
        subtotalText.setText("小计" + String.format("%.2f", subtotal) + "元");

        //显示总价
        double allMoney = deliveryFee + subtotal;
        TextView allMoneyText = findViewById(R.id.allmoney);
        allMoneyText.setText("订单总价: " + String.format("%.2f", allMoney) + "元");

        if (carJson != null && !carJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<TakeOutValue>>() {}.getType();
            List<TakeOutValue> receivedItems = gson.fromJson(carJson, type);

            if (receivedItems != null) {
                carItems.addAll(receivedItems);
                Log.d("DEBUG", "Parsed items: " + carItems);
            } else {
                Log.e("DEBUG", "Failed to parse carJson");
            }
        } else {
            Log.e("DEBUG", "Received empty carJson");
        }


        // 设置 RecyclerView 显示购物车商品
        recyclerView = findViewById(R.id.pay_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carAdapter = new PayCarAdapter(carItems, this);
        recyclerView.setAdapter(carAdapter);

        // 设置支付按钮
        CheckButton = findViewById(R.id.Check);
        CheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        //创建一个对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("支付确认")
                .setMessage("你确定要进行支付吗？")
                .setPositiveButton("确认", (dialog, which) -> {
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

    //显示二维码图片
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

package com.aa.meituan;


import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.sqlcipher.database.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText userET;
    private EditText passET;
    private Button loginBUT;
    private Button regBUT;
    private DatabaseHelper userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);
        userET = findViewById(R.id.user);           //用户
        passET = findViewById(R.id.passwd);         //密码
        loginBUT = findViewById(R.id.login_but);    //登陆按钮
        regBUT = findViewById(R.id.register);       //注册按钮
        userData = new DatabaseHelper(this);
        RecyclerView recyclerView;
        Store storeAdapter;
        // 前往注册的按钮
        regBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        // 登陆按钮
        loginBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValues = userET.getText().toString();
                String passValues = passET.getText().toString();

                if (userData.isUserExists(userValues)) {
                    if (userData.checkUser(userValues, passValues)) {
                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent StoIntent = new Intent(MainActivity.this, Store.class);
                        startActivity(StoIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.aa.meituan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText userET;
    private EditText passET;
    private Button loginBUT;
    private Button regBUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userET = findViewById(R.id.user);           //用户
        passET = findViewById(R.id.passwd);         //密码
        loginBUT = findViewById(R.id.login_but);    //登陆按钮
        regBUT = findViewById(R.id.register);       //注册按钮
    }
}
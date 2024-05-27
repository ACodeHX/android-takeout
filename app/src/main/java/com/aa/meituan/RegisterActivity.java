package com.aa.meituan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText newUser;
    private EditText newPass;
    private Button retLogin;
    private Button register;
    private DatabaseHelper userDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        newUser = findViewById(R.id.newUser);
        newPass = findViewById(R.id.regPass);
        register = findViewById(R.id.regSucceed);
        retLogin = findViewById(R.id.returnLogin);
        userDate = new DatabaseHelper(this);

        //注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = newUser.getText().toString();
                String passVaule = newPass.getText().toString();
                userDate.insertUser(userName,passVaule);
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

        //返回登陆界面
        retLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(retIntent);
            }
        });
    }
}

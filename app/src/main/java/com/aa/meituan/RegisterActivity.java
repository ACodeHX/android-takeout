package com.aa.meituan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private EditText newUser;
    private EditText newPassword;
    private Button signUpLogin;
    private Button register;
    private DatabaseHelper userDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        newUser = findViewById(R.id.newUser);
        newPassword = findViewById(R.id.regPass);
        register = findViewById(R.id.regSucceed);
        signUpLogin = findViewById(R.id.returnLogin);
        userDate = new DatabaseHelper(this);

        //注册用户的按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = newUser.getText().toString();
                String passVaule = newPassword.getText().toString();

                //判断账号和密码是否为空
                if (userName.isEmpty() || passVaule.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请输入账号或密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userDate.insertUser(userName,passVaule)) {
                    saveToFile(userName, passVaule);
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(RegisterActivity.this, "用户已存在",Toast.LENGTH_SHORT).show();
                }

            }
        });

        //返回登陆界面
        signUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        }
    //保存密码和账号到指定文件
    private void saveToFile(String userName, String password) {
        String data = "账号:" + userName + ",密码" + password +  "\n";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data_135.txt", Context.MODE_APPEND);
            fos.write(data.getBytes());     //将数据写入文件
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        // 在此处释放资源，防止内存泄漏
    }
}

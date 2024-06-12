package com.aa.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import net.sqlcipher.database.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText user, password;
    private TextView forgetPasswd, author;
    private Button loginBUT, singUpBut;
    private DatabaseHelper userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);
        user = findViewById(R.id.user);                         //用户
        password = findViewById(R.id.passwd);                   //密码
        forgetPasswd = findViewById(R.id.forgetpasswd);         //忘记密码
        loginBUT = findViewById(R.id.login_but);                //登陆按钮
        singUpBut = findViewById(R.id.register);                //注册按钮
        userData = new DatabaseHelper(this);            //数据库

        //注册按钮
        singUpBut.setOnClickListener(new View.OnClickListener() {
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
                String userValues = user.getText().toString();
                String passValues = password.getText().toString();

                if (userData.isUserExists(userValues)) {
                    if (userData.checkUser(userValues, passValues)) {
                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent StoIntent = new Intent(MainActivity.this, Store.class);
                        startActivity(StoIntent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //忘记密码功能
        forgetPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetPasswd.class);
                startActivity(intent);
            }
        });

        author = findViewById(R.id.author);
        author.setText("@ACodeHX");
    }
}
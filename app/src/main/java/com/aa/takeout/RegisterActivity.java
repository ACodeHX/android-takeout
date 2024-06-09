package com.aa.takeout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    private EditText newUser;
    private EditText newPassword;
    private EditText reEnterPasswd;
    private EditText userEmail;
    private EditText authCode;
    private Button signUpLogin;
    private Button register;
    private Button sendCode;
    private DatabaseHelper userDate;
    private int authCodeValue;
    private long startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        newUser = findViewById(R.id.newUser);
        newPassword = findViewById(R.id.regPass);
        reEnterPasswd = findViewById(R.id.reenterpasswd);
        userEmail = findViewById(R.id.useremail);
        authCode = findViewById(R.id.authcode);
        register = findViewById(R.id.regSucceed);
        signUpLogin = findViewById(R.id.returnLogin);
        sendCode = findViewById(R.id.sendcode);
        userDate = new DatabaseHelper(this);

        //注册用户的按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = newUser.getText().toString();
                String passVaule = newPassword.getText().toString();
                String reEnterPasswdValue = reEnterPasswd.getText().toString();
                String email = userEmail.getText().toString();
                String authcode = authCode.getText().toString();

                //判断账号和密码是否为空
                String judgeValue = userName.isEmpty() ? "请输入账号" :
                                    email.isEmpty() ? "请输入邮箱" :
                                    passVaule.isEmpty() ? "请输入密码" :
                                    reEnterPasswdValue.isEmpty() ? "请再次输入密码" :
                                    authcode.isEmpty() ? "请输入验证码" :
                                    null;


                if (judgeValue != null) {
                    Toast.makeText(RegisterActivity.this, judgeValue, Toast.LENGTH_SHORT).show();
                    return;
                }

                int changeAuthCode;
                changeAuthCode = Integer.parseInt(authcode);
                if (reEnterPasswdValue.equals(passVaule)) {
                    if (userDate.insertUser(userName, passVaule)) {
                        if (authCodeValue == changeAuthCode && isValidCode()) {
                            saveToFile(userName, passVaule);
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(loginIntent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "验证码不正确或验证码以过期", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
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

        //发送短信逻辑
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString();
                String Emailregex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Boolean judgeEmail = false;
                judgeEmail = email != null && email.matches(Emailregex);
                if (judgeEmail) {
                    //设置验证码的逻辑
                    Random random = new Random();
                    authCodeValue = 100000 + random.nextInt(900000);
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("请在5分钟输入你的验证码")
                            .setMessage("您的验证码为: " + authCodeValue)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
                }
            }
        });


        }
    //验证时间
    private boolean isValidCode() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        return elapsedTime <= 300000; // 300 seconds (5 minutes) in milliseconds
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

package com.aa.takeout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ForgetPasswd extends AppCompatActivity {
    private EditText user, email, passwd, repasswd, authCode;
    private Button sendCodeButton, modificationButton, reLogin;
    private int authCodeValue;
    private DatabaseHelper userDate;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.forgetpasswd);

        user = findViewById(R.id.username);
        email = findViewById(R.id.email1);
        passwd = findViewById(R.id.passwd1);
        repasswd = findViewById(R.id.mpasswd);
        authCode = findViewById(R.id.authcode1);
        sendCodeButton = findViewById(R.id.sendcode1);
        modificationButton = findViewById(R.id.modifficationpasswd);
        reLogin = findViewById(R.id.relogin);
        userDate = new DatabaseHelper(this);


        //发送短信
        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailValue = email.getText().toString();
                String Emailregex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Boolean judgeEmail = false;
                judgeEmail = emailValue != null && emailValue.matches(Emailregex);
                if (judgeEmail) {
                    //设置验证码的逻辑
                    Random random = new Random();
                    authCodeValue = 100000 + random.nextInt(900000);
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPasswd.this);
                    builder.setTitle("请在5分钟输入你的验证码")
                            .setMessage("您的验证码为: " + authCodeValue)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                } else if(emailValue.isEmpty()) {
                    Toast.makeText(ForgetPasswd.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgetPasswd.this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //修改密码
        modificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = user.getText().toString();
                String emailValue = email.getText().toString();
                String passValue = passwd.getText().toString();
                String rePassValue = repasswd.getText().toString();
                String codeValue = authCode.getText().toString();

                String judgeValue = userValue.isEmpty() ? "请输入账号":
                                    emailValue.isEmpty() ? "请输入邮箱" :
                                    passValue.isEmpty() ? "请输入密码" :
                                    rePassValue.isEmpty() ? "请再次输入密码" :
                                    codeValue.isEmpty() ? "请输入验证码" :
                                    !passValue.equals(rePassValue) ? "密码不一致" :
                                    authCodeValue != Integer.parseInt(authCode.getText().toString()) ? "验证码不正确" :
                                    !userDate.isUserExists(userValue) ? "用户不存在" :
                                    null;

                //判断输入是否为空
                if (judgeValue != null) {
                    Toast.makeText(ForgetPasswd.this, judgeValue, Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUpdated = userDate.updatePassword(userValue, passValue);
                if (isUpdated) {
                    Toast.makeText(ForgetPasswd.this, "修改成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ForgetPasswd.this, "密码修改失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        //返回按键
        reLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

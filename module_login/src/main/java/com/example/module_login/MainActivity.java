package com.example.module_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.module_login.Util.CountDownTimerUtils;
import com.example.module_login.Util.Internet;
import com.example.module_login.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //viewbinding
    private ActivityMainBinding binding;
    String phoneNumber;
    String getURL;
    private static final String TAG = "MainActivity";
    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //viewbinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());//从xml布局文件中加载试图
        setContentView(binding.getRoot());//并将其设置为activity的内容视图

        //发送验证码点击
        binding.loginVerifycode.sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = binding.loginVerifycode.phoneNumber.getText().toString();
                if(phoneNumber.length() != 11){
                    Toast.makeText(MainActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else{
                    getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/user/code/send","phone",phoneNumber);
                    getURL = Internet.addURLParam(getURL,"mode","0");
                    Log.i(TAG, "请求的url是：" + getURL);
                    Request request = new Request.Builder().get()
                            .url(getURL)
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.i(TAG, "onFailure: 网络请求失败");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    CountDownTimerUtils mCountDown = new CountDownTimerUtils(binding.loginVerifycode.sendCode,60000,1000);
                                    mCountDown.start();
                                }
                            });
                        }
                    });
                }
            }
        });

        //将“还没有账号，点击注册”的颜色和样式进行设置
        String text01 = binding.register.getText().toString();
        SpannableString spannableString01 = new SpannableString(text01);
        //文字的点击事件
        ClickableSpan clickableSpan01 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_register = new Intent(MainActivity.this,register.class);
                intent_register.putExtra("who","2");
                intent_register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_register);
            }
        };
        spannableString01.setSpan(clickableSpan01,6,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString01.setSpan(new ForegroundColorSpan(Color.BLUE),6,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.register.setText(spannableString01);
        binding.register.setMovementMethod(LinkMovementMethod.getInstance());//设置点击事件生效

        //“手机号密码登录”进行样式设置
        String text02 = binding.passwordLogin.getText().toString();
        SpannableString spannableString02 = new SpannableString(text02);
        ClickableSpan clickableSpan02 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //-------------------
                Intent intent_password = new Intent(MainActivity.this, relation_password.class);
                intent_password.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_password);
                Toast.makeText(MainActivity.this,"你点击了手机号密码登录",Toast.LENGTH_SHORT).show();
            }
        };
        spannableString02.setSpan(clickableSpan02,0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString02.setSpan(new ForegroundColorSpan(Color.BLUE),0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.passwordLogin.setText(spannableString02);
        binding.passwordLogin.setMovementMethod(LinkMovementMethod.getInstance());

        //设置什么登录端--默认为家属
        String text03 = binding.loginMode.getText().toString();
        SpannableString spannableString03 = new SpannableString(text03);
        spannableString03.setSpan(new ForegroundColorSpan(Color.BLUE),11,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString03.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_manager = new Intent(MainActivity.this, manager.class);
                intent_manager.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_manager);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.DKGRAY);
                ds.setUnderlineText(false);
            }
        },0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString03.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_staff = new Intent(MainActivity.this, staff.class);
                intent_staff.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_staff);

            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.DKGRAY);
                ds.setUnderlineText(false);
            }
        },6,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString03.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_old = new Intent(MainActivity.this, Old_VerifyCode.class);
                intent_old.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_old);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.DKGRAY);
                ds.setUnderlineText(false);
            }
        },16,18,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.loginMode.setText(spannableString03);
        binding.loginMode.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
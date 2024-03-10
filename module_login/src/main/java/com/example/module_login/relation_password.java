package com.example.module_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.module_login.databinding.ActivityRelationPasswordBinding;

public class relation_password extends AppCompatActivity {

    private static final String TAG = "relation_password";
    //viewbinding
    private ActivityRelationPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_password);

        //viewbinding
        binding = ActivityRelationPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //将“还没有账号，点击注册”的颜色和样式进行设置
        String text01 = binding.register.getText().toString();
        SpannableString spannableString01 = new SpannableString(text01);
        //文字的点击事件
        ClickableSpan clickableSpan01 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_register = new Intent(relation_password.this,register.class);
                intent_register.putExtra("who","2");
                intent_register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_register);
            }
        };
        spannableString01.setSpan(clickableSpan01,6,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString01.setSpan(new ForegroundColorSpan(Color.BLUE),6,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.register.setText(spannableString01);
        binding.register.setMovementMethod(LinkMovementMethod.getInstance());//设置点击事件生效

        //“手机验证码登录”进行样式设置
        String text02 = binding.verifycodeLogin.getText().toString();
        SpannableString spannableString02 = new SpannableString(text02);
        ClickableSpan clickableSpan02 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_password = new Intent(relation_password.this, MainActivity.class);
                startActivity(intent_password);
            }
        };
        spannableString02.setSpan(clickableSpan02,0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString02.setSpan(new ForegroundColorSpan(Color.BLUE),0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.verifycodeLogin.setText(spannableString02);
        binding.verifycodeLogin.setMovementMethod(LinkMovementMethod.getInstance());

        //设置什么登录端--默认为家属
        String text03 = binding.loginMode.getText().toString();
        SpannableString spannableString03 = new SpannableString(text03);
        spannableString03.setSpan(new ForegroundColorSpan(Color.BLUE),11,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString03.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_manager = new Intent(relation_password.this, manager.class);
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
                Intent intent_staff = new Intent(relation_password.this, staff.class);
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
                Intent intent_old = new Intent(relation_password.this, Old_VerifyCode.class);
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
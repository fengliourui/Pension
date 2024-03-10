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

import com.example.module_login.databinding.ActivityStaffBinding;

public class staff extends AppCompatActivity {

    private static final String TAG = "staff";
    //viewbinding
    private ActivityStaffBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        //viewbinding
        binding = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //设置什么端，默认为“管理员”
        String text = binding.loginMode.getText().toString();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),6,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_relation = new Intent(staff.this, MainActivity.class);
                intent_relation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_relation);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.DKGRAY);
                ds.setUnderlineText(false);
            }
        },11,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_staff = new Intent(staff.this, manager.class);
                intent_staff.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_staff);

            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.DKGRAY);
                ds.setUnderlineText(false);
            }
        },0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_old = new Intent(staff.this, Old_VerifyCode.class);
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
        binding.loginMode.setText(spannableString);
        binding.loginMode.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
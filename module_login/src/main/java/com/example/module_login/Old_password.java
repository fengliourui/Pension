package com.example.module_login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.module_login.Util.CheckPhone;
import com.example.module_login.databinding.ActivityOldPasswordBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Old_password extends AppCompatActivity {

    private static final String TAG = "Old_password";
    //viewbinding
    private ActivityOldPasswordBinding binding;

    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_password);

        //viewbinding
        binding = ActivityOldPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //将“还没有账号，点击注册”的颜色和样式进行设置
        String text01 = binding.register.getText().toString();
        SpannableString spannableString01 = new SpannableString(text01);
        //文字的点击事件
        ClickableSpan clickableSpan01 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_register = new Intent(Old_password.this,register.class);
                intent_register.putExtra("who","3");
                intent_register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_register);
            }
        };
        spannableString01.setSpan(clickableSpan01,6,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString01.setSpan(new ForegroundColorSpan(Color.BLUE),6,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.register.setText(spannableString01);
        binding.register.setMovementMethod(LinkMovementMethod.getInstance());//设置点击事件生效

        //“手机号密码登录”进行样式设置
        String text02 = binding.verifycodeLogin.getText().toString();
        SpannableString spannableString02 = new SpannableString(text02);
        ClickableSpan clickableSpan02 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //-------------------
                Intent intent_password = new Intent(Old_password.this, Old_password.class);
                intent_password.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_password);
                Toast.makeText(Old_password.this,"你点击了手机号密码登录",Toast.LENGTH_SHORT).show();
            }
        };
        spannableString02.setSpan(clickableSpan02,0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString02.setSpan(new ForegroundColorSpan(Color.BLUE),0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.verifycodeLogin.setText(spannableString02);
        binding.verifycodeLogin.setMovementMethod(LinkMovementMethod.getInstance());

        //设置什么登录端--默认为老人
        String text03 = binding.loginMode.getText().toString();
        SpannableString spannableString03 = new SpannableString(text03);
        spannableString03.setSpan(new ForegroundColorSpan(Color.BLUE),16,18,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString03.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent_manager = new Intent(Old_password.this, manager.class);
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
                Intent intent_staff = new Intent(Old_password.this, staff.class);
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
                Intent intent_old = new Intent(Old_password.this, MainActivitytht.class);
                intent_old.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_old);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.DKGRAY);
                ds.setUnderlineText(false);
            }
        },11,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.loginMode.setText(spannableString03);
        binding.loginMode.setMovementMethod(LinkMovementMethod.getInstance());

        //手机号校验
        binding.loginPassword.phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false){
                    CheckPhone.checkPhone(binding.loginPassword.phoneNumber,getApplicationContext());
                }else{
                    binding.loginPassword.phoneNumber.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        //密码校验--密码不能为空
        binding.loginPassword.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.loginPassword.password.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.loginPassword.password.setError("请输入密码",drawable);
                    }
                }else{
                    binding.loginPassword.password.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });

        //点击登录
        binding.loginPassword.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = binding.loginPassword.phoneNumber.getText().toString();
                String password = binding.loginPassword.password.getText().toString();
                if(binding.loginPassword.phoneNumber.getError() != null || binding.loginPassword.password.getError() != null ||
                        phoneNumber.length() == 0 || password.length() == 0){
                    Toast.makeText(getApplicationContext(),"请输入完整信息！",Toast.LENGTH_SHORT).show();
                }else{
                    //可以进行网络请求登录
                    Log.i(TAG, "onClick: 手机号密码登录，进行网络请求");
                    String json = String.format("{\n" +
                            "  \"account\": \"%s\",\n" +
                            "  \"mode\": \"account\",\n" +
                            "  \"password\": \"%s\",\n" +
                            "  \"phone\": \"\",\n" +
                            "  \"verifyCode\":\"\"\n" +
                            "}", phoneNumber, password);
                    Log.i(TAG, "请求的json为" + json);
                    //创建http请求
                    Request request = new Request.Builder()
                            .url("https://beadhouse.81jcpd.cn/user/account/login")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.i(TAG, "onFailure: 网络请求失败！");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            Log.i(TAG, "onResponse: 网络请求成功");
                            String res = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                String message = jsonObject.getString("message");
                                JSONObject dataObject = jsonObject.getJSONObject("data");
                                String token = dataObject.getString("token");
                                String identify = dataObject.getString("identify");
                                Log.i(TAG, "identify"+ identify);
                                Log.i(TAG, "message"+ message);
                                Log.i(TAG, "token"+ token);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        });
    }
}
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

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_login.databinding.ActivityStaffBinding;

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

public class staff extends AppCompatActivity {

    private static final String TAG = "staff";
    //viewbinding
    private ActivityStaffBinding binding;

    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

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
                Intent intent_relation = new Intent(staff.this, MainActivitytht.class);
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

        //账号不能为空
        binding.login.account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.login.account.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.login.account.setError("请输入账号",drawable);
                    }
                }else{
                    binding.login.account.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        //密码不能为空
        binding.login.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.login.password.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.login.password.setError("请输入密码",drawable);
                    }
                }else{
                    binding.login.password.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        //点击登录
        binding.login.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = binding.login.account.getText().toString();
                String password = binding.login.password.getText().toString();
                if(account.length() == 0 || password.length() == 0 || binding.login.account.getError() != null || binding.login.password.getError() != null){
                    Toast.makeText(getApplicationContext(),"请输入正确信息",Toast.LENGTH_SHORT).show();
                }else{
                    String json = String.format("{\n" +
                            "  \"account\": \"%s\",\n" +
                            "  \"mode\": \"account\",\n" +
                            "  \"password\": \"%s\",\n" +
                            "  \"phone\": \"\",\n" +
                            "  \"verifyCode\":\"\"\n" +
                            "}", account, password);
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
                            Log.i(TAG, "onFailure: 网络请求失败");
                            e.printStackTrace();
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
                                ARouter.getInstance().build("/nurse/MainActivty3/2").withString("auth",token).navigation();
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
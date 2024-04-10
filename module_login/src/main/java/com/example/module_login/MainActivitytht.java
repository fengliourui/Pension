package com.example.module_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_login.Util.CheckPhone;
import com.example.module_login.Util.CountDownTimerUtils;
import com.example.module_login.Util.Internet;
import com.example.module_login.databinding.ActivityMainBinding;


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

@Route(path="/login/MainActivtyttt/1")
public class  MainActivitytht extends AppCompatActivity {
    private ActivityMainBinding binding;
    String phoneNumber;
    String getURL;
    private static final String TAG = "MainActivitytht";
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
                //发送验证码
                phoneNumber = binding.loginVerifycode.phoneNumber.getText().toString();
                if(phoneNumber.length() != 11){
                    Toast.makeText(MainActivitytht.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else{
                    getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/user/code/send","phone",phoneNumber);
                    getURL = Internet.addURLParam(getURL,"mode","1");
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
                Intent intent_register = new Intent(MainActivitytht.this,register.class);
                intent_register.putExtra("who","2");
//                intent_register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
                Intent intent_password = new Intent(MainActivitytht.this, relation_password.class);
                intent_password.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_password);
                //Toast.makeText(MainActivitytht.this,"你点击了手机号密码登录",Toast.LENGTH_SHORT).show();
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
                Intent intent_manager = new Intent(MainActivitytht.this, manager.class);
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
                Intent intent_staff = new Intent(MainActivitytht.this, staff.class);
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
                Intent intent_old = new Intent(MainActivitytht.this, Old_VerifyCode.class);
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

        //手机号检验
        binding.loginVerifycode.phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false){
                    CheckPhone.checkPhone(binding.loginVerifycode.phoneNumber,getApplicationContext());
                }else{
                    binding.loginVerifycode.phoneNumber.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        //验证码不能为空
        binding.loginVerifycode.verifyWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.loginVerifycode.verifyWord.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.loginVerifycode.verifyWord.setError("请输入密码",drawable);
                    }
                }else{
                    binding.loginVerifycode.verifyWord.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        //登录
        binding.loginVerifycode.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = binding.loginVerifycode.phoneNumber.getText().toString();
                String verifyWord = binding.loginVerifycode.verifyWord.getText().toString();
                if(binding.loginVerifycode.phoneNumber.getError() != null || binding.loginVerifycode.verifyWord.getError() != null ||
                        phoneNumber.length() == 0 || verifyWord.length() == 0){
                    Toast.makeText(getApplicationContext(),"请输入完整信息！",Toast.LENGTH_SHORT).show();
                }else{
                    Log.i(TAG, "手机号验证码进行登录");
                    String json = String.format("{\n" +
                            "  \"account\": \"\",\n" +
                            "  \"mode\": \"phone\",\n" +
                            "  \"password\": \"\",\n" +
                            "  \"phone\": \"%s\",\n" +
                            "  \"verifyCode\":\"%s\"\n" +
                            "}", phoneNumber, verifyWord);
                    Log.i(TAG, "请求的json为" + json);
                    Request request = new Request.Builder()
                            .url("https://beadhouse.81jcpd.cn/user/phone/login")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.i(TAG, "onFailure: 网络请求失败");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            Log.i(TAG, "onResponse: 网络请求成功");
                            String res = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                String message = jsonObject.getString("message");
                                String code = jsonObject.getString("code");
                                Log.i(TAG, "message = "+ message);
                                Log.i(TAG, "code = "+ code);
                                if(code.equals("fail")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{
                                    JSONObject dataObject = jsonObject.getJSONObject("data");
                                    String token = dataObject.getString("token");
                                    String identify = dataObject.getString("identify");
                                    ARouter.getInstance().build("/dependents/MainActivty2/2").withString("auth",token).navigation();
                                    Log.i(TAG, "identify = "+ identify);
                                    Log.i(TAG, "token = "+ token);
                                }
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
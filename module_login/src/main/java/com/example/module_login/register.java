package com.example.module_login;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.module_login.Util.CheckPhone;
import com.example.module_login.Util.CountDownTimerUtils;
import com.example.module_login.Util.Internet;
import com.example.module_login.databinding.ActivityRegisterBinding;

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

public class register extends AppCompatActivity {

    private static final String TAG = "register";
    //viewbinding
    private ActivityRegisterBinding binding;
    private final OkHttpClient client = new OkHttpClient();

    private Toolbar toolbar;
    String name;
    String idNumber;
    String phoneNumber;
    String verifyCode;
    String password01;
    String password02;
    String getURL;
    String who;//判断是家属还是老人

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //设置toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ssssss");
        toolbar.setNavigationIcon(R.drawable.navigationbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: 点击了navigation");
                onBackPressed();//返回
            }
        });

        //得到上一个activity传来的who
        who = getIntent().getStringExtra("who");
        //代码设置
        //EditText会被弹出的软键盘遮挡住
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //viewbinding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //发送验证码
        //发送验证码点击
        binding.sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = binding.phoneNumber.getText().toString();
                if(phoneNumber.length() != 11){
                    Toast.makeText(register.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else{
                    getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/user/code/send","phone",phoneNumber);
                    getURL = Internet.addURLParam(getURL,"mode","0");
                    Log.i(TAG, "请求的url是：" + getURL);
                    Request request_verifyCode = new Request.Builder().get()
                            .url(getURL)
                            .build();
                    Call call_verifyCode = client.newCall(request_verifyCode);
                    call_verifyCode.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.i(TAG, "onFailure: 网络请求失败");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    CountDownTimerUtils mCountDown = new CountDownTimerUtils(binding.sendCode,60000,1000);
                                    mCountDown.start();
                                }
                            });
                        }
                    });
                }
            }
        });

        //电话校验
        binding.phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false){
                    CheckPhone.checkPhone(binding.phoneNumber,getApplicationContext());
                }else{
                    binding.name.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });

        //身份证校验
        binding.idNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.idNumber.getText().length() != 18){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.idNumber.setError("请输入正确的身份证号码",drawable);
                    }
                }else{
                    binding.name.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });

        //姓名校验
        binding.name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.name.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        Log.i(TAG, "onFocusChange: 判断失去焦点");
                        binding.name.setError("请输入姓名",drawable);
                    }
                }else{
                    binding.name.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });

        //验证码校验
        binding.verifyWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.verifyWord.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.verifyWord.setError("请输入获得的验证码",drawable);
                    }
                }else{
                    binding.verifyWord.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });

        //密码校验
        binding.password01.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.password01.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.password01.setError("请输入密码",drawable);
                    }
                }else{
                    binding.password01.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        binding.password02.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b ==false ){
                    if(binding.password02.getText().length() == 0){
                        Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                        drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                        binding.password02.setError("请再次输入密码",drawable);
                    }
                    password01 = binding.password01.getText().toString();
                    password02 = binding.password02.getText().toString();
                    if(!password01.equals(password02)){
                        Toast.makeText(getApplicationContext(),"两次输入的密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                        binding.password01.setText("");
                        binding.password02.setText("");
                    }
                }else{
                    binding.password02.setError(null, null);//焦点聚焦时去除错误图标
                }
            }
        });
        //按钮登录
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.name.getText().toString();
                idNumber = binding.idNumber.getText().toString();
                phoneNumber = binding.phoneNumber.getText().toString();
                verifyCode = binding.verifyWord.getText().toString();
                password01 = binding.password01.getText().toString();
                password02 = binding.password02.getText().toString();

                if(!binding.verifyWord.getText().toString().equals("123456")){
                    Drawable drawable = getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
                    drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
                    binding.verifyWord.setError("输入的验证码不正确",drawable);
                    binding.verifyWord.setText("");

                }
                if(binding.name.getError() != null || binding.idNumber.getError() != null || binding.phoneNumber.getError() != null || binding.verifyWord.getError() != null
                        || binding.password01.getError() != null || binding.password02.getError() != null || name.length() == 0 || idNumber.length() == 0 || phoneNumber.length() == 0
                        || password01.length() == 0 || password02.length() == 0){
                    Toast.makeText(getApplicationContext(),"请输入完整信息！",Toast.LENGTH_SHORT).show();
                }else{
                    //可以进行注册
                    String json = String.format("{\n" +
                            "  \"identifyId\": \"%s\",\n" +
                            "  \"password\": \"%s\",\n" +
                            "  \"phone\": \"%s\",\n" +
                            "  \"userName\": \"%s\",\n" +
                            "  \"verifyCode\": \"%s\",\n" +
                            "  \"who\": \"%s\"\n" +
                            "}", idNumber, password01, phoneNumber, name, verifyCode, who);
                    //创建http请求
                    Request request_register = new Request.Builder()
                            .url("https://beadhouse.81jcpd.cn/user/register")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();
                    Log.i(TAG, "onClick: "+ request_register);
                    Call call_register = client.newCall(request_register);
                    call_register.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.i(TAG, "onFailure: json请求失败！");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            Log.i(TAG, "onResponse: 请求成功");
                            String res = response.body().string();//注意这里不能用tostring方法，会出错
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                Log.i(TAG, "onResponse: "+ message);
                                Log.i(TAG, "onResponse: "+ code);
                                if(!code.equals("200")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.i(TAG, "run: !code equals 200");
                                            Log.i(TAG, "run: " + message);
                                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();//到了异步处理中，拷贝，所以变成了粉色加下划线
                                        }
                                    });
                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.i(TAG, "onResponse: code == 200 注册成功请登录！");
                                            Toast.makeText(getApplicationContext(),"注册成功，请登录！",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    //finish();
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
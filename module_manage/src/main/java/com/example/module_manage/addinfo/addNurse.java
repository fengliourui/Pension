package com.example.module_manage.addinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.module_manage.MainActivity;
import com.example.module_manage.R;
import com.example.module_manage.databinding.ActivityAddNurseBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class addNurse extends AppCompatActivity {

    private static final String TAG = "addNurse";
    ActivityAddNurseBinding binding;
    Button register;
    String account;
    String idNumber;
    String password;
    String name;
    private final OkHttpClient client = new OkHttpClient();
    String token = MainActivity.token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nurse);

        //binding
        binding = ActivityAddNurseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = binding.account.getText().toString();
                idNumber = binding.idNumber.getText().toString();
                password = binding.password.getText().toString();
                name = binding.name.getText().toString();
                if(account.length() == 0 || idNumber.length() == 0 || password.length() == 0 || name.length() == 0 ){
                    Toast.makeText(addNurse.this, "请填写完整信息！", Toast.LENGTH_SHORT).show();
                }else{
                    //先创建json字符串
                    String json = String.format("{\n" +
                            "  \"account\": \"%s\",\n" +
                            "  \"identifyId\": \"%s\",\n" +
                            "  \"password\": \"%s\",\n" +
                            "  \"phone\": \"\",\n" +
                            "  \"userName\": \"%s\",\n" +
                            "  \"verifyCode\": \"\",\n" +
                            "  \"who\": \"1\"\n" +
                            "}",account,idNumber,password,name);
                    //创建http请求,因为这里要post json和string，所以要分开处理，然后使用multipart
                    //json数据
                    RequestBody jsonBody = RequestBody.create(json, MediaType.parse("application/json"));

                    //普通字符串数据，这里假设时表单键值对
                    /*FormBody.Builder formBuilder = new FormBody.Builder();
                    formBuilder.add("auth",token);
                    RequestBody formBody = formBuilder.build();*/

                    //字符串数据
                    RequestBody stringBody = RequestBody.create(MediaType.parse("text/plain"),token);

                    //创建MultipartBody，用于同时包含JSON和普通字符串
                    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    //添加json部分作为part
                    multipartBuilder.addFormDataPart("registerVO","json_data.json",jsonBody);//第二个参数为文件名，虽然没有文件但还是要写 不知道为什么
                    //添加string部分作为part
                    multipartBuilder.addFormDataPart("auth","string_data.txt",stringBody);
                    //创建完整的multipartBody
                    RequestBody requestBody = multipartBuilder.build();
                    Request request = new Request.Builder()
                            .url("https://beadhouse.81jcpd.cn/master/register/nursing")
                            .post(requestBody)
                            .build();
                    Log.i(TAG, json);
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
                                Log.i(TAG, "返回的message：" + message);
                                String code = jsonObject.getString("code");
                                Log.i(TAG, "返回的code：" + code);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                    }
                                });
                                if(code.equals("success")){
                                    finish();
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
package com.example.module_manage.addinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.module_manage.Main;
import com.example.module_manage.R;
import com.example.module_manage.databinding.ActivityAddBinding;
import com.example.module_manage.util.Internet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class addActivity extends AppCompatActivity {

    private static final String TAG = "addActivity";
    ActivityAddBinding binding;
    Button register;
    String title;
    String introduce;
    String startTime;
    String endTime;
    String applyStart;
    String applyEnd;
    int maxParticipants;
    private final OkHttpClient client = new OkHttpClient();
    String token = Main.token;
    String getURL;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        //binding
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = binding.title.getText().toString();
                introduce = binding.introduce.getText().toString();
                startTime = binding.startTime.getText().toString();
                endTime = binding.endTime.getText().toString();
                applyStart = binding.applyStart.getText().toString();
                applyEnd = binding.applyEnd.getText().toString();
                maxParticipants = Integer.parseInt(binding.maxParticipants.getText().toString());
                if(title.length() == 0 || introduce.length() == 0 || startTime.length() == 0 || endTime.length() == 0 ||
                applyStart.length() == 0 ||applyEnd.length() == 0 ||binding.maxParticipants.getText().toString().length() == 0 ){
                    Toast.makeText(addActivity.this, "请填写完整信息！", Toast.LENGTH_SHORT).show();
                }else{
                    //先创建json字符串
                    String json = String.format("{\n" +
                            "  \"applyEnd\": \"%s\",\n" +
                            "  \"applyStart\": \"%s\",\n" +
                            "  \"endTime\": \"%s\",\n" +
                            "  \"introduce\": \"%s\",\n" +
                            "  \"maxParticipants\": %d,\n" +
                            "  \"startTime\": \"%s\",\n" +
                            "  \"title\": \"%s\"\n" +
                            "}",applyEnd,applyStart,endTime,introduce,maxParticipants,startTime,title);
                    //创建http请求,因为这里要post json和string，所以要分开处理，然后使用multipart
                    //json数据
                    RequestBody jsonBody = RequestBody.create(json, MediaType.parse("application/json"));

                    //普通字符串数据，这里假设时表单键值对
                    /*FormBody.Builder formBuilder = new FormBody.Builder();
                    formBuilder.add("auth",token);
                    RequestBody formBody = formBuilder.build();*/

                    //字符串数据
                    //RequestBody stringBody = RequestBody.create(token,MediaType.parse("text/plain"));

                    //创建MultipartBody，用于同时包含JSON和普通字符串
                    //MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    //添加json部分作为part
                    //multipartBuilder.addFormDataPart("newActivityVo","json_data.json",jsonBody);//第二个参数为文件名，虽然没有文件但还是要写 不知道为什么
                    //添加string部分作为part
                    //multipartBuilder.addFormDataPart("auth","string_data.txt",stringBody);
                    //终于发现错误的原因.....auth要作为参数直接加到url里面

                    //创建完整的multipartBody
                    //MultipartBody multipartBody = multipartBuilder.build();
                    getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/activity/new","auth",token);
                    Request request = new Request.Builder()
                            .url(getURL)
                            .post(jsonBody)
                            .build();
                    //Log.i(TAG, json);
                    Log.i(TAG, request.toString());
                    Log.i(TAG, jsonBody.toString());
                    /*Log.i(TAG, stringBody.toString());
                    Log.i(TAG, multipartBody.toString());*/
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
                                String data = jsonObject.getString("data");
                                Log.i(TAG, "返回的data：" + data);//创建成功的活动的id
                                int statusCode = response.code();
                                Log.i(TAG, "返回的状态码：" + statusCode);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                    }
                                });

                                if(code.equals("success")){
                                    MediaType type = MediaType.parse("image/png");
                                    //File file = new File("C:\\Users\\29877\\Pictures\\Screenshots\\屏幕截图 2024-04-05 115142.png");
                                    //从当前包下的drawable资源中获取bitmap
                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("nopicture","drawable",getPackageName()));
                                    //将Bitmap转换为字节数组
                                    //ByteArrayOutputStream用于写入数据到字节数组
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    //将bitmap压缩为png格式，并写入到ByteArrayOutputStream中，压缩质量设置为100，表示最高质量
                                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                                    //获取压缩了的png图像的字节数据
                                    byte[] fileContent = byteArrayOutputStream.toByteArray();

                                    //创建MultipartBody.Part用于文件
                                    RequestBody fileBody = RequestBody.create(fileContent,type);
                                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file","nopicture.png",fileBody);
                                    //创建MultipartBody.Builder 用于添加其他表单字段（如果需要）
                                    MultipartBody.Builder requestBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                                    //requestBuilder.addFormDataPart("auto",token);
                                    requestBuilder.addFormDataPart("id",data);
                                    //添加文件部分
                                    requestBuilder.addPart(filePart);
                                    //构建MultipartBody
                                    RequestBody requestBody = requestBuilder.build();
                                    //构建request
                                    Request request = new Request.Builder()
                                            .url(Internet.addURLParam("https://beadhouse.81jcpd.cn/activity/upload/iv","auth",token))
                                                    .post(requestBody)
                                                            .build();
                                    Log.i(TAG, "添加活动后请求添加照片的url" + request);
                                    Call add_pic = client.newCall(request);
                                    add_pic.enqueue(new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                            Log.i(TAG, "添加活动后添加照片网络请求失败！");
                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            Log.i(TAG, "添加活动后添加照片网络请求成功！");
                                            String addPic = response.body().string();
                                            try {
                                                JSONObject object = new JSONObject(addPic);
                                                String code = object.getString("code");
                                                Log.i(TAG, "onResponse: " + code);
                                                finish();
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }

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
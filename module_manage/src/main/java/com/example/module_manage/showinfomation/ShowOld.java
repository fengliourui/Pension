package com.example.module_manage.showinfomation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.module_manage.MainActivity;
import com.example.module_manage.R;
import com.example.module_manage.databinding.ActivityShowBinding;
import com.example.module_manage.util.Internet;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowOld extends AppCompatActivity {

    private static final String TAG = "ShowOld";

    private final OkHttpClient client = new OkHttpClient();
    String token = MainActivity.token;
    String id;
    String userName;
    String getURL;
    TextView height,weight,lung,sugar,pressure,fat;
    TextView cholesterol,apolipoprotein,urea,creatinine;//胆固醇，载脂蛋白，尿素，肌酐
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_old);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        lung = findViewById(R.id.lung_capcity);
        sugar = findViewById(R.id.blood_sugar);
        pressure = findViewById(R.id.blood_pressure);
        fat = findViewById(R.id.blood_fat);
        cholesterol = findViewById(R.id.cholesterol);
        apolipoprotein = findViewById(R.id.apolipoprotein);
        urea = findViewById(R.id.urea);
        creatinine = findViewById(R.id.creatinine);
        name = findViewById(R.id.name);

        //获取传过来的id
        id = getIntent().getStringExtra("userId");
        //获取传过来的name
        userName = getIntent().getStringExtra("userName");
        name.setText(userName);

        Log.i(TAG, "点击的老人id为：" + id);

        getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/older/getolder","auth",token);
        getURL = Internet.addURLParam(getURL,"olderid",id);
        Log.i(TAG, "展示老人详细数据--请求的url为：" + getURL);
        Request request = new Request.Builder()
                .url(getURL)
                .get()
                .build();
        Call call = client.newCall(request);
        //主线程中不能开启同步网络请求，会出错
       // Response getid_response = client.newCall(get_id).execute();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "获取老人详细信息 error!!!");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response res) throws IOException {
                Log.i(TAG, "获取老人详细信息 success!!!");
                String response = res.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject data = jsonObject.getJSONObject("data");
                    height.setText(data.getString("338051"));
                    weight.setText(data.getString("715035"));
                    lung.setText(data.getString("656425"));
                    sugar.setText(data.getString("422085"));
                    pressure.setText(data.getString("894678"));
                    fat.setText(data.getString("786248"));
                    urea.setText(data.getString("781058"));
                    cholesterol.setText(data.getString("673263"));
                    apolipoprotein.setText(data.getString("905756"));
                    creatinine.setText(data.getString("481612"));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
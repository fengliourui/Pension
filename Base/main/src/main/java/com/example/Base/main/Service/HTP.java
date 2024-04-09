package com.example.Base.main.Service;

import com.example.Base.main.Service.Chat.Data.Answer;
import com.example.Base.main.Service.Data.nusion.Nurse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HTP {
    public String okHttp(String requestData ,String auth1) throws InterruptedException, JSONException {
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        String  auth = auth1;
        String url = "https://beadhouse.81jcpd.cn/nursing/upload/older";
        MediaType mediaType;
        mediaType = MediaType.parse("application/json");
        OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();;
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(mediaType, new JSONObject(requestData).toString());
//         创建Request对象，设置URL和请求方式
        Request request = new Request.Builder()
                .url(url+ "?auth=" + auth)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        try {
            okhttp3.Response response = HTTP_CLIENT.newCall(request).execute(); // 同步请求改为使用execute()
            if (response.isSuccessful() && response.body() != null) {
                final String responseBody = response.body().string();
                Nurse nurse = gson.fromJson(responseBody, Nurse.class);
                return nurse.getData();
            } else {
                // 处理错误情况，如网络错误或服务器返回错误
                return "服务器响应失败";
            }
        } catch (IOException e) {
            // 处理网络请求异常
            return "网络请求出错";
        }
    }
}

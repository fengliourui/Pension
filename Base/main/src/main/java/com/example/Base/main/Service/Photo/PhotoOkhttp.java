package com.example.Base.main.Service.Photo;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PhotoOkhttp {
   private   OkHttpClient client =new OkHttpClient();
    Gson gson = new Gson();
    // 构建请求体，这里假设发送的是JSON数据
    public Barcode  getdata()
    {
        String url = " https://medical.nocode.com/open/v2/nc.ms.drug.detail.barcode";
        String barcode = null;
        String json = "{\"key\": \"value\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        // 构建请求对象，这里使用POST方法
        Request request = new Request.Builder()
                .url(url+"?barcode="+barcode)
                .post(requestBody) // 使用POST方法发送请求
                .build();
        // 发送请求并获取响应
        try {
            Response response =  client.newCall(request).execute();
            if (response.isSuccessful()&&response.body()!=null)
            {
                final String responseBody = response.body().string();
                Barcode barcode1 = gson.fromJson(responseBody,Barcode.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

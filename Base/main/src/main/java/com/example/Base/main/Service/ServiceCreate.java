package com.example.Base.main.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreate {
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://beadhouse.81jcpd.cn/") // 设置基础 URL
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析器
            .build();
    public static <T> T create(Class<T> serviceClass) { return retrofit.create(serviceClass); }
}

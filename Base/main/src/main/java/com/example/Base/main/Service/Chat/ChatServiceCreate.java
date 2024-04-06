package com.example.Base.main.Service.Chat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatServiceCreate {
    String url1 = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie_speed";
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/") // 设置基础 URL
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析器
            .build();
    public static <T> T create(Class<T> serviceClass) { return retrofit.create(serviceClass); }
}

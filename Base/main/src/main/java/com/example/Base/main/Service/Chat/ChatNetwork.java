package com.example.Base.main.Service.Chat;

import com.example.Base.main.Service.Chat.Data.Answer;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ChatNetwork {
    private OkHttpClient HTTP_CLIENT;
    private final String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie_speed";

    private final static String ACCESS_TOKEN = "24.f0e3a9df9d175c46bd85751740139cb2.2592000.1714824620.282335-59589873";
    Gson gson = new Gson();
    private HashMap<String, Object> requestBody ;
    MediaType mediaType;
    private ArrayList<HashMap<String, String>> messages;;
    static String access_token = "24.f0e3a9df9d175c46bd85751740139cb2.2592000.1714824620.282335-59589873";
    private static ChatService Service = ChatServiceCreate.create(ChatService.class);
    public ChatNetwork() {
        mediaType = MediaType.parse("application/json");
        this.HTTP_CLIENT = new OkHttpClient().newBuilder().build();
        this.requestBody = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public  String addAndCall(String content) throws InterruptedException {
        addMsg(content);
        return chatCall();
    }
    int  times = 1;
    public void addMsg(String content){
        HashMap<String, String> msg = new HashMap<>();
        if (times++ % 2 == 1){
            msg.put("role", "user");
        } else {
            msg.put("role", "assistant");
        }
        msg.put("content", content);
        messages.add(msg);
    }
    public String chatCall() throws InterruptedException {
        requestBody.put("messages", messages);
        RequestBody body = RequestBody.create(mediaType, new JSONObject(requestBody).toString());
        Request request = new Request.Builder()
                .url(url + "?access_token=" + ACCESS_TOKEN)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try {
            okhttp3.Response response = HTTP_CLIENT.newCall(request).execute(); // 同步请求改为使用execute()
            if (response.isSuccessful() && response.body() != null) {
                final String responseBody = response.body().string();
                Answer answer = gson.fromJson(responseBody, Answer.class);
                addMsg(answer.getResult()); // 假设Answer类有getResult()方法获取答案
                return answer.getResult();
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

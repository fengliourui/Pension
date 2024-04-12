package com.example.Base.main.Service.Chat;

import com.example.Base.main.Service.Chat.Data.Answer;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ChatService {
    @POST("chat/ernie_speed")
    @Headers({"Content-Type: application/json"})
    Call<Answer> AddMsg(
            @Query("access_token") String access_token,
            @Body ArrayList<HashMap<String, String>> messages

    );
}

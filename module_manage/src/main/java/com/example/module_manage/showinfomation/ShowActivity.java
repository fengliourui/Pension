package com.example.module_manage.showinfomation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.module_manage.Main;
import com.example.module_manage.R;
import com.example.module_manage.databinding.ActivityShowBinding;
import com.example.module_manage.util.ActivityEvent;
import com.example.module_manage.util.Internet;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowActivity extends AppCompatActivity {

    private static final String TAG = "ShowActivity";
    ActivityShowBinding binding;

    String activityId;
    String token = Main.token;
    ActivityEvent event;
    MZBannerView banner;
    private final OkHttpClient client = new OkHttpClient();
    String getURL;

    Toolbar toolbar;
    String title;

    String introduce;

    int curParticipants;

    int maxParticipants;

    String applyStart;

    String applyEnd;

    String startTime;

    String endTime;

    List<ActivityEvent.Image> images = new ArrayList<>();

    List<ActivityEvent.Video> videos = new ArrayList<>();

    //用于存放图片和视频的url
    List<String> dataList = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //binding
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        banner = findViewById(R.id.activity_banner);
        //设置toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置navigation up按钮的图标和点击监听
        //左侧按钮：可见+更换图标+点击监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回按钮
        toolbar.setNavigationIcon(R.drawable.navigationbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        activityId = getIntent().getStringExtra("id");
        Log.i(TAG, "得到的activity id为：" + activityId);

        getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/activity/get/detail", "id", activityId);
        getURL = Internet.addURLParam(getURL, "auth", token);
        Request request = new Request.Builder()
                .url(getURL)
                .get()
                .build();
        Log.i(TAG, "获取活动详细信息的url：" + request);
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "onFailure: 获取活动详细信息网络请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i(TAG, "onResponse: 获取活动详细信息网络请求成功");
                String res = response.body().string();
                Log.i(TAG, res);

                //使用Gson的JsonParser来解析JSON字符串
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(res);

                JsonObject dataObject = jsonElement.getAsJsonObject().getAsJsonObject("data");
                String dataString = dataObject.toString();
                //使用Gson来解析返回结果
                Gson gson = new Gson();
                event = gson.fromJson(dataString, ActivityEvent.class);
                Log.i(TAG, "event:" + event);
                title = event.getTitle();
                introduce = event.getIntroduce();
                curParticipants = event.getCurParticipants();
                maxParticipants = event.getMaxParticipants();
                startTime = event.getStartTime();
                endTime = event.getEndTime();
                applyStart = event.getApplyStart();
                applyEnd = event.getApplyEnd();
                images = event.getImages();
                videos = event.getVideos();
                for (int i = 0; i < 50; i++) {
                    sb.append(introduce);
                }
                String repeatedIntroduce = sb.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.title.setText(title);
                        binding.participant.setText("参与人数：" + curParticipants + " / " + maxParticipants);
                        binding.applyTime.setText("活动申请时间：" + applyStart + " -- " + applyEnd);
                        binding.holdTime.setText("活动举办时间：" + startTime + " -- " + endTime);
                        binding.content.setText(repeatedIntroduce);
                        //把返回的图片和视频的url都放到dataList中,然后再放到banner里
                        for(ActivityEvent.Image image : images){
                            if (image.getUrl() != null) {
                                dataList.add(image.getUrl());
                                // Log.i(TAG, "此时的图片url：" + image.getUrl());
                            }
                        }
                        //banner
                        banner.setPages(dataList, new MZHolderCreator() {
                            @Override
                            public MZViewHolder createViewHolder() {
                                return new AnotherBannerViewHolder();
                            }
                        });
                    }
                });
            }
        });
    }

    private class AnotherBannerViewHolder implements MZViewHolder<String>{

        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String url) {
            Glide.with(context)
                    .load(url)
                    .into(mImageView);
        }
    }
}
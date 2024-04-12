package com.example.module_manage.showinfomation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.module_manage.Main;
import com.example.module_manage.R;
import com.example.module_manage.databinding.ActivityShowBinding;
import com.example.module_manage.util.ActivityEvent;
import com.example.module_manage.util.Internet;
import com.example.module_manage.util.Path;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowActivity extends AppCompatActivity {

    private static final String TAG = "ShowActivity";
    ActivityShowBinding binding;

    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    //上传图片用的
    private  File imageFile;

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

        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

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

        binding.addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            }
        });
    }


    //在手机上选择玩图片后会回调这个函数，data是选择的图片的信息
    //功能：从请求结果中获取图片，然后下载图片到本地缓存目录，并上传到后台
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                String s=data.getData().toString();//data.getDtat()返回的是一个虚拟的Uri
                //这里使用Glide来下载图片，downloadOnly表示只下载而不显示
                //into(new CustomTarget)是一个自定义的下载目标，用于处理下载后的文件
                Glide.with(this)
                        .downloadOnly()
                        .load(s)
                        .into(new CustomTarget<File>() {
                            //当Glide下载完图片后，会回调这个方法
                            //在这里面获取应用的缓存目录，然后创建一个新的文件路径，并将下载的图片文件复制到这个新路径
                            @Override
                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                // resource即为下载后的File文件
                                File cacheDir = getCacheDir();
                                String fileName = "cached_image1.jpg";
                                imageFile = new File(cacheDir, fileName);
                                try {
                                    copyFile(resource, imageFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //将文件上传到后台
                                upload(imageFile);
                                // 创建一个Intent对象,目的是将图片的路径返回上一个活动
                                Intent intent = new Intent();
                                String imagePath = imageFile.getAbsolutePath();
                                intent.putExtra("imagePath2",imagePath);
                                // 设置结果码为RESULT_OK，表示操作成功
                                setResult(Activity.RESULT_OK, intent);
                            }
                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                // 可选的清理操作
                            }
                        });
            }
        }
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
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }
        fos.flush();
        fos.close();
        fis.close();
    }
    public void upload(File file){
        getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/activity/upload/iv","auth",token);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(),fileBody)
                .addFormDataPart("id",activityId)
                .build();
        Request request1 = new Request.Builder()
                .url(getURL)
                .post(requestBody)
                .build();
        Call call1 = client.newCall(request1);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "上传图片网络请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i(TAG, "上传图片网络请求成功");
                Log.i(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}
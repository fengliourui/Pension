package com.example.module_manage.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.module_manage.MainActivity;
import com.example.module_manage.R;
import com.example.module_manage.util.ActivityEvent;
import com.example.module_manage.util.Internet;
import com.example.module_manage.util.MyRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstPage extends Fragment {

    private static final String TAG = "FirstPage";
    //banner
    MZBannerView banner;
    List<Integer> list = new ArrayList<>();

    //recyclerview
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;

    private final OkHttpClient client = new OkHttpClient();
    String token = MainActivity.token;
    String getURL;
    List<ActivityEvent> events = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add(R.drawable.banner1);
        list.add(R.drawable.banner2);
        list.add(R.drawable.banner3);
        list.add(R.drawable.banner4);
        list.add(R.drawable.banner5);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //banner
        banner = (MZBannerView) view.findViewById(R.id.banner);
        recyclerView =  view.findViewById(R.id.recycler_view);
        //设置数据
        banner.setPages(list, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

        //刷新数据
        //refreshLayout = view.findViewById(R.id.refresh);
        /*refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
        });*/
        //网络请求
        getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/activity/getsome","auth",token);
        Log.i(TAG, "onViewCreated: 请求的url是" + getURL);
        Request request = new Request.Builder()
                .url(getURL)
                .build();
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
                Gson gson = new Gson();
                //创建typetoken来指定list类型，这里的泛型里是序列化的类
                Type eventType = new TypeToken<List<ActivityEvent>>(){}.getType();
                //解析json字符串
                JsonElement jsonElement = gson.fromJson(res,JsonElement.class);
                JsonArray jsonArray = jsonElement.getAsJsonObject().getAsJsonArray("data");
                //将jsonArray转换为List
                events = gson.fromJson(jsonArray,eventType);

                for(ActivityEvent event: events){//测试用
                    Log.i(TAG, event + "\n");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //recyclerview
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext(),events);
                        recyclerView.setAdapter(adapter);
                        Log.i(TAG, "已添加适配器");
                    }
                });
            }
        });

    }
    //refresh

}

class BannerViewHolder implements MZViewHolder<Integer>{

    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, Integer data) {
        mImageView.setImageResource(data);
    }
}
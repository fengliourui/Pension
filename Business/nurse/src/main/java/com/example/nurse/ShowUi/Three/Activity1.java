package com.example.nurse.ShowUi.Three;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.R;
import com.example.nurse.MainActivity3;

import UI.Adapter.ImageAdapter;

public class Activity1 extends AppCompatActivity {
    LinearLayout linearLayout;
    OlderViewModel olderViewModel;
    RecyclerView recyclerView;
    ImageAdapter adapter;
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        // 设置状态栏颜色为灰色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.green(R.color.green));
            // 设置状态栏中的字体颜色为深色
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initView();
        getCloudData();
    }
    private void initView() {
        recyclerView =findViewById(R.id.tttt2);
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        linearLayout = findViewById(R.id.tht);
        textView=findViewById(R.id.text1);
    }
    private void getCloudData() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        olderViewModel.getGoodView(MainActivity3.auth);
        olderViewModel.goodviewLiveData.observe(this, new Observer<Imageview>() {
            @Override
            public void onChanged(Imageview imageview) {
                if(imageview!=null)
                {
                    if(imageview.getImage().getData().size()!=0)
                    {
                        linearLayout.setVisibility(View.GONE);
                    }
                    else
                    {
                        textView.setText("您暂无收藏视频");
                    }
                    adapter = new  ImageAdapter(Activity1.this,imageview);
                    recyclerView.setAdapter(adapter); // 设置适配器
                }
            }
        });
    }
}
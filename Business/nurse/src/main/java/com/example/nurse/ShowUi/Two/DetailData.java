package com.example.nurse.ShowUi.Two;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Base.main.Service.Data.Health.AllData;
import com.example.Base.main.Service.Data.nusion.Nurse;
import com.example.Base.main.Service.HTP;
import com.example.Base.main.Service.Repository;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.nurse.MainActivity3;
import com.example.nurse.R;
import com.example.nurse.ShowUi.Adapter.OlderHeathAdapter;
import com.example.nurse.ShowUi.SharedViewModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DetailData extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    OlderViewModel olderViewModel;
    AllData allData1;
    String id;
    OlderHeathAdapter olderHeathAdapter;
    Button button;
    public static SharedViewModel viewModel;
    Repository repository= new Repository();
    String postS;
    HTP  hp = new HTP();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dete);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
        id= getIntent().getStringExtra("id");
        initVIew();
        initData();
    }

    private void initData() {
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        Future<AllData> future = repository.getOlderData();
        try {
            allData1 = future.get(5, TimeUnit.SECONDS); // 设置超时时间为5秒
            // 使用获取到的数据（data）
        } catch (TimeoutException e) {
            // 处理超时异常
        } catch (ExecutionException e) {
            // 处理执行异常（如网络请求失败等）
        } catch (InterruptedException e) {
            // 处理中断异常
            Thread.currentThread().interrupt(); // 重置中断标志
        }
        olderHeathAdapter = new OlderHeathAdapter(DetailData.this,allData1,id);
        recyclerView.setAdapter(olderHeathAdapter);
        //每次recycleview更新数据可以刷新postS内数据
        viewModel.getReturnedData().observe(DetailData.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                postS= s;
            }
        });
        //点击上传数据
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ttttt", postS);
                Log.d("ttttt", MainActivity3.auth);
                olderViewModel.Postnurse(postS , MainActivity3.auth);
            }
        });
        olderViewModel.PostNurseLiveData.observe(DetailData.this, new Observer<Nurse>() {
            @Override
            public void onChanged(Nurse nurse) {
//                Toast.makeText(DetailData.this,nurse.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initVIew() {
        recyclerView = findViewById(R.id.recyclerView4);
        textView = findViewById(R.id.loading_text);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailData.this, 1));
        button = findViewById(R.id.posttext);
        viewModel = new ViewModelProvider(DetailData.this).get(SharedViewModel.class);
    }
}
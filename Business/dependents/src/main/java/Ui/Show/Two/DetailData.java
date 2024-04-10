package Ui.Show.Two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.Base.main.Service.Data.Health.AllData;
import com.example.Base.main.Service.Data.Health.ShowData;
import com.example.Base.main.Service.Repository;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.dependents.MainActivity2;
import com.example.Business.dependents.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Ui.Show.Adapter.OlderHeathAdapter;

public class DetailData extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    OlderViewModel olderViewModel;
    Repository repository =new Repository();
    AllData allData1;
    String id;
    OlderHeathAdapter olderHeathAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
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
        olderViewModel.getOlerDataDetail(MainActivity2.auth,id);
        olderViewModel.DataDetailLiveData.observe(this, new Observer<ShowData>() {
            @Override
            public void onChanged(ShowData showData) {
                if (showData==null)
                {
                    textView.setVisibility(View.GONE);
                    textView.setText("showdata为空");
                }else
                {
                    textView.setVisibility(View.GONE);
                    olderHeathAdapter = new OlderHeathAdapter(DetailData.this,allData1,showData);
                    recyclerView.setAdapter(olderHeathAdapter); // 设置适配器
                }
            }
        });

    }

    private void initVIew() {
        recyclerView = findViewById(R.id.recyclerView4);
        textView = findViewById(R.id.loading_text);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailData.this, 1));
    }
}
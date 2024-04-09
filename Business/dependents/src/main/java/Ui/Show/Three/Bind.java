package Ui.Show.Three;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Base.main.Service.Data.add.OlderBind;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.dependents.MainActivity2;
import com.example.Business.dependents.R;

import Ui.Show.Adapter.BindAdapter;


public class Bind extends AppCompatActivity {
    private OlderViewModel olderViewModel;
    private RecyclerView recyclerView;
    private BindAdapter adapter;
    private TextView textView;
    private Button button;
    private static final int REQUEST_CODE_OLDER_DETAIL_1 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
        recyclerView = findViewById(R.id.th);
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        textView = findViewById(R.id.noMoreCommentsTextView1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter); // 设置适配器
        olderViewModel.getOlerBind(MainActivity2.auth);
        olderViewModel.GetOlderBindLiveData.observe(this, new Observer<OlderBind>() {
            @Override
            public void onChanged(OlderBind olderBind) {
                if (olderBind != null) {
                    textView.setVisibility(View.GONE);
                    adapter = new BindAdapter(Bind.this, olderBind);
                    recyclerView.setAdapter(adapter); // 设置适配器
                }
            }
        });
        button = findViewById(R.id.try1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bind.this, SumBmit.class);
                startActivityForResult(intent, REQUEST_CODE_OLDER_DETAIL_1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断请求码和结果码
        if (requestCode == REQUEST_CODE_OLDER_DETAIL_1 && resultCode == Activity.RESULT_OK) {
            olderViewModel.getOlerBind(MainActivity2.auth);
            olderViewModel.GetOlderBindLiveData.observe(this, new Observer<OlderBind>() {
                @Override
                public void onChanged(OlderBind olderBind) {
                    if (olderBind != null) {
                        textView.setVisibility(View.GONE);
                        adapter = new BindAdapter(Bind.this, olderBind);
                        recyclerView.setAdapter(adapter); // 设置适配器
                    }
                }
            });
        }
    }

}
package UI.Show.Three;



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

import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;

import UI.Adapter.ActivtyAdapter;


public class Activity2 extends AppCompatActivity {
    public Activtyall activtyall1;
    public TextView textView;
    RecyclerView recyclerView;
    OlderViewModel olderViewModel;
    ActivtyAdapter activtyAdapter;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        // 设置状态栏颜色为灰色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.green(R.color.green));
            // 设置状态栏中的字体颜色为深色
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    private void initView() {
        recyclerView = findViewById(R.id.tttt5);
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        linearLayout =findViewById(R.id.tht);
        textView=findViewById(R.id.text1);
    }

    private void getCloudData() {

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        // 观察LiveData，更新适配器数据
        olderViewModel.getUserActivity(MainActivity1.auth,"3");
        olderViewModel.getUserACtivityLiveData.observe(this, new Observer<Activtyall>() {
            @Override
            public void onChanged(Activtyall activtyall) {
                activtyall1=activtyall;
                activtyAdapter = new ActivtyAdapter(activtyall,Activity2.this);
                if(activtyall.getData().size()!=0)
                {
                    linearLayout.setVisibility(View.GONE);
                }
                else
                {
                    textView.setText("您暂无参加活动");
                }
                recyclerView.setAdapter(activtyAdapter); // 设置适配器
            }
        });
    }
}
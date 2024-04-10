package com.example.module_manage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_manage.fragment.FirstPage;
import com.example.module_manage.fragment.Info;
import com.example.module_manage.fragment.Nurse;
import com.example.module_manage.fragment.Old;
import com.example.module_manage.util.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

@Route(path="/manage/Main/1")
public class Main extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    public static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyRHRvIjp7InVzZXJJZCI6ImNiZDVjNjRlLTRlNGYtNDkxZC1iYjk0LTQxMjI2Y2Y4NjhhYyIsImlkZW50aWZ5IjoiMCIsInVzZXJOYW1lIjoiIn0sImV4cCI6MzI4OTI4NDk1Mn0.U0tSSZmNHFZUFXQIMwFzQ0-CiwZQcQ1ZbG_IQbIuBjY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_main);
        token = getIntent().getStringExtra("auth");
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        navigationView = findViewById(R.id.nav_bottom);
        viewPager = findViewById(R.id.vp);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FirstPage());
        fragments.add(new Old());
        fragments.add(new Nurse());
        fragments.add(new Info());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(viewPagerAdapter);
        //底部导航栏监听事件
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_firstpage) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (id == R.id.item_old) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (id == R.id.item_nurse) {
                    viewPager.setCurrentItem(2);
                    return true;
                } else if (id == R.id.item_info) {
                    viewPager.setCurrentItem(3);
                    return true;
                }
                return false;
            }
        });

        //添加页面切换监听器，根据页面切换实现菜单切换
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.setSelectedItemId(R.id.item_firstpage);
                        break;
                    case 1:
                        navigationView.setSelectedItemId(R.id.item_old);
                        break;
                    case 2:
                        navigationView.setSelectedItemId(R.id.item_nurse);
                        break;
                    case 3:
                        navigationView.setSelectedItemId(R.id.item_info);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
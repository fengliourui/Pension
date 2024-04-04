package com.example.module_manage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.module_manage.fragment.FirstPage;
import com.example.module_manage.fragment.Info;
import com.example.module_manage.fragment.Nurse;
import com.example.module_manage.fragment.Old;
import com.example.module_manage.util.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    public static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyRHRvIjp7InVzZXJJZCI6IjY4YmQ1Y2ZiLTkwNjEtNDI2My1hOTQ0LWMwYTY3NWIxYmMwZiIsImlkZW50aWZ5IjoiMCIsInVzZXJOYW1lIjoiIn0sImV4cCI6MzI4OTAwMTc2N30.oNo1fu3rp-acrVsP0MAZn_C7s3JgeBR48mPz1kiaI2A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
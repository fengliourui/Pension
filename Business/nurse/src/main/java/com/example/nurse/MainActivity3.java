package com.example.nurse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Base.main.Service.Data.older.Avatar;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MyFragmentPagerAdapter;
import com.example.nurse.ShowUi.Three.Fragment3;
import com.example.nurse.ShowUi.Two.Fragment2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import UI.Show.first.Fragment1;
import UI.Show.four.Fragment4;


public class MainActivity3 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    List<Fragment> fragmentList;
    public static  String auth = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyRHRvIjp7InVzZXJJZCI6IjBiZTY5Njk2LTBhYTQtNDAyYi04Y2UyLWM2MWVhNDU4NDExNiIsImlkZW50aWZ5IjoiMSJ9LCJleHAiOjMyODM1MTI5NDd9.aacm-l9zeI8tGSajqC2GXbnsdfzZMBFZdVjeZjCh-kI";
    private int[] tabIcons = {R.drawable.bottom1, R.drawable.bottom2, R.drawable.liaot, R.drawable.bottom3};
    private File imageFile;

    public static String imagePath;
    public static int flog=0;
    public static String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        auth = getIntent().getStringExtra("auth");
        // 设置状态栏颜色为灰色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.green(R.color.green));
            // 设置状态栏中的字体颜色为深色
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initOlderAvater();
        initView();
    }
    private void initOlderAvater() {
        OlderViewModel olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.getavatar(auth);//老人头像
        olderViewModel.avatarLiveData.observe(this, new Observer<Avatar>() {
            @Override
            public void onChanged(Avatar avatar) {
                if (avatar!=null)
                {
                    //初始化视图
                    url = avatar.getData();
                    Glide.with(MainActivity3.this)
                            .downloadOnly()
                            .load(url)
                            .into(new CustomTarget<File>() {
                                @Override
                                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                    // 下载完成后的处理
                                    // resource即为下载后的File文件
                                    // 在这里可以进行文件操作，比如复制、移动等
                                    // 例如，将文件复制到指定目录
                                    File cacheDir = getCacheDir();
                                    String fileName = "cached_image.jpg";
                                    imageFile = new File(cacheDir, fileName);
                                    try {
                                        copyFile(resource, imageFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                    // 可选的清理操作
                                }
                            });
                }
            }
        });
    }

    //复制文件
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
    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fragmentList = new ArrayList<>();
        if(imageFile != null && imageFile.exists() && flog == 0) {
            imagePath = imageFile.getAbsolutePath();
        }
        Fragment3 fragment3 = Fragment3.newInstance(imagePath);
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment4());
        fragmentList.add(fragment3);
        MyFragmentPagerAdapter fragmentAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        // 设置TabLayout的TabGravity为GRAVITY_FILL
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // 设置TabLayout的TabMode为MODE_FIXED
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //将图标导入Tablayout
        new TabLayoutMediator(tabLayout,viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(tabIcons[position]);
            }
        }).attach();
        //设置当前位置图标
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //获取总的TabLayout的个数
                int tabCount = tabLayout.getTabCount();
                //遍历选择所有的Tab,如果判断是当前的Tab则进行相关操作，不是则还原操作
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab =tabLayout.getTabAt(i);    //取得tab
                    if (tab.getPosition() == position) {
                        // 设置选中页面对应的Tab的提醒效果，比如修改背景颜色
                        int white = ContextCompat.getColor(MainActivity3.this, R.color.white);
                        int green = ContextCompat.getColor(MainActivity3.this, R.color.green);
                        animateTabBackgroundColor(tabLayout, position,white, green, 1000);
                    } else {
                        // 设置当前页面对应的Tab的图标
                        tab.setIcon(tabIcons[i]);
                    }
                }
            }
        });
    }


    //变色动画
    private void animateTabBackgroundColor(final TabLayout tabLayout, int position, int startColor, int endColor, int animationDuration) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        if (tab != null) {
            View tabView = tab.view;
            if (tabView instanceof CardView) {
                final CardView cardView = (CardView) tabView;
                cardView.clearAnimation();
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
                colorAnimation.setDuration(animationDuration);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        cardView.setCardBackgroundColor((int) animator.getAnimatedValue());
                    }
                });
                colorAnimation.start();
            }
        }
    }
}
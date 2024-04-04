package com.example.Business.dependents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Base.main.Service.Data.older.Avatar;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



import Ui.Show.Three.Fragment3;
import Ui.Show.Two.Fragment2;
import Ui.Show.first.Fragment1;

@Route(path="/dependents/MainActivty2/2")
public class MainActivity2 extends AppCompatActivity {
    private MaterialCardView m1;//三个按钮
    private  MaterialCardView m2;
    private MaterialCardView m3;
    public static  String auth = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyRHRvIjp7InVzZXJJZCI6IjZkODdiZTM3LTI3NjktNDdlYy1hOTY2LTA3YzZjMTFlMjU4ZiIsImlkZW50aWZ5IjoiMiJ9LCJleHAiOjMyODM3NjU2NTV9.xVDdcO38b9lDCz8n89B-6EYsNSDKFXROBOmrgawBkHU";

    private File imageFile;

    public static String imagePath;
    public static int flog=0;
    public static String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOlderAvater();
        initView();
        initState();
        initEvent();
    }

    private void initOlderAvater() {
        OlderViewModel olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.getavatar(auth);//老人头像
        olderViewModel.avatarLiveData.observe(this, new Observer<Avatar>() {
            @Override
            public void onChanged(Avatar avatar) {
                //初始化视图
                url = avatar.getData();
                Glide.with(MainActivity2.this)
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
        m1 = findViewById(R.id.fragment1);
        m2 = findViewById(R.id.fragment2);
        m3 = findViewById(R.id.fragment3);
    }
    //初始化应用状态
    private void initState() {
        // 初始化
        FragmentUtils.replaceFragment(MainActivity2.this, R.id.fl_Demo,
                new Fragment1());

    }
    //设置点击事件
    private void initEvent() {
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.replaceFragment(MainActivity2.this, R.id.fl_Demo,
                        new Fragment1());
                checkState(1);
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.replaceFragment(MainActivity2.this, R.id.fl_Demo,
                        new Fragment2());
                checkState(2);
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageFile != null && imageFile.exists() && flog == 0) {
                    imagePath = imageFile.getAbsolutePath();
                }
                Fragment3 fragment3 = Fragment3.newInstance(imagePath);
                FragmentUtils.replaceFragment(MainActivity2.this, R.id.fl_Demo, fragment3);
                checkState(3);
                checkState(3);
            }
        });
    }
    public void checkState(int index) {
        int white = ContextCompat.getColor(MainActivity2.this, R.color.white);
        int green = ContextCompat.getColor(MainActivity2.this, R.color.green);
        if (index == 1) {
            animateCardBackgroundColor(m2, white, white);
            animateCardBackgroundColor(m3, white, white);
            animateCardBackgroundColor(m1, white, green);
            return;
        }
        if (index == 2) {
            animateCardBackgroundColor(m1, white, white);
            animateCardBackgroundColor(m3, white, white);
            animateCardBackgroundColor(m2, white, green);
            return;
        }
        if (index == 3) {
            animateCardBackgroundColor(m1, white, white);
            animateCardBackgroundColor(m2, white, white);
            animateCardBackgroundColor(m3, white, green);
            return;
        }
    }
    //变色动画
    private  void animateCardBackgroundColor(final MaterialCardView cardView, int startColor, int endColor) {

        // 取消之前的动画以避免动画叠加
        cardView.clearAnimation();
        ValueAnimator colorAnimation = ValueAnimator.ofArgb(startColor, endColor);
        colorAnimation.setDuration(500); // 设置动画持续时间，例如250毫秒
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                cardView.setCardBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }
}
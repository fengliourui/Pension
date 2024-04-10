package com.example.pension;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path ="/app/MainActivity")
public class  MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainthtm);
        // 初始化ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.getInstance().build("/login/MainActivtyttt/1").navigation();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 897 && resultCode == 999){

        }
    }
}
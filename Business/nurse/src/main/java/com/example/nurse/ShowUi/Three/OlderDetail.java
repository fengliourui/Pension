package com.example.nurse.ShowUi.Three;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.Business.elders.R;

public class OlderDetail extends AppCompatActivity {
    ImageView materialCardView;
    ImageView imageView;
    private static final int REQUEST_CODE = 1;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedview1);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
         getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
//        dealWindow();
        initView();

        imagePath = getIntent().getStringExtra("imagePath");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        materialCardView.setImageBitmap(bitmap); // 加载本地图片
        Glide.with(this).load(imagePath).into(materialCardView);

        initClick();
    }
    private void initView() {
        materialCardView = findViewById(R.id.toux1);
        imageView=findViewById(R.id.backButton);
    }

//    private void dealWindow() {
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//           getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        }
//    }
    private void initClick() {
        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OlderDetail.this, Photodetail.class);
                intent.putExtra("imagePath",imagePath);
                startActivityForResult(intent, REQUEST_CODE); // 使用startActivityForResult()方法启动活动
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 在这里处理返回的数据
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // 获取传递的信息
            String imagePath1 = data.getStringExtra("imagePath2");
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath1);
            materialCardView.setImageBitmap(bitmap); // 加载本地图片
            Intent intent = new Intent();
            intent.putExtra("imagePath2",imagePath1);
            setResult(Activity.RESULT_OK, intent);
        }
    }
}
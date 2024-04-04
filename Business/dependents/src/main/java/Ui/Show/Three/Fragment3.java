package Ui.Show.Three;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;


import com.bumptech.glide.Glide;
import com.example.Base.main.Service.Data.older.Message;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.dependents.MainActivity2;
import com.example.Business.dependents.R;

import Ui.Show.Adapter.ViewPagerAdapter;


public class Fragment3 extends Fragment {
    private static final int REQUEST_CODE = 1;


    private static final int REQUEST_CODE_OLDER_DETAIL_1 = 2;
    LinearLayout linearLayout1;//点击展开详细老人信息
    ImageView imageView1;//展示头像

    TextView textView1;//展示名字

    View view;

    String imagePath;
    ViewPager2 viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment31, null);
        dealWindow();
        //获取实例
        initView();
        //处理头像展示
        dealView();
        //处理服务端数据
        getCloudData();

        viewPager.setAdapter(new ViewPagerAdapter(this));
//        设置点击事件
        initClick();
        return view;
    }

    private void getCloudData() {
        OlderViewModel olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.getolder(MainActivity2.auth);//老人信息
        olderViewModel.getUserMess(MainActivity2.auth);
        olderViewModel.getUserMessLiveData.observe(getViewLifecycleOwner(), new Observer<Message>() {
            @Override
            public void onChanged(Message message) {
                textView1.setText(message.getData().getNickname());
            }
        });
    }

    private void dealView() {
        Bundle args = getArguments();
        if (args != null) {
            imagePath = args.getString("imagePath");
            if(imagePath!=null)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                imageView1.setImageBitmap(bitmap); // 加载本地图片
            }
            else
            {
                Glide.with(this)
                        .load(MainActivity2.url) // 加载指定URL的图片
                        .placeholder(R.drawable.qth) // 设置占位图，可选
                        .error(R.drawable.error) // 加载失败时显示的图片，可选
                        .into(imageView1); // 将图片加载到指定的ImageView中
            }
        }
    }

    private void dealWindow() {
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    //获取实例
    private void initView() {
        linearLayout1 = view.findViewById(R.id.tool);
        imageView1 = view.findViewById(R.id.toux);
        textView1= view.findViewById(R.id.name);
        viewPager = view.findViewById(R.id.viewPager);
    }

    private void initClick() {
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Bind.class);
                startActivityForResult(intent, REQUEST_CODE_OLDER_DETAIL_1 );
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Photodetail.class);
                intent.putExtra("imagePath",imagePath);
                startActivityForResult(intent,REQUEST_CODE ); // 使用startActivityForResult()方法启动活动
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Bind.class);
                startActivityForResult(intent, REQUEST_CODE_OLDER_DETAIL_1 );
            }
        });
    }

    public static Fragment3 newInstance(String imagePath) {
        Fragment3 fragment3 = new Fragment3();
        Bundle args = new Bundle();
        args.putString("imagePath", imagePath);
        fragment3.setArguments(args);
        return fragment3;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断请求码和结果码
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // 获取传递的信息
            imagePath = data.getStringExtra("imagePath2");
            MainActivity2.imagePath = imagePath;
            MainActivity2.flog=1;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView1.setImageBitmap(bitmap); // 加载本地图片
        }

    }

}

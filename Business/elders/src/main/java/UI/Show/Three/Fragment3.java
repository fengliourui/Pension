package UI.Show.Three;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.bumptech.glide.Glide;
import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.Data.older.Message;
import com.example.Base.main.Service.Data.older.Older;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;


public class Fragment3 extends Fragment {
    private static final int REQUEST_CODE = 1;


    private static final int REQUEST_CODE_OLDER_DETAIL_1 = 2;
    LinearLayout linearLayout1;//点击展开详细老人信息
    LinearLayout linearLayout2;//点击展开详细老人信息
    LinearLayout linearLayout3;//点击展开详细老人信息
    ImageView imageView1;//展示头像


    TextView textView1;//展示名字

    View view;

    String imagePath;

    Activtyall activtyall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment3, null);

        //获取实例
        initView();
        //处理头像展示
        dealView();
        //处理服务端数据
        getCloudData();
        //设置点击事件
        initClick();
        return view;
    }

    private void getCloudData() {
        OlderViewModel olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.getolder(MainActivity1.auth);//老人信息
        olderViewModel.OlderLiveData.observe(getViewLifecycleOwner(), new Observer<Older>() {
            @Override
            public void onChanged(Older older) {
                //初始化视图
                initialize(older);
            }
        });
        olderViewModel.getUserActivity(MainActivity1.auth,"3");
        olderViewModel.getUserACtivityLiveData.observe(getViewLifecycleOwner(), new Observer<Activtyall>() {
            @Override
            public void onChanged(Activtyall activtyall1) {
                activtyall=activtyall1;
            }
        });
        olderViewModel.getUserMess(MainActivity1.auth);
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
                        .load(MainActivity1.url) // 加载指定URL的图片
                        .placeholder(R.drawable.q) // 设置占位图，可选
                        .error(R.drawable.error) // 加载失败时显示的图片，可选
                        .into(imageView1); // 将图片加载到指定的ImageView中
            }
        }
    }


    //获取实例
    private void initView() {
        linearLayout1 = view.findViewById(R.id.car1);
        linearLayout2 = view.findViewById(R.id.car3);
        linearLayout3 = view.findViewById(R.id.car4);
        imageView1 = view.findViewById(R.id.toux);
        textView1= view.findViewById(R.id.car2);

    }

    private void initClick() {
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OlderDetail.class);
                intent.putExtra("imagePath",imagePath);
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
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity1.class);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity2.class);
                startActivity(intent);
            }
        });
    }
    private void initialize(Older older) {}

    public static Fragment3 newInstance(String imagePath) {
        Fragment3 fragment3 = new Fragment3();
        Bundle args = new Bundle();
        args.putString("imagePath", imagePath);
        fragment3.setArguments(args);
        return fragment3;
    }
    //在其他界面改变头像后将会在这里处理展示
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断请求码和结果码
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // 获取传递的信息
            imagePath = data.getStringExtra("imagePath2");
            MainActivity1.imagePath = imagePath;
            MainActivity1.flog=1;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView1.setImageBitmap(bitmap); // 加载本地图片
        }
        if (requestCode == REQUEST_CODE_OLDER_DETAIL_1  && resultCode == Activity.RESULT_OK) {
            // 获取传递的信息
            imagePath = data.getStringExtra("imagePath2");
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            MainActivity1.imagePath = imagePath;
            MainActivity1.flog=1;
            imageView1.setImageBitmap(bitmap); // 加载本地图片
        }
    }

}

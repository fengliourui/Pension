package UI.Show.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.ViewModel.ImageViewViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;


import java.util.ArrayList;
import java.util.List;

import UI.Adapter.ImageAdapter;

public class Fragment1 extends Fragment {

    View view;
    LinearLayout textview1;
    LinearLayout textview2;
    LinearLayout textview3;

    TextView textView21;
    TextView textView22;
    TextView textView23;

    TextView loadingText;
    private BannerLayout bannerLayout;
    private List<String> bannerImageUrls;

    public static ImageViewViewModel imageViewViewModel;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment1, null);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getActivity().getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getActivity().getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
        //获取实例
        initView();
        //轮播图图片导入
        bannerInit();
        //设置点击事件
        initClick();
        //获取客户端信息，设置RecyclerView
        getCloudData();
        return view;
    }

    private void bannerInit() {
        bannerImageUrls = new ArrayList<>();
        bannerImageUrls.add("https://th.bing.com/th/id/R.142034cef407da71368669ab4c5ff11f?rik=gI3Aazyw6FJUKQ&pid=ImgRaw&r=0");
        bannerImageUrls.add("https://pic.quanjing.com/xx/ta/QJ6146842590.jpg?x-oss-process=style/794ws");
        bannerImageUrls.add("https://img95.699pic.com/photo/40014/9815.jpg_wh860.jpg");
        bannerLayout.setAdapter(new BannerImageAdapter(bannerImageUrls));
    }
    private void getCloudData() {
        imageViewViewModel = new ViewModelProvider(this).get(ImageViewViewModel.class);
         recyclerView = view.findViewById(R.id.recyclerView1);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
// 设置错落效果
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        imageViewViewModel.getImageView("1", "100", MainActivity1.auth);
        imageViewViewModel.ImageviewLiveData.observe(getViewLifecycleOwner(), new Observer<Imageview>() {
            @Override
            public void onChanged(Imageview imageview) {
                loadingText.setVisibility(View.GONE);
                ImageAdapter adapter = new  ImageAdapter(getContext(),imageview);
                recyclerView.setAdapter(adapter); // 设置适配器
            }
        });
    }

    private void initClick() {
            textview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  initClick(1);
                  imageViewViewModel.getImageView("1", "100", MainActivity1.auth);
                    imageViewViewModel.ImageviewLiveData.observe(getViewLifecycleOwner(), new Observer<Imageview>() {
                        @Override
                        public void onChanged(Imageview imageview) {
                            loadingText.setVisibility(View.GONE);
                            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
// 设置错落效果
                            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                            recyclerView.setLayoutManager(layoutManager);
                            ImageAdapter adapter = new  ImageAdapter(getContext(),imageview);
                            recyclerView.setAdapter(adapter); // 设置适配器
                        }
                    });
                }
            });
            textview2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(2);
                  imageViewViewModel.getImageView("2", "100", MainActivity1.auth);
                    imageViewViewModel.ImageviewLiveData.observe(getViewLifecycleOwner(), new Observer<Imageview>() {
                        @Override
                        public void onChanged(Imageview imageview) {
                            loadingText.setVisibility(View.GONE);
                            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
// 设置错落效果
                            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                            recyclerView.setLayoutManager(layoutManager);
                            ImageAdapter adapter = new  ImageAdapter(getContext(),imageview);
                            recyclerView.setAdapter(adapter); // 设置适配器
                        }
                    });

                }
            });
            textview3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(3);
//                  imageViewViewModel.getImageView("1", "100", MainActivity1.auth);
                }
            });
    }

   //获取实例
    private void initView() {
        bannerLayout = view.findViewById(R.id.homepage_banner_layout);
        loadingText = view.findViewById(R.id.loading_text);
        textview1 = view.findViewById(R.id.www1);
        textview2 = view.findViewById(R.id.www2);
        textview3 = view.findViewById(R.id.www3);
        textView21 =view.findViewById(R.id.ttttt1);
        textView22 =view.findViewById(R.id.ttttt2);
        textView23 =view.findViewById(R.id.ttttt3);
    }

    private  void  initClick(int t)
    {
        textView21.setTextSize(18);
        textView22.setTextSize(18);
        textView23.setTextSize(18);

        textView21.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textView22.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textView23.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        switch (t)
        {
            case 1:
                textView21.setTextSize(15);
                textView21.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case 2:
                textView22.setTextSize(15);
                textView22.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case 3:
                textView23.setTextSize(15);
                textView23.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
        }
    }
}

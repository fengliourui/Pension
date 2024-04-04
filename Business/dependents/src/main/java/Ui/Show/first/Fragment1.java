package Ui.Show.first;

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

import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.ViewModel.ImageViewViewModel;
import com.example.Business.dependents.MainActivity2;
import com.example.Business.dependents.R;



import Ui.Show.Adapter.ImageAdapter;


public class Fragment1 extends Fragment {

    View view;
    LinearLayout textview1;
    LinearLayout textview2;
    LinearLayout textview3;
    LinearLayout textview4;
    LinearLayout textview5;
    LinearLayout textview6;
    LinearLayout textview7;
    LinearLayout textview8;

    TextView textView21;
    TextView textView22;
    TextView textView23;
    TextView textView24;
    TextView textView25;
    TextView textView26;
    TextView textView27;
    TextView textView28;

    TextView loadingText;
    public static ImageViewViewModel imageViewViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment11, null);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getActivity().getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getActivity().getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
        //获取实例
        initView();
        //设置点击事件
        initClick();
        //获取客户端信息，设置RecyclerView
        getCloudData();
        return view;
    }

    private void getCloudData() {
        imageViewViewModel = new ViewModelProvider(this).get(ImageViewViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        imageViewViewModel.getImageView("1", "100", MainActivity2.auth);
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
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);
                }
            });
            textview2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(2);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });
            textview3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(3);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });
            textview4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(4);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });
            textview5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(5);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });
            textview6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(6);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });
            textview7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(7);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });
            textview8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initClick(8);
//                  imageViewViewModel.getImageView("1", "100", MainActivity2.auth);

                }
            });

    }

   //获取实例
    private void initView() {

        loadingText = view.findViewById(R.id.loading_text);

            textview1 = view.findViewById(R.id.txtview1);
            textview2 = view.findViewById(R.id.txtview2);
            textview3 = view.findViewById(R.id.txtview3);
            textview4 = view.findViewById(R.id.txtview4);
            textview5 = view.findViewById(R.id.txtview5);
            textview6 = view.findViewById(R.id.txtview6);
            textview7 = view.findViewById(R.id.txtview7);
            textview8 = view.findViewById(R.id.txtview8);
            textView21 = view.findViewById(R.id.tet1);
            textView22 = view.findViewById(R.id.tet2);
            textView23 = view.findViewById(R.id.tet3);
            textView24 = view.findViewById(R.id.tet4);
            textView25 = view.findViewById(R.id.tet5);
            textView26 = view.findViewById(R.id.tet6);
            textView27 = view.findViewById(R.id.tet7);
            textView28 = view.findViewById(R.id.tet8);

    }

    private  void  initClick(int t)
    {
        textView21.setTextSize(12);
        textView22.setTextSize(12);
        textView23.setTextSize(12);
        textView24.setTextSize(12);
        textView25.setTextSize(12);
        textView26.setTextSize(12);
        textView27.setTextSize(12);
        textView28.setTextSize(12);

        textView21.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView22.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView23.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView24.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView25.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView26.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView27.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView28.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
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
            case 4:
                textView24.setTextSize(15);
                textView24.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case 5:
                textView25.setTextSize(15);
                textView25.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case 6:
                textView26.setTextSize(15);
                textView26.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case 7:
                textView27.setTextSize(15);
                textView27.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case 8:
                textView28.setTextSize(15);
                textView28.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        textView21.setTextSize(15);
        textView22.setTextSize(12);
        textView23.setTextSize(12);
        textView24.setTextSize(12);
        textView25.setTextSize(12);
        textView26.setTextSize(12);
        textView27.setTextSize(12);
        textView28.setTextSize(12);

        textView21.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        textView22.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView23.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView24.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView25.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView26.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView27.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        textView28.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
    }

}

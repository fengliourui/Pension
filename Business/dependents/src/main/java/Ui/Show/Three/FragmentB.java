package Ui.Show.Three;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.dependents.MainActivity2;
import com.example.Business.dependents.R;



import Ui.Show.Adapter.ImageAdapter;


public class FragmentB extends Fragment {
    View view;
    LinearLayout linearLayout;
    OlderViewModel olderViewModel;
    RecyclerView recyclerView;
    ImageAdapter adapter;
    public TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragmentbbb, null);
        initView();
        getCloudData();
        return view;
    }
    private void initView() {
        recyclerView = view.findViewById(R.id.tttt2);
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        linearLayout = view.findViewById(R.id.tht);
        textView=view.findViewById(R.id.text1);
    }
    private void getCloudData() {
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        olderViewModel.getGoodView(MainActivity2.auth);
        olderViewModel.goodviewLiveData.observe(getViewLifecycleOwner(), new Observer<Imageview>() {
            @Override
            public void onChanged(Imageview imageview) {
                if(imageview!=null)
                {
                    if(imageview.getImage().getData().size()!=0)
                    {
                        linearLayout.setVisibility(View.GONE);
                    }
                    else
                    {
                        textView.setText("您暂无收藏视频");
                    }
                    adapter = new  ImageAdapter(getContext(),imageview);
                    recyclerView.setAdapter(adapter); // 设置适配器
                }
            }
        });
    }
}

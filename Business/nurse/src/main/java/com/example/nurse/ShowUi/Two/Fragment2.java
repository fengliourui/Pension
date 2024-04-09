package com.example.nurse.ShowUi.Two;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Base.main.Service.Data.add.OlderBind;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.nurse.MainActivity3;
import com.example.nurse.R;
import com.example.nurse.ShowUi.Adapter.BindAdapter;


public class Fragment2 extends Fragment {
    private OlderViewModel olderViewModel;
    private RecyclerView recyclerView;
    TextView textView;

    private BindAdapter adapter;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment22, null);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getActivity().getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getActivity().getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景

        initVIew();
        return view;
    }

    private void initVIew() {
        recyclerView = view.findViewById(R.id.recyclerView3);
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        textView = view.findViewById(R.id.loading_text);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter); // 设置适配器
        olderViewModel.getOlerBind(MainActivity3.auth);
        olderViewModel.GetOlderBindLiveData.observe(getActivity(), new Observer<OlderBind>() {
            @Override
            public void onChanged(OlderBind olderBind) {
                if (olderBind != null) {
                    textView.setVisibility(View.GONE);
                    adapter = new BindAdapter(getContext(), olderBind);
                    recyclerView.setAdapter(adapter); // 设置适配器
                }
            }
        });
    }

}

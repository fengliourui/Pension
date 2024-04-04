package Ui.Show.Two;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.dependents.R;


import Ui.Show.Adapter.BindAdapter;

public class Fragment2 extends Fragment {
    private OlderViewModel olderViewModel;
    private RecyclerView recyclerView;
    private BindAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment21, null);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getActivity().getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getActivity().getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景


//        recyclerView = view.findViewById(R.id.recyclerView3);
//        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
//        recyclerView.setAdapter(adapter); // 设置适配器
        return view;
    }
}

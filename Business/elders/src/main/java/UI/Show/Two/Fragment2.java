package UI.Show.Two;

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

import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;

import UI.Adapter.ActivtyAdapter;


public class Fragment2 extends Fragment {
   public static Activtyall activtyall1;
   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment2, null);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getActivity().getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getActivity().getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
        //下面设置适配器是为了使用recycleView这一个变量，如果不设置一个默认适配其会报错
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView211111);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        // 观察LiveData，更新适配器数据
        OlderViewModel olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.getActivity(MainActivity1.auth);
        TextView loadingText = view.findViewById(R.id.loading_text);
        olderViewModel.ActivityallLiveData.observe(getViewLifecycleOwner(), new Observer<Activtyall>() {
            @Override
            public void onChanged(Activtyall activtyall) {
                loadingText.setVisibility(View.GONE);
                activtyall1=activtyall;
                ActivtyAdapter activtyAdapter = new ActivtyAdapter(activtyall,getContext());
                recyclerView.setAdapter(activtyAdapter); // 设置适配器
            }
        });
        return view;
    }
}

package Ui.Show.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Ui.Show.Three.FragmentB;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 根据位置返回相应的 Fragment
            return new FragmentB();
    }

    @Override
    public int getItemCount() {
        // 返回页面总数
        return 2;
    }
}
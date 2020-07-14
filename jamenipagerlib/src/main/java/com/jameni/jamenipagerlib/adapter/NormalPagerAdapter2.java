package com.jameni.jamenipagerlib.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class NormalPagerAdapter2 extends FragmentStateAdapter {
    private ArrayList<Fragment> list;

    public NormalPagerAdapter2(ArrayList<Fragment> list, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public void update(ArrayList<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
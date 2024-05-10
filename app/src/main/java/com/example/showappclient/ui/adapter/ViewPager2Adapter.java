package com.example.showappclient.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private List<Fragment> fragments;

    public ViewPager2Adapter(FragmentActivity activity, List<Fragment> fragments) {
        super(activity);
        this.fragments = fragments;
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
}

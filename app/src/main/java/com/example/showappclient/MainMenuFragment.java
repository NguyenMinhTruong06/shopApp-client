package com.example.showappclient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.showappclient.ui.adapter.ViewPager2Adapter;
import com.example.showappclient.ui.cart.CartFragment;
import com.example.showappclient.ui.home.HomeFragment;
import com.example.showappclient.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuFragment extends Fragment {

    private ViewPager2 viewPager;
    private BottomNavigationView navigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewPager);
        navigation=view.findViewById(R.id.navigation);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new CartFragment());


        ViewPager2Adapter adapter = new ViewPager2Adapter(getActivity(), fragments);
        viewPager.setAdapter(adapter);
//        viewPager.setUserInputEnabled(false);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navigation.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        navigation.getMenu().findItem(R.id.menu_search).setChecked(true);
                        break;
                    case 2:
                        navigation.getMenu().findItem(R.id.menu_cart).setChecked(true);
                        break;
                }
            }
        });
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_cart:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });
    }
}
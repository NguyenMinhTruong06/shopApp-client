package com.example.showappclient.ui.home;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.showappclient.R;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.ProductResponse;
import com.example.showappclient.ui.adapter.ProductListAdapter;
import com.example.showappclient.ui.auth.login.LoginViewModel;
import com.example.showappclient.ui.auth.signup.SignupFragment;
import com.example.showappclient.ui.cart.CartFragment;
import com.example.showappclient.ui.product.ProductViewModel;
import com.example.showappclient.ui.profile.ProfileFragment;
import com.example.showappclient.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private ProductViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    private RecyclerView recyclerViewNewArrival;
    private ProductListAdapter productListAdapter;
    //private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerViewProduct;
     private ImageView imgProfile;
    public List<Product> products=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel =new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(ProductViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);


        productListAdapter=new ProductListAdapter();
        recyclerViewNewArrival = view.findViewById(R.id.recyclerview_newarrival);
        imgProfile = view.findViewById(R.id.image_profile);
        recyclerViewProduct = view.findViewById(R.id.recyclerview_product);

        recyclerViewProduct.setAdapter(productListAdapter);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getAllProduct(20, 0);
        initVieModel();
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, profileFragment)
                        .commit();
            }
        });

    }

    private void initVieModel(){

        mViewModel.products.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                productListAdapter.setData(productList);


            }
        });

    }
}
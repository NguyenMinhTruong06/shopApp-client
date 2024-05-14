package com.example.showappclient.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showappclient.R;
import com.example.showappclient.localdb.AppDatabase;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Product;
import com.example.showappclient.ui.adapter.CartListAdapter;
import com.example.showappclient.ui.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartListAdapter cartListAdapter;
    private Product product = new Product();
    private List<Product> cartProductList = new ArrayList<>();
    CartViewModel cartViewModel;
    private AppDatabase appDatabase;

    public CartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = new ViewModelProvider(this, new CartViewModelFactory(requireActivity().getApplication())).get(CartViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        cartViewModel.getAllProductInCart();
        AppDatabase appDatabase = AppDatabase.getInstance(requireContext().getApplicationContext());
        cartListAdapter=new CartListAdapter(appDatabase);
        recyclerView = view.findViewById(R.id.recycler_cartItem);
        recyclerView.setAdapter(cartListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cartListAdapter.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(int position) {
                Cart product = cartListAdapter.getProduct(position);
                cartListAdapter.add(product);
            }
        });
        cartListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Cart product = cartListAdapter.getProduct(position);
                cartListAdapter.remove(product);
            }
        });
    }

    private void initViewModel() {
        cartViewModel.products.observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                cartListAdapter.setData(carts);
            }
        });
    }
}
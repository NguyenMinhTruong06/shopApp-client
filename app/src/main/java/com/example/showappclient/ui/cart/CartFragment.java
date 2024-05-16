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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.R;
import com.example.showappclient.localdb.AppDatabase;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Order;
import com.example.showappclient.model.Product;
import com.example.showappclient.ui.adapter.CartListAdapter;
import com.example.showappclient.ui.adapter.OnItemClickListener;
import com.example.showappclient.ui.adapter.OrderListAdapter;
import com.example.showappclient.ui.order.OrderFragment;
import com.example.showappclient.ui.profile.ProfileFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment implements CartListAdapter.OnItemCheckedChangeListener{

    private RecyclerView recyclerView;
    private CartListAdapter cartListAdapter;
    private Product product = new Product();
    private List<Product> cartProductList = new ArrayList<>();
    private TextView tvSum,tvCheckOut;
    CartViewModel cartViewModel;
    private ImageView imgProfile;
    private AppDatabase appDatabase;
    private float totalSum = 0f;
    private OrderListAdapter orderListAdapter;
    private List<Cart> selectedProducts = new ArrayList<>();


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
    public void updateTotal(float totalSum) {
        // Cập nhật giá trị của tvSum
        tvSum.setText("Total Price: " + totalSum);
    }
    @Override
    public void onItemCheckedChange(int position, boolean isChecked) {
        Cart currentProduct = cartListAdapter.getProduct(position);
        double itemPrice = currentProduct.getQuantity() * currentProduct.getPrice();
        currentProduct.setSelected(isChecked);

        if (isChecked) {
            // Nếu checkbox được chọn, cộng giá trị của mục vào tổng giá
            totalSum += itemPrice;


        } else {
            // Nếu checkbox được bỏ chọn, trừ giá trị của mục ra khỏi tổng giá
            totalSum -= itemPrice;

        }
        if (cartListAdapter.isEmpty()) {
            totalSum = 0f;
            tvSum.setText("Total Price: " + totalSum);
        }

        // Cập nhật giá trị của tvSum
        tvSum.setText(totalSum+"₫");
        if (totalSum > 0) {
            tvCheckOut.setClickable(true);
        } else {
            tvCheckOut.setClickable(false);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        tvSum = view.findViewById(R.id.text_total_price);
        cartViewModel.getAllProductInCart();
        AppDatabase appDatabase = AppDatabase.getInstance(requireContext().getApplicationContext());
        cartListAdapter=new CartListAdapter(appDatabase,this);
        recyclerView = view.findViewById(R.id.recycler_cartItem);
        recyclerView.setAdapter(cartListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvCheckOut=view.findViewById(R.id.text_check_out);
        imgProfile = view.findViewById(R.id.image_profile);
        cartListAdapter.setOnItemCheckedChangeListener(this);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileFragment profileFragment = new ProfileFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, profileFragment)
                        .addToBackStack("ProfileFragment")
                        .commit();
            }
        });

        tvCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedProducts.isEmpty()) {
                    Toast.makeText(requireContext(), "Vui lòng chọn sản phẩm trước khi nhấn Mua hàng", Toast.LENGTH_SHORT).show();
                    return;
                }

                    Bundle bundle = new Bundle();
                    bundle.putFloat("total_price", totalSum);
                    bundle.putSerializable("selected_products", (Serializable) selectedProducts);
                OrderFragment orderFragment = new OrderFragment();
                orderFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, orderFragment)
                        .addToBackStack("ProfileFragment")
                        .commit();

            }
        });

        
        cartListAdapter.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                Cart cart = cartListAdapter.getProduct(position);
                cartListAdapter.add(cart);
                cartViewModel.getAllProductInCart();
            }
        });
        cartListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Cart cart = cartListAdapter.getProduct(position);
                cartListAdapter.remove(cart);
                cartViewModel.getAllProductInCart();
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

    @Override
    public void onResume() {
        super.onResume();
        cartViewModel.getAllProductInCart();
    }
    public void updateSelectedProducts(Cart cart, boolean isChecked) {
        if (isChecked) {
            selectedProducts.add(cart);
        } else {
            selectedProducts.remove(cart);
        }
    }
}
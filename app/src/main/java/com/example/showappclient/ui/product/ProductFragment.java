package com.example.showappclient.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.R;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.ProductImage;
import com.example.showappclient.model.ProductOption;
import com.example.showappclient.ui.adapter.CartListAdapter;
import com.example.showappclient.ui.adapter.OnItemClickListener;
import com.example.showappclient.ui.adapter.OptionListAdapter;
import com.example.showappclient.ui.adapter.ProductAdapter;
import com.example.showappclient.ui.cart.CartViewModel;
import com.example.showappclient.ui.cart.CartViewModelFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class ProductFragment extends Fragment {

    private ImageView imageLeft;
    private TextView tvPrice, tvDescription, tvName, tvAddToCart, tvId;
    private ViewPager2 viewpagerProductImg;
    private RecyclerView recyclerViewListOption;
    private ProductAdapter adapterProduct;
    private CartListAdapter cartListAdapter;
    private CartViewModel cartViewModel;
    private CircleIndicator3 circleIndicator;
    private OptionListAdapter optionListAdapter;
    private Product product = new Product();
    private List<Product> cartProductList = new ArrayList<>();
    private int productId = 0;

    private ProductOption selectedProductOption;


    public ProductFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartViewModel = new ViewModelProvider(this, new CartViewModelFactory(requireActivity().getApplication())).get(CartViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterProduct = new ProductAdapter();
        optionListAdapter =new OptionListAdapter();
        viewpagerProductImg = view.findViewById(R.id.viewpager_product_img);
        circleIndicator = view.findViewById(R.id.circleindicator);
        imageLeft = view.findViewById(R.id.image_left);
        tvPrice = view.findViewById(R.id.text_price);
        tvDescription = view.findViewById(R.id.text_ncc);
        tvName = view.findViewById(R.id.text_ma);
        tvAddToCart = view.findViewById(R.id.text_additemtocart);
        tvId = view.findViewById(R.id.text_author);
        recyclerViewListOption = view.findViewById(R.id.recyclerView_options);
        recyclerViewListOption.setAdapter(optionListAdapter);
        recyclerViewListOption.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
        }
        Log.d("ProductFragment", "Options: " + product.getOptions());
        optionListAdapter.setOptions(product.getOptions());
        autoImageSlider(product.getImages());

        tvDescription.setText("Mô tả: " + product.getDescription());
        tvName.setText("Tên sản phẩm: " + product.getName());
        tvId.setText("ID sản phẩm: " + product.getId());



        optionListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedProductOption = optionListAdapter.getOptions(position);
                tvPrice.setText(String.valueOf(selectedProductOption.getPrice())+"₫");
            }
        });

        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(product);
                Toast.makeText(getContext(), "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void initVieModel() {


    }


    public void addToCart(Product product) {
        if (selectedProductOption == null) {
            Toast.makeText(getContext(), "Vui lòng chọn một tùy chọn sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        String imagePath = "";
        if (product.getImages().size() > 0) {
            imagePath = product.getImages().get(0).getImageUrl();
        }
        Cart cart = new Cart();
        cart.setProductName(product.getName());
        cart.setDescription(product.getDescription());
        cart.setPrice(selectedProductOption.getPrice());
        cart.setOption(selectedProductOption.getOption());
        cart.setQuantity(1);
        String productIdString = product.getId();
        int productId = Integer.parseInt(productIdString);
        cart.setProductId(productId);
        cart.setImagePath(imagePath);
        cart.setCreatedAt(LocalDate.now().toString());
        cartViewModel.addToCart(cart);
    }

    private void autoImageSlider(List<ProductImage> images) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewpagerProductImg.getCurrentItem() == images.size() - 1) {
                    viewpagerProductImg.setCurrentItem(0);
                } else {
                    viewpagerProductImg.setCurrentItem(viewpagerProductImg.getCurrentItem() + 1);
                }
            }
        };
        adapterProduct.setData(images);
        viewpagerProductImg.setAdapter(adapterProduct);
        circleIndicator.setViewPager(viewpagerProductImg);
        viewpagerProductImg.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

    }
}
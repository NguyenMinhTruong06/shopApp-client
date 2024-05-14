package com.example.showappclient.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.showappclient.R;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.ProductImage;
import com.example.showappclient.ui.adapter.CartListAdapter;
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
    private ProductAdapter adapterProduct;
    private CartListAdapter cartListAdapter;
    private CartViewModel cartViewModel;
    private CircleIndicator3 circleIndicator;
    private Product product = new Product();
    private List<Product> cartProductList = new ArrayList<>();


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
        viewpagerProductImg = view.findViewById(R.id.viewpager_product_img);
        circleIndicator = view.findViewById(R.id.circleindicator);
        imageLeft = view.findViewById(R.id.image_left);
        tvPrice = view.findViewById(R.id.text_price);
        tvDescription = view.findViewById(R.id.text_ncc);
        tvName = view.findViewById(R.id.text_ma);
        tvAddToCart = view.findViewById(R.id.text_additemtocart);
        tvId = view.findViewById(R.id.text_author);
        Bundle bundle = getArguments();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
        }
        autoImageSlider(product.getImages());
        tvPrice.setText(String.valueOf(product.getPrice()) + "₫");
        tvDescription.setText(product.getDescription());
        tvName.setText(product.getName());
        tvId.setText(product.getId());


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
            }
        });
    }


    public void initVieModel() {


    }

    public void addToCart(Product product) {

        String imagePath="";
        if(product.getImages().size()>0){
            imagePath=product.getImages().get(0).getImageUrl();
        }
        Cart cart = new Cart();
        cart.setProductName(product.getName());
        cart.setDescription(product.getDescription());
        cart.setPrice(product.getPrice());
        cart.setQuantity(1);
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
package com.example.showappclient.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.showappclient.R;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.ProductImage;
import com.example.showappclient.ui.adapter.ProductAdapter;
import com.example.showappclient.ui.adapter.ProductListAdapter;

import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class ProductFragment extends Fragment {

    private ImageView imageLeft;
    private TextView tvPrice,tvDescription,tvName;
    private ViewPager2 viewpagerProductImg;
    private ProductAdapter adapterProduct;
    private CircleIndicator3 circleIndicator;
    private Product product = new Product();

    public ProductFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        tvName= view.findViewById(R.id.text_ma);
        Bundle bundle = getArguments();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
        }
        autoImageSlider(product.getImages());
        tvPrice.setText(String.valueOf(product.getPrice())+"₫");
        tvDescription.setText(product.getDescription());
        tvName.setText(product.getName());

        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public void initVieModel() {


    }

    private void autoImageSlider(List<ProductImage> images) {
//        if (binding != null) {
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
//        }
    }
}
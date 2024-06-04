package com.example.showappclient.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.R;
import com.example.showappclient.model.Category;
import com.example.showappclient.model.Product;
import com.example.showappclient.ui.adapter.ProductListAdapter;

import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private EditText editSearch;
    private ImageView imgSearch;
    private TextView tvCategory;
    private ProductListAdapter productListAdapter;
    private RecyclerView recyclerViewProduct;
    private List<Category> categoryList;
    private int selectedCategoryId =-1;




    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(SearchViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editSearch= view.findViewById(R.id.edit_search);
        imgSearch= view.findViewById(R.id.image_seach);
        tvCategory =view.findViewById(R.id.text_category);
        recyclerViewProduct = view.findViewById(R.id.recycler_product);
        productListAdapter = new ProductListAdapter();
        recyclerViewProduct.setAdapter(productListAdapter);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getCategory();

        mViewModel.category.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                    categoryList = categories;
                for (Category category : categories) {
                    Log.d("Category", category.getName());
                }
            }
        });
        mViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (products != null) {
                    productListAdapter.setData(products);
                } else {
                    // Xử lý khi không nhận được danh sách sản phẩm
                }
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String keyword = editSearch.getText().toString();
                if (selectedCategoryId != -1) {
                    int limit =10;
                    int page =0;
                    // Gọi API getAllProducts với id của Category được chọn
                    mViewModel.fetchProductsByCategoryId(limit,page,keyword,selectedCategoryId);


                } else {
                    Toast.makeText(getContext(), "Please select a category first", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryList != null && !categoryList.isEmpty()) {
                    showCategoryMenu(view, categoryList);
                } else {
                    //Toast.makeText(getContext(), "No categories available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showCategoryMenu(View view, List<Category> categories) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        for (int i = 0; i < categories.size(); i++) {
            popupMenu.getMenu().add(0, i, 0, categories.get(i).getName());
        }
        popupMenu.setOnMenuItemClickListener(item -> {
            Category selectedCategory = categories.get(item.getItemId());
            tvCategory.setText(selectedCategory.getName());
            selectedCategoryId = Integer.parseInt((selectedCategory.getId()));
            return true;
        });
        popupMenu.show();
    }

}
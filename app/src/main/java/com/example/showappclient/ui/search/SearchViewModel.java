package com.example.showappclient.ui.search;

import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.Category;

import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.CategoryResponse;

import com.example.showappclient.model.response.ProductResponse;
import com.example.showappclient.repository.category.CategoryReponsitory;
import com.example.showappclient.repository.category.CategoryReponsitoryImpl;
import com.example.showappclient.repository.product.ProductRepository;
import com.example.showappclient.repository.product.ProductRepositoryImpl;


import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    public MutableLiveData<List<Category>> category = new MutableLiveData<>();
    CategoryReponsitory categoryReponsitory = new CategoryReponsitoryImpl();
    ProductRepository productRepository = new ProductRepositoryImpl();
    public MutableLiveData<List<Product>> products = new MutableLiveData<>();
    public LiveData<List<Product>> getProducts() {
        return products;
    }


    public void getCategory() {
        categoryReponsitory.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    category.postValue(response.body());
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }


        });
    }
    public void fetchProductsByCategoryId(int limit, int page, String keyword,int categoryId) {
        productRepository.getProductByKeywordAndCategoryId(limit,page,keyword,categoryId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.postValue(response.body().getProducts());
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }


        });
    }
}
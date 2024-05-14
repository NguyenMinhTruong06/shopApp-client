package com.example.showappclient.ui.product;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.ProductResponse;
import com.example.showappclient.repository.cart.CartRepository;
import com.example.showappclient.repository.cart.CartRepositoryImpl;
import com.example.showappclient.repository.product.ProductRepository;
import com.example.showappclient.repository.product.ProductRepositoryImpl;
import com.example.showappclient.ui.cart.CartViewModel;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    public MutableLiveData<List<Product>> products = new MutableLiveData<>();
    ProductRepository productRepository = new ProductRepositoryImpl();


    public LiveData<List<Product>> getProducts() {
        return products;
    }


    public void setProducts(List<Product> productList) {
        products.setValue(productList);
    }

    public void getAllProduct(int limit, int page) {
        productRepository.getAllProduct(limit, page).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    products.postValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }

}
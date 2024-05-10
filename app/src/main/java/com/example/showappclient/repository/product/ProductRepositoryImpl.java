package com.example.showappclient.repository.product;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.response.ProductResponse;

import retrofit2.Call;

public class ProductRepositoryImpl implements ProductRepository{
    @Override
    public Call<ProductResponse> getAllProduct(int limit, int page) {
        return RetrofitClient.getApiService().getAllProduct(limit, page);
    }
}

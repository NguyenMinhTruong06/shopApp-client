package com.example.showappclient.repository.product;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.response.ProductResponse;

import retrofit2.Call;

public class ProductRepositoryImpl implements ProductRepository{
    @Override
    public Call<ProductResponse> getAllProduct(int limit, int page) {
        return RetrofitClient.getApiService().getAllProduct(limit, page);
    }

    @Override
    public Call<ProductResponse> getProductByKeywordAndCategoryId(int limit, int page, String keyword, int categoryId) {
        return RetrofitClient.getApiService().getProductByKeywordAndCategoryId(limit,page,keyword,categoryId);
    }

    @Override
    public Call<ProductResponse> getProduct(int productId) {
        return RetrofitClient.getApiService().getProductById(productId);
    }
}

package com.example.showappclient.repository.product;

import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;
import com.example.showappclient.model.response.ProductResponse;

import retrofit2.Call;

public interface ProductRepository {
    Call<ProductResponse> getAllProduct(int limit, int page);
}

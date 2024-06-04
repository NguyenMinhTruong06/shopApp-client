package com.example.showappclient.repository.order;

import com.example.showappclient.api.RetrofitClient;

import com.example.showappclient.model.Order;
import com.example.showappclient.model.request.OrderRequest;
import com.example.showappclient.model.response.OrderResponse;

import java.util.List;

import retrofit2.Call;

public class OrderRepositoryImpl implements OrderRepository{
    @Override
    public Call<OrderResponse> createOrder(OrderRequest orderRequest) {
        return RetrofitClient.getApiService().createOrder(orderRequest);
    }

    @Override
    public Call<List<OrderResponse>> getOrder(Long userId) {
        return RetrofitClient.getApiService().getOrder(userId);
    }
}

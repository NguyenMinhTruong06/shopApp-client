package com.example.showappclient.repository.order;

import com.example.showappclient.model.Order;
import com.example.showappclient.model.request.OrderRequest;
import com.example.showappclient.model.response.OrderResponse;

import java.util.List;

import retrofit2.Call;

public interface OrderRepository {
    public Call<OrderResponse> createOrder(OrderRequest orderRequest);
    public Call<List<OrderResponse>> getOrder(Long userId);
}

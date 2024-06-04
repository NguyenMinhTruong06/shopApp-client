package com.example.showappclient.repository.orderdetail;

import com.example.showappclient.model.request.OrderDetailRequest;

import com.example.showappclient.model.response.OrderDetailResponse;
import com.example.showappclient.model.response.OrderResponse;

import retrofit2.Call;

public interface OrderDetailRepository {
    public Call<OrderDetailResponse> createOrderDetail(OrderDetailRequest orderDetailRequest);

}

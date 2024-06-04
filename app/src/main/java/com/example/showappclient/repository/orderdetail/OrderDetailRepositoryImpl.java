package com.example.showappclient.repository.orderdetail;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.request.OrderDetailRequest;

import com.example.showappclient.model.response.OrderDetailResponse;
import com.example.showappclient.model.response.OrderResponse;

import retrofit2.Call;

public class OrderDetailRepositoryImpl implements OrderDetailRepository{
    @Override
    public Call<OrderDetailResponse> createOrderDetail(OrderDetailRequest orderDetailRequest) {
        return RetrofitClient.getApiService().createOrderDetail(orderDetailRequest);
    }
}

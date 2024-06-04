package com.example.showappclient.ui.orderdetail;

import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.Order;
import com.example.showappclient.model.request.OrderRequest;
import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.repository.order.OrderRepository;
import com.example.showappclient.repository.order.OrderRepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailViewModel extends ViewModel {
    public OrderRepository orderRepository = new OrderRepositoryImpl();





}
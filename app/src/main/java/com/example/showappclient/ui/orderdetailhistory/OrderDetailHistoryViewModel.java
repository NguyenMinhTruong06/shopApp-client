package com.example.showappclient.ui.orderdetailhistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.repository.order.OrderRepository;
import com.example.showappclient.repository.order.OrderRepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailHistoryViewModel extends ViewModel {
    public OrderRepository orderRepository = new OrderRepositoryImpl();
    private MutableLiveData<OrderResponse> orderLiveData = new MutableLiveData<>();

    // Getter cho LiveData để Fragment có thể quan sát dữ liệu
    public LiveData<OrderResponse> getOrderLiveData() {
        return orderLiveData;
    }

    public void getOrderById(Long orderId){
        orderRepository.getOrderById(orderId).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {

                    OrderResponse orderResponse = response.body();

                    // Cập nhật LiveData với đơn hàng nhận được
                    orderLiveData.setValue(orderResponse);
                    }
                }


            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });
    }



}
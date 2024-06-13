package com.example.showappclient.ui.orderhistory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.Order;

import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.repository.order.OrderRepository;
import com.example.showappclient.repository.order.OrderRepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryViewModel extends ViewModel {
    public OrderRepository orderRepository = new OrderRepositoryImpl();
    public MutableLiveData<List<OrderResponse>> orderLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorMessageLiveData = new MutableLiveData<>();



    public void getOrder(Long userId){
        orderRepository.getOrder(userId).enqueue(new Callback<List<OrderResponse>>() {
            @Override
            public void onResponse(Call<List<OrderResponse>> call, Response<List<OrderResponse>> response) {
                if (response.isSuccessful()) {
                    List<OrderResponse> orders = response.body();
                    if (orders != null) {
                        orderLiveData.postValue(orders); // Cập nhật LiveData với dữ liệu trả về
                    }
                } else {
                    errorMessageLiveData.setValue("Có lỗi xảy ra, vui lòng thử lại sau"); // Xử lý khi có lỗi trong quá trình gọi API
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponse>> call, Throwable t) {
                Log.d("ERR", t.getMessage());
            }
        });
    }
}
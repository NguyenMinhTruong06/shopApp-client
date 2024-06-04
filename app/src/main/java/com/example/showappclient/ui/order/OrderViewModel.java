package com.example.showappclient.ui.order;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Callback;

import com.example.showappclient.model.Product;
import com.example.showappclient.model.User;

import com.example.showappclient.model.request.OrderDetailRequest;
import com.example.showappclient.model.request.OrderRequest;


import com.example.showappclient.model.response.OrderDetailResponse;
import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.repository.order.OrderRepository;
import com.example.showappclient.repository.order.OrderRepositoryImpl;
import com.example.showappclient.repository.orderdetail.OrderDetailRepository;
import com.example.showappclient.repository.orderdetail.OrderDetailRepositoryImpl;
import com.example.showappclient.repository.user.UserRepository;
import com.example.showappclient.repository.user.UserRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
   public MutableLiveData<User> user = new MutableLiveData<>();
    UserRepository userRepository = new UserRepositoryImpl();

    OrderRepository orderRepository = new OrderRepositoryImpl();
    OrderDetailRepository orderDetailRepository = new OrderDetailRepositoryImpl() ;
    public MutableLiveData<Boolean> apiCallSuccess = new MutableLiveData<>();

    private final MutableLiveData<String> recipientName = new MutableLiveData<>();
    private final MutableLiveData<String> recipientPhone = new MutableLiveData<>();
    private final MutableLiveData<String> recipientAddress = new MutableLiveData<>();
    public MutableLiveData<OrderResponse> orderResponse = new MutableLiveData<>();
    public MutableLiveData<OrderDetailResponse> orderDetailReponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Map<String, String>> message = new MutableLiveData<>();

    // Getters for the LiveData
    public LiveData<String> getRecipientName() {
        return recipientName;
    }

    public LiveData<String> getRecipientPhone() {
        return recipientPhone;
    }

    public LiveData<String> getRecipientAddress() {
        return recipientAddress;
    }

    // Method to update recipient info
    public void setRecipientInfo(String newRecipientName, String newRecipientPhone, String newRecipientAddress) {
        recipientName.setValue(newRecipientName);
        recipientPhone.setValue(newRecipientPhone);
        recipientAddress.setValue(newRecipientAddress);
    }

    public void getUser() {
        userRepository.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public void createOrder(OrderRequest orderRequest){
        orderRepository.createOrder(orderRequest).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    orderResponse.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });
    }
    public void createOrderDetail(OrderDetailRequest orderDetailRequest){
        orderDetailRepository.createOrderDetail(orderDetailRequest).enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                if (response.isSuccessful()) {
                    orderDetailReponseMutableLiveData.postValue(response.body());
                    Map<String, String> data = new HashMap<>();
                    data.put("successful","call api success");
                    message.postValue(data);
                    Log.d("call api createOrder","success");
                    apiCallSuccess.postValue(true);
                }
                else {
                    apiCallSuccess.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                Log.d("call api createOrder","failure");
                apiCallSuccess.postValue(false);
            }
        });
    }

}
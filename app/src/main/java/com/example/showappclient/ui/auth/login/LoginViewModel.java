package com.example.showappclient.ui.auth.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.ProductResponse;
import com.example.showappclient.repository.user.UserRepository;
import com.example.showappclient.repository.user.UserRepositoryImpl;
import com.example.showappclient.repository.product.ProductRepository;
import com.example.showappclient.repository.product.ProductRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<Map<String, String>> message = new MutableLiveData<>();


    UserRepository userRepository = new UserRepositoryImpl();




    public void login(UserRequest userRequest) {
        userRepository.login(userRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    // Gọi callback onResponse và log token
                    String token = response.body().token;
                    Map<String, String> data = new HashMap<>();
                    data.put("successful", token);
                    message.postValue(data);
                    Log.i("TOKEN", token);
                } else {
                    // Gọi callback onFailure với thông báo lỗi
                    Map<String, String> data = new HashMap<>();
                    data.put("failure", "wrong phone number or password");
                    message.postValue(data);
                    Log.e("Login", "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Map<String, String> data = new HashMap<>();
                data.put("failure", "Failed to login");
                message.postValue(data);
                Log.i("Login", "Failed to login", t);
            }
        });
    }
}
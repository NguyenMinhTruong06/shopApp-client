package com.example.showappclient.ui.auth.signup;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.R;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;
import com.example.showappclient.repository.user.UserRepository;
import com.example.showappclient.repository.user.UserRepositoryImpl;
import com.example.showappclient.ui.auth.login.LoginFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel extends ViewModel {
    MutableLiveData<Map<String, String>> message = new MutableLiveData<>();
    UserRepository userRepository = new UserRepositoryImpl();
    public void signup(UserRequest userRequest){
        userRepository.signup(userRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()){
                    String ok = "ok";
                    Map<String, String> data = new HashMap<>();
                    data.put("successful",ok);
                    message.postValue(data);


                }
                else {
                    // Gọi callback onFailure với thông báo lỗi
                    Map<String, String> data = new HashMap<>();
                    data.put("failure", "Lỗi không thể đăng ký");
                    message.postValue(data);
                    Log.e("Login", "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Map<String, String> data = new HashMap<>();
                data.put("failure", "wrong phone number or password");
                message.postValue(data);
                Log.e("Signup", "Unsuccessful response: " );
            }
        });
    }
}

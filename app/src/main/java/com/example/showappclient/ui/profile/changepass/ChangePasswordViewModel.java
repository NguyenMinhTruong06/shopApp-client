package com.example.showappclient.ui.profile.changepass;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.MainMenuFragment;
import com.example.showappclient.R;
import com.example.showappclient.model.request.ChangePasswordRequest;
import com.example.showappclient.model.response.AuthResponse;
import com.example.showappclient.repository.changepassword.ChangePasswordRepository;
import com.example.showappclient.repository.changepassword.ChangePasswordRepositoryImpl;
import com.example.showappclient.repository.user.UserRepository;
import com.example.showappclient.repository.user.UserRepositoryImpl;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordViewModel extends ViewModel {
    MutableLiveData<Map<String, String>> message = new MutableLiveData<>();
    ChangePasswordRepository changePasswordRepository = new ChangePasswordRepositoryImpl();
    public void update(ChangePasswordRequest changePasswordRequest){
        changePasswordRepository.updatePass(changePasswordRequest).enqueue(new Callback<AuthResponse>(){

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
                    data.put("failure", "Lỗi không thể cập nhật");
                    message.postValue(data);
                    Log.e("Login", "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Map<String, String> data = new HashMap<>();
                data.put("failure", "Không thể gửi Resquest");
                message.postValue(data);
                Log.e("Update", "Unsuccessful response: " );
            }
        });
    }

    // TODO: Implement the ViewModel
}
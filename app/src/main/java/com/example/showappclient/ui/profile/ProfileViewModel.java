package com.example.showappclient.ui.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.User;

import com.example.showappclient.repository.user.UserRepository;
import com.example.showappclient.repository.user.UserRepositoryImpl;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    public MutableLiveData<User> user = new MutableLiveData<>();
    UserRepository userRepository = new UserRepositoryImpl();

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
    // TODO: Implement the ViewModel
}
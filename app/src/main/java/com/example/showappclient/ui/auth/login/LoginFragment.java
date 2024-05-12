package com.example.showappclient.ui.auth.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.MainActivity;
import com.example.showappclient.MainMenuFragment;
import com.example.showappclient.R;
import com.example.showappclient.api.RetrofitClient;

import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.Product;
import com.example.showappclient.ui.auth.signup.SignupFragment;
import com.example.showappclient.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginFragment extends Fragment {


    private LoginViewModel mViewModel;
    private boolean checkVisible=false;
    private List<Product> products=new ArrayList<>();

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel =new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(LoginViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editPhone = view.findViewById(R.id.edit_username);
        EditText editPassword = view.findViewById(R.id.edit_password);
        Button btnLogin = view.findViewById(R.id.button_login);
        TextView register = view.findViewById(R.id.text_register);
        ImageView showPassword = view.findViewById(R.id.image_eye);
        initVieModel();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber=editPhone.getText().toString();
                String password=editPassword.getText().toString();

//                mViewModel.login(new UserRequest(phoneNumber, password));
                MainMenuFragment mainMenuFragment=new MainMenuFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, mainMenuFragment)
                        .commit();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupFragment signupFragment = new SignupFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, signupFragment)
                        .commit();
            }
            });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursorPosition = editPassword.getSelectionEnd();
                if (!checkVisible) {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    checkVisible = true;
                    showPassword.setImageResource(R.drawable.ic_hide_eye);
                } else {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    checkVisible = false;
                    showPassword.setImageResource(R.drawable.ic_visible_eye);
                }
                if (cursorPosition >= 0) {
                    editPassword.setSelection(cursorPosition);
                }
            }
        });


    }
    private void initVieModel(){
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> data) {
                if(data.get("successful")!=null){
//                    RetrofitClient.updateAccessToken(data.get("successful"));

                    MainMenuFragment mainMenuFragment=new MainMenuFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root, mainMenuFragment)
                            .commit();

                }else{
                    LoginFragment loginFragment = new LoginFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root, loginFragment)
                            .commit();
                    Toast.makeText(getContext(), data.get("failure"), Toast.LENGTH_SHORT).show();
                }
//                        mViewModel.message.removeObservers(getViewLifecycleOwner());
            }
        });

    }
}
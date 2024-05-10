package com.example.showappclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.root,loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
//
//       SignupFragment signupFragment = new SignupFragment();
//
//        transaction.replace(R.id.root,signupFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();


//        HomeFragment homeFragment = new HomeFragment();
//
//        transaction.replace(R.id.root, homeFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();


    }
}
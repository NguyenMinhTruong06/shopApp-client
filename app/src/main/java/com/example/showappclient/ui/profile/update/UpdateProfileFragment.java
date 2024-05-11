package com.example.showappclient.ui.profile.update;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.R;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.profile.ProfileFragment;

import java.util.Date;
import java.util.Map;

public class UpdateProfileFragment extends Fragment {

    private UpdateProfileViewModel mViewModel;
    private EditText edtName, edtDob, edtEmail, edtAddress;
     private TextView textUpdate;


    public static UpdateProfileFragment newInstance() {
        return new UpdateProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtName=view.findViewById(R.id.edit_fullname);
        edtEmail=view.findViewById(R.id.edit_email);
        edtAddress = view.findViewById(R.id.edit_address);
        edtDob = view.findViewById(R.id.edit_dob);
        textUpdate = view.findViewById(R.id.text_update_profile);
        textUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String dateOfBirth = edtDob.getText().toString();
                String address = edtAddress.getText().toString();
                if(name.isEmpty()||dateOfBirth.isEmpty()||address.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                mViewModel.update(new UserRequest(name,dateOfBirth,address));
                initVieModel();

            }
        });
    }

    public void initVieModel(){
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> data) {
                if (data.get("successful") != null) {

                    ProfileFragment profileFragment = new ProfileFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root, profileFragment)
                            .commit();
                    Toast.makeText(getContext(), data.get("successful"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), data.get("failure"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
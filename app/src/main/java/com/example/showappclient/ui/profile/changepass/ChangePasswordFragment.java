package com.example.showappclient.ui.profile.changepass;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.MainMenuFragment;
import com.example.showappclient.R;
import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.User;
import com.example.showappclient.model.request.ChangePasswordRequest;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;
import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.product.ProductFragment;
import com.example.showappclient.ui.profile.ProfileFragment;

import java.util.Map;

public class ChangePasswordFragment extends Fragment {

    private ChangePasswordViewModel mViewModel;
    private EditText edtPass, edtNewPass, edtConfirmNewPass;
    private TextView tvUpdatePassword;
    private ImageView imgLeft;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtPass = view.findViewById(R.id.edit_current_pass);
        edtNewPass = view.findViewById(R.id.edit_new_pass);
        edtConfirmNewPass = view.findViewById(R.id.edit_confirm);
        tvUpdatePassword = view.findViewById(R.id.text_update_password);
        imgLeft = view.findViewById(R.id.image_left);
        initViewModel();
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        tvUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edtPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String confirmPass = edtConfirmNewPass.getText().toString();
                if (pass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                mViewModel.update(new ChangePasswordRequest(pass, newPass, confirmPass));

            }
        });


    }

    private void initViewModel() {
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> data) {
                if (data.get("successful") != null) {
                    Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    ProfileFragment fragment = new ProfileFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.root, fragment).commit();
                } else {
                    Toast.makeText(getContext(), data.get("failure"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
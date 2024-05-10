package com.example.showappclient.ui.auth.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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

import com.example.showappclient.R;
import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.checkPhonenumber;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.auth.login.LoginViewModel;

import java.util.Map;


public class SignupFragment extends Fragment {
    EditText editName, editPhone, editPassword, editConfirm;
    Button buttonRegister;
    ImageView showPassword, showPassword1;
    TextView textLogin;
    public int role = 1;
    private SignupViewModel mViewModel;
    private boolean checkVisible = false;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(SignupViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);


    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editName = view.findViewById(R.id.edit_name);
        editPhone = view.findViewById(R.id.edit_phone);
        editPassword = view.findViewById(R.id.edit_password);
        editConfirm = view.findViewById(R.id.edit_confirmPassword);
        buttonRegister = view.findViewById(R.id.button_register);
        showPassword = view.findViewById(R.id.image_eye_password);
        showPassword1 = view.findViewById(R.id.image_eye_confirmPassword);
        textLogin = view.findViewById(R.id.text_login);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, loginFragment)
                        .commit();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editPhone.getText().toString();
                String password = editPassword.getText().toString();
                String name = editName.getText().toString();
                String retypePassword = editConfirm.getText().toString();

                if (phoneNumber.isEmpty() || password.isEmpty() || name.isEmpty() || retypePassword.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if any field is empty
                }

                // Validate phone number only if not empty
                if (!phoneNumber.isEmpty() && !checkPhonenumber.isValidPhoneNumber(phoneNumber)) {
                    Toast.makeText(getContext(), "Số điện thoại không đúng", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if phone number is invalid
                }

                // Check password match only if all fields are not empty and phone number is valid
                if (!password.equals(retypePassword)) {
                    Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if passwords don't match
                }
                mViewModel.signup(new UserRequest(phoneNumber, password, name, role, retypePassword));

                initVieModel();


            }
        });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        showPassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show1();
            }
        });

    }

    private void initVieModel() {
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> data) {
                if (data.get("successful") != null) {
//                    RetrofitClient.updateAccessToken(data.get("successful"));
                    LoginFragment loginFragment = new LoginFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.root, loginFragment)
                            .commit();
                    Toast.makeText(getContext(), data.get("successful"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), data.get("failure"), Toast.LENGTH_SHORT).show();
                }
//                        mViewModel.message.removeObservers(getViewLifecycleOwner());
            }
        });
    }

    private void show() {

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

    private void show1() {

        int cursorPosition = editConfirm.getSelectionEnd();
        if (!checkVisible) {
            editConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            checkVisible = true;
            showPassword1.setImageResource(R.drawable.ic_hide_eye);
        } else {
            editConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            checkVisible = false;
            showPassword1.setImageResource(R.drawable.ic_visible_eye);
        }
        if (cursorPosition >= 0) {
            editConfirm.setSelection(cursorPosition);
        }
    }


}
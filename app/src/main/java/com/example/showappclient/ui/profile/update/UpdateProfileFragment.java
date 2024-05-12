package com.example.showappclient.ui.profile.update;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.MainMenuFragment;
import com.example.showappclient.R;
import com.example.showappclient.model.User;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.product.ProductViewModel;
import com.example.showappclient.ui.profile.ProfileFragment;
import com.example.showappclient.ui.profile.ProfileViewModel;
import com.example.showappclient.util.FormatDate;

import java.util.Date;
import java.util.Map;

public class UpdateProfileFragment extends Fragment {

    private UpdateProfileViewModel mViewModel;
    private ProfileViewModel profileViewModel;
    private EditText edtName, edtDob, edtAddress, edtPhone;
    private TextView textUpdate;
    private ImageView imgLeft;


    public static UpdateProfileFragment newInstance() {
        return new UpdateProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(ProfileViewModel.class);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(UpdateProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgLeft = view.findViewById(R.id.image_left);
        edtName = view.findViewById(R.id.edit_fullname);
        edtAddress = view.findViewById(R.id.edit_address);
        edtDob = view.findViewById(R.id.edit_dob);
        edtPhone = view.findViewById(R.id.edit_phone);
        textUpdate = view.findViewById(R.id.text_update_profile);
        initVieModel();
        profileViewModel.getUser();
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtDob.getText().toString().isEmpty()) {
                    String[] date = edtDob.getText().toString().split("-");
                    int dayOfMonth = Integer.parseInt(date[2]);
                    int month = Integer.parseInt(date[1]) - 1;
                    int year = Integer.parseInt(date[0]);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            requireContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String dateOfBirth = dayOfMonth + "/" + (month + 1) + "/" + year;
                                    edtDob.setText(new FormatDate().formatDateOfBirth(dateOfBirth));
                                }
                            }, year, month, dayOfMonth
                    );
                    datePickerDialog.show();
                }
            }
        });

        textUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String dateOfBirth = edtDob.getText().toString();
                String address = edtAddress.getText().toString();
                if (name.isEmpty() || dateOfBirth.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                mViewModel.update(new UserRequest(name, dateOfBirth, address));
            }
        });
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public void initVieModel() {
        profileViewModel.user.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                edtName.setText(user.getFullName());
                edtPhone.setText(user.getPhoneNumber());
                edtAddress.setText(user.getAddress());
                edtDob.setText(user.getDateOfBirth());
            }
        });
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> data) {
                if (data.get("successful") != null) {
                    requireActivity().getSupportFragmentManager().popBackStack();
                    Toast.makeText(getContext(), data.get("successful"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), data.get("failure"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
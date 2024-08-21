package com.example.showappclient.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.showappclient.MainMenuFragment;
import com.example.showappclient.R;
import com.example.showappclient.model.User;
import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.home.HomeFragment;
import com.example.showappclient.ui.orderhistory.OrderHistoryFragment;
import com.example.showappclient.ui.product.ProductViewModel;
import com.example.showappclient.ui.profile.changepass.ChangePasswordFragment;
import com.example.showappclient.ui.profile.update.UpdateProfileFragment;

import retrofit2.Call;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private LinearLayout textLogout,textChangePassword,textOrderHistory;
    private TextView tvUserName, tvPhoneNumber;
    private ImageView imgLeft, imageUpdate;
    int userId=-1;


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel =new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textLogout = view.findViewById(R.id.text_logout);
        imgLeft = view.findViewById(R.id.image_left);
        imageUpdate = view.findViewById(R.id.image_update);
        tvUserName=view.findViewById(R.id.text_name);
        tvPhoneNumber=view.findViewById(R.id.text_phone);
        textChangePassword=view.findViewById(R.id.text_change_password);
        textOrderHistory = view.findViewById(R.id.linear_my_order);

        initViewModel();
        mViewModel.getUser();

        textOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("userId",userId);
                orderHistoryFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root,orderHistoryFragment)
                        .addToBackStack("orderHistoryFragment")
                        .commit();
            }
        });
        textChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordFragment changePasswordFragmentProfileFragment = new ChangePasswordFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, changePasswordFragmentProfileFragment)
                        .addToBackStack("changePasswordFragmentProfileFragment")
                        .commit();
            }
        });
        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateProfileFragment updateProfileFragment = new UpdateProfileFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, updateProfileFragment)
                        .addToBackStack("ProfileFragment")
                        .commit();

            }
        });
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        textLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog();

            }
        });


    }

    private void initViewModel() {
        mViewModel.user.observe(getViewLifecycleOwner(), new Observer<User>() {

            @Override
            public void onChanged(User user) {
                userId = Math.toIntExact(user.getId());
                tvUserName.setText(user.getFullName());
                tvPhoneNumber.setText(user.getPhoneNumber());
            }
        });
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn thoát?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginFragment loginFragment = new LoginFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, loginFragment)
                        .commit();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
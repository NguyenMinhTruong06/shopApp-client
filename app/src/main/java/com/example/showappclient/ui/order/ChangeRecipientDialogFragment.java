package com.example.showappclient.ui.order;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.showappclient.R;


public class ChangeRecipientDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_change_recipient, container, false);


        EditText editRecipientInfo = view.findViewById(R.id.edit_recipient_info);
        EditText editRecipientPhone = view.findViewById(R.id.edit_recipient_phone);
        EditText editRecipientAddress = view.findViewById(R.id.edit_recipient_address);
        TextView tvSave = view.findViewById(R.id.text_save);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newRecipientInfo = editRecipientInfo.getText().toString();
                String newRecipientPhone = editRecipientPhone.getText().toString();
                String newRecipientAddress = editRecipientAddress.getText().toString();

                if (newRecipientInfo.isEmpty() || newRecipientPhone.isEmpty() || newRecipientAddress.isEmpty()) {
                    // Hiển thị thông báo cho người dùng
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return; // Kết thúc phương thức để ngăn việc lưu dữ liệu
                }
                if (!isValidPhoneNumber(newRecipientPhone)) {
                    // Hiển thị thông báo cho người dùng
                    Toast.makeText(getContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return; // Kết thúc phương thức để ngăn việc lưu dữ liệu
                }
                Bundle result = new Bundle();
                result.putString("newRecipientInfo", newRecipientInfo);
                result.putString("newRecipientPhone", newRecipientPhone);
                result.putString("newRecipientAddress", newRecipientAddress);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                dismiss();
            }
        });

        return view;
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Bạn có thể thay đổi phương thức kiểm tra số điện thoại tùy theo định dạng bạn muốn
        // Ở đây, một phương pháp đơn giản để kiểm tra có phải là số và có đủ độ dài không
        return phoneNumber.matches("0\\d{9}");  // Kiểm tra xem số điện thoại có 10 chữ số không
    }
}

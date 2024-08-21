package com.example.showappclient.ui.order;

import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showappclient.MainMenuFragment;
import com.example.showappclient.R;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.User;
import com.example.showappclient.model.request.OrderDetailRequest;
import com.example.showappclient.model.request.OrderRequest;
import com.example.showappclient.ui.adapter.CartListAdapter;
import com.example.showappclient.ui.adapter.OrderListAdapter;
import com.example.showappclient.ui.cart.CartFragment;
import com.example.showappclient.ui.cart.CartViewModel;
import com.example.showappclient.util.AlertMessageViewer;
import com.example.showappclient.zalo.Api.CreateOrder;

import org.json.JSONObject;

import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class OrderFragment extends Fragment {
    private List<Cart> selectedProducts;
    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;

    private ImageView imageLeft;
    private TextView tvTotalProduct, tvShipPrice, tvTotalPayment, tvDiscount, tvName, tvPayment, tvPhone, tvAddress, tvChangeInfo;

    private CartViewModel mViewModel;

    private OrderViewModel orderViewModel;

    int userId = 0;
    private CartListAdapter cartListAdapter;



    private String shippingMethod = "Hoả tốc"; // Giá trị mặc định
    private String paymentMethod = "MoMo";
    float tShip = 100000;
    int id=0;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public static OrderFragment newInstance(List<Cart> selectedProducts) {
        OrderFragment fragment = new OrderFragment();
        fragment.selectedProducts = selectedProducts;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String newRecipientInfo = result.getString("newRecipientInfo");
                String newRecipientPhone = result.getString("newRecipientPhone");
                String newRecipientAddress = result.getString("newRecipientAddress");
                orderViewModel.setRecipientInfo(newRecipientInfo, newRecipientPhone, newRecipientAddress);
            }
        });
//        getParentFragmentManager().setFragmentResultListener("payment_result", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String paymentStatus = result.getString("payment_status");
//                if ("success".equals(paymentStatus)) {
//                    Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
//                    // Xử lý logic khi thanh toán thành công
//                } else if ("canceled".equals(paymentStatus)) {
//                    Toast.makeText(getContext(), "Khách hàng hủy thanh toán", Toast.LENGTH_SHORT).show();
//                    // Xử lý logic khi thanh toán bị hủy
//                } else if ("error".equals(paymentStatus)) {
//                    String errorCode = result.getString("error_code");
//                    Toast.makeText(getContext(), "Thanh toán thất bại: " + errorCode, Toast.LENGTH_SHORT).show();
//                    // Xử lý logic khi thanh toán thất bại
//                }
//            }
//        });
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(553, Environment.SANDBOX);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        imageLeft = view.findViewById(R.id.image_left);
        recyclerView = view.findViewById(R.id.recycler_cartItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvTotalProduct = view.findViewById(R.id.text_total_price_product);
        tvShipPrice = view.findViewById(R.id.text_ship_price);
        tvTotalPayment = view.findViewById(R.id.text_total_price);
        tvDiscount = view.findViewById(R.id.text_promotion_price);
        tvPayment = view.findViewById(R.id.text_payment);
        tvName = view.findViewById(R.id.text_customer_name);
        tvPhone = view.findViewById(R.id.text_cutomer_phone);
        tvAddress = view.findViewById(R.id.text_cutomer_address);
        tvChangeInfo = view.findViewById(R.id.text_change_infor);
        initViewModel();

        orderViewModel.getUser();


        tvDiscount.setText(0 + "₫");


        tvShipPrice.setText(String.valueOf(tShip) + "₫");

        RadioGroup radioGroupShipping = view.findViewById(R.id.radioGroup);
        radioGroupShipping.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobtn_express:
                        shippingMethod = "Hỏa tốc";
                        tShip = 100000;
                        break;
                    case R.id.radiobtn_fast:
                        shippingMethod = "Nhanh";
                        tShip = 50000;
                        break;
                    case R.id.radiobtn_economical:
                        shippingMethod = "Tiết kiệm";
                        tShip = 1;
                        break;
                }
                tvShipPrice.setText(String.valueOf(tShip) + "₫");
                updateTotalPayment();
            }
        });

        RadioGroup radioGroupPayment = view.findViewById(R.id.radio_payment);
        radioGroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobtn_momo:
                        paymentMethod = "MoMo";
                        break;
                    case R.id.radiobtn_zalopay:
                        paymentMethod = "ZaloPay";
                        break;
                    case R.id.radiobtn_receive:
                        paymentMethod = "Khi nhận hàng";
                        break;
                }
            }
        });
        tvChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeRecipientDialog();
            }
        });


        tvPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paymentMethod.equals("ZaloPay"))
                {
                    zalopayPayment();
                }
                else if(paymentMethod.equals("MoMo")){

                }
                else{
                createOrderAfterPayment();}
            }
        });


        Bundle receivedBundle = getArguments();
        if (receivedBundle != null) {
            selectedProducts = (List<Cart>) receivedBundle.getSerializable("selected_products");
            float totalPrice = receivedBundle.getFloat("total_price");
            tvTotalProduct.setText(String.valueOf(totalPrice) + "₫");
            updateTotalPayment();
            if (selectedProducts != null && !selectedProducts.isEmpty()) {

                orderListAdapter = new OrderListAdapter(selectedProducts);
                recyclerView.setAdapter(orderListAdapter);
            } else {

            }
        }


        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderListAdapter.clear();
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });




    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Loại bỏ Observer khi Fragment được hủy
        orderViewModel.orderIdLiveData.removeObservers(getViewLifecycleOwner());
    }
    public void createOrderDetail(int orderId){
        if (selectedProducts != null && !selectedProducts.isEmpty()) {
            for (Cart cartItem : selectedProducts) {
                int productId = cartItem.getProductId();
                double price = cartItem.getPrice();
                int quantity = cartItem.getQuantity();
                String option = cartItem.getOption();
                double totalMoneyProduct = price * quantity;
                String productName = cartItem.getProductName();

                orderViewModel.createOrderDetail(new OrderDetailRequest(orderId, productId,(float) price, quantity, (float) totalMoneyProduct, option,productName ));
                orderViewModel.apiCallSuccess.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    public void onChanged(Boolean success) {

                        if (success != null && success ) {
                            mViewModel.deleteFromCart(cartItem);
                            MainMenuFragment cartFragment = new MainMenuFragment();
                            requireActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.root, cartFragment)
                                    .commit();

                        }
                        else {
                            CartFragment cartFragment = new CartFragment();
                            requireActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.root, cartFragment)
                                    .commit();

                        }
                        {
                            Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }
    }

    public void initViewModel() {
        orderViewModel.user.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                tvName.setText(user.getFullName());
                tvPhone.setText(user.getPhoneNumber());
                tvAddress.setText(user.getAddress());
                userId = Math.toIntExact(user.getId());
                Log.d("UserId", "User Isd2: " + userId);
            }
        });
        orderViewModel.getRecipientName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newName) {
                tvName.setText(newName);
            }
        });

        orderViewModel.getRecipientPhone().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newPhone) {
                tvPhone.setText(newPhone);
            }
        });

        orderViewModel.getRecipientAddress().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newAddress) {
                tvAddress.setText(newAddress);
            }
        });
        orderViewModel.getPaymentSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success != null) {
                    if (success) {
                        // Xử lý khi thanh toán thành công
                        Toast.makeText(requireContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        createOrderAfterPayment();
                    } else {
                        // Xử lý khi thanh toán thất bại
                        Toast.makeText(requireContext(), "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    private void showChangeRecipientDialog() {

        ChangeRecipientDialogFragment dialog = new ChangeRecipientDialogFragment();

        dialog.show(getParentFragmentManager(), "ChangeRecipientDialogFragment");
    }
    private void updateTotalPayment() {
        if (tShip != 0 && tvTotalProduct.getText() != null) { // Kiểm tra tShip và tvTotalProduct có khác 0 và null hay không
            float totalPrice = Float.parseFloat(tvTotalProduct.getText().toString().replace("₫", "").trim()); // Lấy giá trị tổng giá sản phẩm
            float totalPricePayment = tShip + totalPrice;

            tvTotalPayment.setText((int)totalPricePayment + "₫");
        }
    }
    public void createOrderAfterPayment(){
        String name = tvName.getText().toString();
        String phone = tvPhone.getText().toString();
        String address = tvAddress.getText().toString();
        String priceString = tvTotalPayment.getText().toString();
        priceString = priceString.replace("₫", "");
        Float totalMoney = Float.valueOf(priceString);

        orderViewModel.createOrder(new OrderRequest(name, userId, phone, address, shippingMethod, totalMoney, paymentMethod));

        // Observer mới để xử lý khi có orderId mới
        Observer<Integer> orderIdObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer orderId) {
                if (orderId != null) {
                    id = orderId;
                    createOrderDetail(orderId);
                    // Sau khi sử dụng, loại bỏ Observer và đặt lại giá trị orderId
                    orderViewModel.orderIdLiveData.removeObserver(this);
                    orderViewModel.orderIdLiveData.setValue(null);
                }
            }
        };

        // Đảm bảo rằng không có Observer cũ còn tồn tại
        orderViewModel.orderIdLiveData.removeObservers(getViewLifecycleOwner());
        // Bắt đầu quan sát orderIdLiveData
        orderViewModel.orderIdLiveData.observe(getViewLifecycleOwner(), orderIdObserver);
    }

    private void zalopayPayment(){
        String token = "";
        CreateOrder orderApi = new CreateOrder();
        try {
            String amount = tvTotalPayment.getText().toString().replaceAll("\\D", "");
            JSONObject data = orderApi.createOrder(amount);
            Log.d("CreateOrderResponse", data.toString());
            String code = data.getString("returncode");
//            Toast.makeText(requireContext(), "Đang chuyển sang ứng dụng zalopay", Toast.LENGTH_SHORT).show();
            if (code.equals("1")) {
                token = data.getString("zptranstoken"); // Mã zp_trans_token của đơn hàng thanh toán
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gọi hàm thanh toán
        ZaloPaySDK.getInstance().payOrder(requireActivity(), token, "zalopayment://app", new PayOrderListener() {


            @Override
            public void onPaymentSucceeded(String s, String s1, String s2) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        AlertMessageViewer.showAlertZalopay(
                                requireContext(),
                                "Thanh toán thành công!",
                                "TransactionId: " + s + " - TransToken: " + s1
                        );
                        createOrderAfterPayment();
                    });
                }

            }

            @Override
            public void onPaymentCanceled(String s, String s1) {
                AlertMessageViewer.showAlertZalopay(
                        requireContext(),
                        "Khách hàng hủy thanh toán bằng ZaloPay",
                        "zpTransToken: " + s
                );

            }

            @Override
            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                AlertMessageViewer.showAlertZalopay(
                        requireContext(),
                        "Thanh toán thất bại!",
                        "ZaloPayErrorCode: " + zaloPayError.toString() + "\nTransToken: " + s
                );

            }
        });
    }



public void handleNewIntent(Intent intent) {
    ZaloPaySDK.getInstance().onResult(intent);

    }

}





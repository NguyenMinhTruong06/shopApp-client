package com.example.showappclient.ui.order;

import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.showappclient.model.response.OrderDetailResponse;
import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.ui.adapter.OrderListAdapter;
import com.example.showappclient.ui.cart.CartFragment;
import com.example.showappclient.ui.cart.CartViewModel;

import java.util.List;

public class OrderFragment extends Fragment {
    private List<Cart> selectedProducts;
    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;

    private ImageView imageLeft;
    private TextView tvTotalProduct, tvShipPrice, tvTotalPayment, tvDiscount, tvName, tvPayment, tvPhone, tvAddress, tvChangeInfo;

    private CartViewModel mViewModel;

    private OrderViewModel orderViewModel;

    int userId = 0;
    private int orderId = -1;

    private String shippingMethod = "Hỏa tốc"; // Giá trị mặc định
    private String paymentMethod = "MoMo";
    float tShip = 100000;

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
                        tShip = 20000;
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

                String name = tvName.getText().toString();
                String phone = tvPhone.getText().toString();
                String address = tvAddress.getText().toString();
                String priceString = tvTotalPayment.getText().toString();
                priceString = priceString.replace("₫", "");
                Float totalMoney = Float.valueOf(priceString);

                orderViewModel.createOrder(new OrderRequest(name, userId, phone, address, shippingMethod, totalMoney, paymentMethod));

                orderViewModel.orderResponse.observe(getViewLifecycleOwner(), new Observer<OrderResponse>() {
                    @Override
                    public void onChanged(OrderResponse orderResponse) {
                        if (orderResponse != null) {
                            orderId = orderResponse.getId();
                            if (selectedProducts != null && !selectedProducts.isEmpty()) {
                                for (Cart cartItem : selectedProducts) {
                                    int productId = cartItem.getProductId();
                                    double price = cartItem.getPrice();
                                    int quantity = cartItem.getQuantity();
                                    String option = cartItem.getOption();
                                    double totalMoneyProduct = price * quantity;

                                    orderViewModel.createOrderDetail(new OrderDetailRequest(orderId, productId,(float) price, quantity, (float) totalMoneyProduct, option ));
                                    orderViewModel.apiCallSuccess.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                                                public void onChanged(Boolean success) {
                                                    if (success != null && success) {
                                                        mViewModel.deleteFromCart(cartItem);
                                                        MainMenuFragment cartFragment = new MainMenuFragment();
                                                        requireActivity().getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.root, cartFragment)
                                                                .commit();
                                                        Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        CartFragment cartFragment = new CartFragment();
                                                        requireActivity().getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.root, cartFragment)
                                                                .commit();
                                                        Toast.makeText(getContext(), "Lỗi khi thanh toán", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                    });

                                }


                            }

                        }
                    }
                });


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
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

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

    }


    private void showChangeRecipientDialog() {

        ChangeRecipientDialogFragment dialog = new ChangeRecipientDialogFragment();

        dialog.show(getParentFragmentManager(), "ChangeRecipientDialogFragment");
    }
    private void updateTotalPayment() {
        if (tShip != 0 && tvTotalProduct.getText() != null) { // Kiểm tra tShip và tvTotalProduct có khác 0 và null hay không
            float totalPrice = Float.parseFloat(tvTotalProduct.getText().toString().replace("₫", "").trim()); // Lấy giá trị tổng giá sản phẩm
            float totalPricePayment = tShip + totalPrice;
            tvTotalPayment.setText(totalPricePayment + "₫");
        }
    }

}
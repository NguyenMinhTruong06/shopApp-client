package com.example.showappclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showappclient.R;
import com.example.showappclient.localdb.AppDatabase;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.ui.cart.CartFragment;
import com.example.showappclient.ui.cart.CartViewModel;


import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder>  {

    private List<Cart> productList = new ArrayList<>();
    private AppDatabase appDatabase;
    private CartFragment cartFragment;

    private static OnItemClickListener onItemClickListener;
    private OnItemCheckedChangeListener onItemCheckedChangeListener;

    public CartListAdapter(AppDatabase appDatabase, CartFragment fragment) {
        this.appDatabase = appDatabase;
        this.cartFragment = fragment;
    }

    public void setOnItemCheckedChangeListener(OnItemCheckedChangeListener onItemCheckedChangeListener) {
        this.onItemCheckedChangeListener = onItemCheckedChangeListener;;
    }


    public Cart getProduct(int position) {
        return productList.get(position);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product_list, parent, false);
        return new CartViewHolder(view,appDatabase);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = productList.get(position);
        holder.bind(cart);

    }
    public void setData(List<Cart> carts ) {
        this.productList.clear(); // Xóa dữ liệu hiện tại trong danh sách sản phẩm
        this.productList.addAll(carts); // Thêm tất cả sản phẩm mới vào danh sách
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void add(Cart cart) {
    }

    public void remove(Cart cart) {
    }



    public boolean isEmpty() {return false;
    }




    public interface OnItemCheckedChangeListener {
        void onItemCheckedChange(int position, boolean isChecked);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView tvName, tvPrice, tvAdd, tvRemove, tvValue, tvId;
        private ImageView imgProduct;
        private Cart currentProduct;
        private AppDatabase appDatabase;
        private CartViewModel cartViewModel;

        public CartViewHolder(@NonNull View itemView, AppDatabase appDatabase) {
            super(itemView);
            this.appDatabase = appDatabase;
            checkBox = itemView.findViewById(R.id.checkbox_favorite);
            tvName = itemView.findViewById(R.id.text_name_product);
            tvPrice = itemView.findViewById(R.id.text_price_product);
            tvAdd = itemView.findViewById(R.id.text_add);
            tvRemove = itemView.findViewById(R.id.text_remove);
            tvValue = itemView.findViewById(R.id.text_quantity_value);
            imgProduct = itemView.findViewById(R.id.image_product);
            tvId = itemView.findViewById(R.id.text_description);


            tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position);
                        }
                        add(currentProduct);
                    }
                }
            });
            tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position);
                        }
                        remove(currentProduct);
                    }
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (currentProduct != null) {
                        currentProduct.setSelected(b);
                        cartFragment.updateSelectedProducts(currentProduct, b);// Đảm bảo Cart có phương thức setSelected
                    }
                    if (onItemCheckedChangeListener != null) {
                        onItemCheckedChangeListener.onItemCheckedChange(getAdapterPosition(), b);
                    }
                    float total = sum();
                    if (b ==true) {
                        tvRemove.setClickable(false);
                        tvAdd.setClickable(false);
                    } else {
                        tvAdd.setClickable(true);
                        tvRemove.setClickable(true);
                    }

                }
            });


        }

        public void bind(Cart cart) {
            currentProduct = cart;
            tvName.setText(cart.getProductName());
            tvPrice.setText(String.valueOf(cart.getPrice())+"₫");
            tvValue.setText(String.valueOf(cart.getQuantity()));

            Glide.with(itemView.getRootView())
                    .load(cart.getImagePath())
                    .centerCrop()
                    .into(imgProduct);

        }

        public void add(Cart cart) {
            int quantity = cart.getQuantity() + 1;
            cart.setQuantity(quantity);
            tvValue.setText(String.valueOf(quantity));
            appDatabase.cartDao().update(cart);
            cartFragment.onResume();

        }

        public void remove(Cart cart) {
            int quantity = cart.getQuantity() - 1;
            cart.setQuantity(quantity);
            float totalSum = sum();
            if (quantity == 0) {
                appDatabase.cartDao().delete(cart);
                appDatabase.cartDao().update(cart);



            } else {
                tvValue.setText(String.valueOf(quantity));
                appDatabase.cartDao().update(cart);

            }
            cartFragment.onResume();

        }

        public float sum() {
            float total = 0f;
            for (Cart cart : productList) {
                total += cart.getQuantity() * cart.getPrice();
            }
            return total;
        }
    }
}

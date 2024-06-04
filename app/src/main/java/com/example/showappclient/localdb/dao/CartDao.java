package com.example.showappclient.localdb.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Product;

import java.util.List;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cart cart);

    @Update
    void update(Cart cart);

    @Delete
    void delete(Cart cart);

    @Query("SELECT * FROM cart")
    List<Cart> findAll();


    @Query("SELECT * FROM cart WHERE product_Name = :productName AND option = :option LIMIT 1")
    Cart findByNameAndOption(String productName, String option);
}

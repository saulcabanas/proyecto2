package com.example.proyecto_2.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductsDao {

    @Insert
    public void InsertProducts(products Product);

    @Update
    public void UpdateProducts(products Product);

    @Query("SELECT * FROM products ORDER BY description ASC")
    public List<products> getAllProducts();

    @Query("SELECT * FROM products WHERE id = :id")
    public List<products> getProductsById(int id);

}

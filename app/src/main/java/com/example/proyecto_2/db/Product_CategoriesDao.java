package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Product_CategoriesDao {

    @Insert
    public void InsertProductCategory(Product_categories Category);

    @Update
    public void UpdateProductCategory(Product_categories Category);

    @Query("SELECT * FROM product_categories ORDER BY id")
    public List<Product_categories> getAllProCategories();

    @Query("SELECT id FROM product_categories ORDER BY id")
    public List<Product_categories> getProCategoriesid();

    @Query("SELECT * FROM product_categories ORDER BY id")
    public List<Product_categories> getProCategoriesById();

}


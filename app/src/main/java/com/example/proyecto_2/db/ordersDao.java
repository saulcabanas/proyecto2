package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ordersDao {

    @Insert
    public void InsertOrder(orders Orders);

    @Update
    public void UpdateOrder(orders Orders);

    @Query("SELECT * FROM orders ORDER BY id")
    public List<orders> getAllOrders();

    @Query("SELECT * FROM orders WHERE id = :id")
    public List<orders> getOrdersById(int id);

    @Query("SELECT * FROM orders WHERE customer_id = :id")
    public List<orders> getOrdersByCuId(int id);
}

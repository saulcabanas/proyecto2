package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface order_statusDao {

    @Insert
    public void InsertOrderStatus(order_status Order_status);

    @Update
    public void UpdateOrderStatus(order_status Order_status);

    @Query("SELECT * FROM order_status ORDER BY id")
    public List<order_status> getAllOrStatus();

    @Query("SELECT * FROM order_status WHERE id = :id")
    public List<order_status> getOrStatusById(int id);


}
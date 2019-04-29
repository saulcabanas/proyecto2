package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface New_Order_TableDao {

    @Insert
    public void InsertOrden(New_Order_Table new_order_table);

    @Query("DELETE FROM Ordenes_Recy")
    public void nukeTable();

    @Query("SELECT * FROM New_Order_Table ORDER BY newor_id")
    public List<New_Order_Table> getAll();

    @Update
    public void UpdateNewOrder(New_Order_Table new_order_table);


}
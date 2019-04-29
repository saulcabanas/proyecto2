package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface order_assembliesDao {

    @Insert
    public void InsertOrderAssem(order_assemblies Order_assemblies);

    @Update
    public void UpdateOrderAssem(order_assemblies Order_assemblies);

    @Query("SELECT * FROM order_assemblies ORDER BY id")
    public List<order_assemblies> getAllOrAssem();

    @Query("SELECT * FROM order_assemblies WHERE id = :id")
    public List<order_assemblies> getOrAssemById(int id);

}

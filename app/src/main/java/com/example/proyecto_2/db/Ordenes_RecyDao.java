package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Ordenes_RecyDao {

    @Insert
    public void InsertOrden(Ordenes_Recy ordenesRecies);

    @Query("SELECT * FROM Ordenes_Recy ORDER BY id")
    public List<Ordenes_Recy> getAll();

    @Query("DELETE FROM Ordenes_Recy")
    public void nukeTable();
}
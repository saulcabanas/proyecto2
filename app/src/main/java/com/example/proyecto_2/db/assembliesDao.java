package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface assembliesDao {

    @Insert
    public void InsertAssemblies(assemblies Assemblies);

    @Update
    public void UpdateAssemblies(assemblies Assemblies);

    @Query("SELECT * FROM assemblies ORDER BY id")
    public List<assemblies> getAllassemblies();

    @Query("SELECT * FROM assemblies WHERE id = :id")
    public List<assemblies> getAssembliesById(int id);

}
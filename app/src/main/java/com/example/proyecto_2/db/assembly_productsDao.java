package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface assembly_productsDao {

    @Insert
    public void InsertAssemblies_products(assembly_products Assemblies_products);

    @Update
    public void UpdateAssemblies_products(assembly_products Assemblies_products);

    @Query("SELECT * FROM assembly_products ORDER BY id")
    public List<assembly_products> getAllassemblies_products();

    @Query("SELECT * FROM assembly_products WHERE id = :id")
    public assembly_products getProductAssembliesById(int id);

}


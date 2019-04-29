package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface RecyclerPositionDao {

    @Insert
    public void InsertPosition(RecyclerPosition recyclerPosition);

    @Query("DELETE FROM RecyclerPosition")
    public void nukeTable();

    @Query("SELECT position FROM RecyclerPosition")
    public int getposition();

}
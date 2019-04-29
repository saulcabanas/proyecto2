package com.example.proyecto_2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "RecyclerPosition")
public class RecyclerPosition {
    @PrimaryKey
    @ColumnInfo(name = "position")
    private int position;

    public RecyclerPosition(int position) {
        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

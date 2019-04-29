package com.example.proyecto_2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "order_assemblies")
public class order_assemblies {

    @PrimaryKey(autoGenerate = true)
    private int asPr_id;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "assembly_id")
    private int assembly_id;

    @ColumnInfo(name = "qty")
    private int qty;

    public int getAsPr_id(){return asPr_id; }

    public void setAsPr_id(int asPr_id) {
        this.asPr_id = asPr_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssembly_id() {
        return assembly_id;
    }

    public void setAssembly_id(int assembly_id) {
        this.assembly_id = assembly_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public order_assemblies(int id, int assembly_id, int qty) {
        this.id = id;
        this.assembly_id = assembly_id;
        this.qty = qty;
    }
}

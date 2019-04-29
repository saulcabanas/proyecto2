package com.example.proyecto_2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "New_Order_Table")
public class New_Order_Table {

    @PrimaryKey
    @ColumnInfo(name = "newor_id")
    private int newor_id;

    @ColumnInfo(name = "or_id")
    private int or_id;

    @ColumnInfo(name = "assembly_id")
    private int assembly_id;

    @ColumnInfo(name = "qty")
    private int qty;

    @ColumnInfo(name = "assembly")
    private String assembly;

    @ColumnInfo(name = "qtyProducts")
    private String qtyProducs;

    @ColumnInfo(name = "price")
    private String price;

    public New_Order_Table(int newor_id, int or_id, int assembly_id, int qty, String assembly, String qtyProducs, String price) {
        this.newor_id = newor_id;
        this.or_id = or_id;
        this.assembly_id = assembly_id;
        this.qty = qty;
        this.assembly = assembly;
        this.qtyProducs = qtyProducs;
        this.price = price;
    }

    public int getNewor_id() {
        return newor_id;
    }

    public void setNewor_id(int newor_id) {
        this.newor_id = newor_id;
    }

    public int getOr_id() {
        return or_id;
    }

    public void setOr_id(int or_id) {
        this.or_id = or_id;
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

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getQtyProducs() {
        return qtyProducs;
    }

    public void setQtyProducs(String qtyProducs) {
        this.qtyProducs = qtyProducs;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

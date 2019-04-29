package com.example.proyecto_2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "assembly_products")
public class assembly_products {

    @PrimaryKey(autoGenerate = true)
    private int asPr_id;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "product_id")
    private int product_id;

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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public assembly_products(int id, int product_id, int qty) {
        this.id = id;
        this.product_id = product_id;
        this.qty = qty;
    }
}

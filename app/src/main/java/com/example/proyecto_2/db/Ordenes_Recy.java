package com.example.proyecto_2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "Ordenes_Recy")
public class Ordenes_Recy {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int ordRec_id;

    @ColumnInfo(name = "cliente")
    private String cliente;

    @ColumnInfo(name = "estado")
    private String estado;

    @ColumnInfo(name = "fecha")
    private String fecha;

    @ColumnInfo(name = "qty")
    private String qty;

    @ColumnInfo(name = "costo")
    private String costo;

    public int getOrdRec_id() {
        return ordRec_id;
    }

    public void setOrdRec_id(int ordRec_id) {
        this.ordRec_id = ordRec_id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public Ordenes_Recy(int ordRec_id, String cliente, String estado, String fecha, String qty, String costo) {
        this.ordRec_id = ordRec_id;
        this.cliente = cliente;
        this.estado = estado;
        this.fecha = fecha;
        this.qty = qty;
        this.costo = costo;
    }
}
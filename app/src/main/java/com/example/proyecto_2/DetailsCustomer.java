package com.example.proyecto_2;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;
import android.content.Intent;

import com.example.proyecto_2.db.Customers;

import com.example.proyecto_2.db.Customers;
import com.example.proyecto_2.db.CustomersDao;

public class DetailsCustomer extends DialogFragment {

    private String message = "Usuario no seleccionado";

    public DetailsCustomer setMessage(String customMessage){
        message = customMessage;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Detalles").setMessage(message);
        return builder.create();

    }
}

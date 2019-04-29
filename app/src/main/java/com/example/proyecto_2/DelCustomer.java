package com.example.proyecto_2;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;
import com.example.proyecto_2.db.AppDatabase;
import com.example.proyecto_2.db.Customers;
import com.example.proyecto_2.db.CustomersDao;
import com.example.proyecto_2.db.RecyclerPosition;
import com.example.proyecto_2.db.RecyclerPositionDao;

import java.util.List;

public class DelCustomer extends DialogFragment {

    private int CustomerId;

    public DelCustomer SetId(int id){
        CustomerId = id;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Desea eliminar este usuario?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AppDatabase db = AppDatabase.getAppDatabase(getContext());
                        List<Customers> customers = db.customersDao().getCustomersById(CustomerId);
                        Toast.makeText(getActivity(), "Cliente "+ customers.get(0).getLast_name()+ " "+ customers.get(0).getFirst_name() +" eliminado",Toast.LENGTH_SHORT).show();
                        db.customersDao().deleteCustomerById(CustomerId);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}


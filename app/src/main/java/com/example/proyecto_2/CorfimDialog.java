package com.example.proyecto_2;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;
import android.widget.CheckBox;

import com.example.proyecto_2.db.AppDatabase;
import com.example.proyecto_2.db.Customers;
import com.example.proyecto_2.db.Customers;
import com.example.proyecto_2.db.CustomersDao;
import java.util.List;

public class CorfimDialog extends DialogFragment {

    private String Data;
    private int Id;
    private int Identifier;
    private String Text;

    public CorfimDialog SetCustomer(int identifier, int id,  String data, String text){
        Data = data;
        Text=text;
        Id = id;
        Identifier = identifier;
        return this;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(Text).setMessage("¿Esta seguro que desea cambiar el " +Text+ " del Usuario por "+ Data)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppDatabase db = AppDatabase.getAppDatabase(getContext());
                                CustomersDao ctDao = db.customersDao();
                                List<Customers> tempClient = ctDao.getCustomersById(Id);


                                if(Identifier==1){
                                    db.customersDao().setNameById(Id,Data);
                                    //Toast.makeText(getActivity(),tempClient.get(0).getFirst_name()+" = " +Data, Toast.LENGTH_SHORT).show();
                                }
                                if(Identifier==2 ){
                                    db.customersDao().setLastNameById(Id,Data);
                                }
                                if(Identifier==3){
                                    db.customersDao().setPhone1ById(Id,Data);
                                }
                                if(Identifier==4){
                                    db.customersDao().setPhone2ById(Id,Data);
                                }
                                if(Identifier==5){
                                    db.customersDao().setPhone3ById(Id,Data);
                                }
                                if(Identifier==6){
                                    db.customersDao().setEmailById(Id,Data);
                                }
                                if(Identifier==7){
                                    db.customersDao().setAddressById(Id,Data);
                                }
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "El "+Text+" no fue cambiado", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}

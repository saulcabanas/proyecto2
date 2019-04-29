package com.example.proyecto_2;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.proyecto_2.db.AppDatabase;
import com.example.proyecto_2.db.Customers;
import com.example.proyecto_2.db.CustomersDao;
import com.example.proyecto_2.db.RecyclerPosition;
import com.example.proyecto_2.db.RecyclerPositionDao;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddCustomer extends AppCompatActivity {

    private String name="";
    private String Last_name="";
    private String phone1="";
    private String phone2="";
    private String phone3="";
    private String email="";
    private String address="";
    private int id;
    private EditText E_Name;
    private EditText E_Last_Name;
    private EditText E_Phone1;
    private EditText E_Phone2;
    private EditText E_Phone3;
    private EditText E_Email;
    private TextView Edit_Title;
    private CheckBox Ch_Phone2;
    private CheckBox Ch_Phone3;
    private CheckBox Ch_Email;
    private CheckBox Ch_Address;
    private EditText E_Address;
    private Button SaveCustBotton;
    private int DetectAddEdit=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle datos = this.getIntent().getExtras();
        if (datos != null) {

            DetectAddEdit=1;

            name = datos.getString("C_Name");
            if (name == null) {
                name = "";
            }
            Last_name = datos.getString("C_Lname");
            if (Last_name == null) {
                Last_name = "";
            }
            phone1 = datos.getString("C_Phone1");
            if (phone1 == null) {
                phone1 = "";
            }
            phone2 = datos.getString("C_Phone2");
            if (phone2 == null) {
                phone2 = "";
            }
            phone3 = datos.getString("C_Phone3");
            if (phone3 == null) {
                phone3 = "";
            }
            email = datos.getString("C_Email");
            if (email == null) {
                email = "";
            }
            address = datos.getString("C_Address");
            if (address == null) {
                address = "";
            }
            id = datos.getInt("C_Id");

            Edit_Title = findViewById(R.id.TxtviewAddCustomer);
            Edit_Title.setText("Editar Cliente");
        }

        E_Name = findViewById(R.id.EdTextName);
        E_Last_Name = findViewById(R.id.EdTextLas_Name);
        E_Phone1 = findViewById(R.id.EdTextTelefono1);
        E_Phone2 = findViewById(R.id.EdTextTelefono2);
        E_Phone3 = findViewById(R.id.EdTextTelefono3);
        E_Email = findViewById(R.id.EdTextEmail);
        E_Address = findViewById(R.id.EdTextAdress);

        Ch_Email = findViewById(R.id.checkboxEmail);
        Ch_Phone2 = findViewById(R.id.checkboxTelefono2);
        Ch_Phone3 = findViewById(R.id.checkboxTelefono3);
        Ch_Address = findViewById(R.id.checkboxAdress);

        SaveCustBotton = findViewById(R.id.SaveCustButton);

        E_Name.setText(name);
        E_Last_Name.setText(Last_name);
        E_Phone1.setText(phone1);
        E_Phone2.setText(phone2);
        E_Phone3.setText(phone3);
        E_Email.setText(email);

        E_Phone2.setEnabled(false);
        E_Phone3.setEnabled(false);
        E_Email.setEnabled(false);
        E_Address.setEnabled(false);

        Ch_Phone2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Ch_Phone2.isChecked()) {
                    E_Phone2.setEnabled(true);
                }
                if (!Ch_Phone2.isChecked()) {
                    E_Phone2.setEnabled(false);
                }
            }
        });

        Ch_Phone3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Ch_Phone3.isChecked()) {
                    E_Phone3.setEnabled(true);
                }
                if (!Ch_Phone3.isChecked()) {
                    E_Phone3.setEnabled(false);
                }
            }
        });

        Ch_Email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Ch_Email.isChecked()) {
                    E_Email.setEnabled(true);
                }
                if (!Ch_Email.isChecked()) {
                    E_Email.setEnabled(false);
                }
            }
        });

        Ch_Address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Ch_Address.isChecked()) {
                    E_Address.setEnabled(true);
                }
                if (!Ch_Address.isChecked()) {
                    E_Address.setEnabled(false);
                }
            }
        });

        SaveCustBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                DialogFragment newFragment2 = new DetailsCustomer();
                ((DetailsCustomer) newFragment2).setMessage("Hay casillas vacias, favor de llenarlas");
                if(DetectAddEdit == 1){
                    if(E_Name.getText().toString().isEmpty() || E_Last_Name.getText().toString().isEmpty() || E_Phone1.getText().toString().isEmpty()){
                        newFragment2.show(getSupportFragmentManager(), "Espere");
                    }else{

                        if(Ch_Phone2.isChecked() && !E_Phone2.getText().toString().isEmpty() || !Ch_Phone2.isChecked()){
                            if(Ch_Phone3.isChecked() && !E_Phone3.getText().toString().isEmpty() || !Ch_Phone3.isChecked()){
                                if(Ch_Email.isChecked() && !E_Email.getText().toString().isEmpty() || !Ch_Email.isChecked()){
                                    if(Ch_Address.isChecked() && !E_Address.getText().toString().isEmpty() || !Ch_Address.isChecked()){
                                        CustomersDao ctDao = db.customersDao();
                                        List<Customers> tempClient = ctDao.getCustomersById(id);
                                        if(!E_Name.getText().toString().equals(tempClient.get(0).getFirst_name())){
                                            DialogFragment Name = new CorfimDialog();
                                            ((CorfimDialog) Name).SetCustomer(1, id, E_Name.getText().toString(), "Nombre");
                                            Name.show(getSupportFragmentManager(), "Espere");
                                            Toast.makeText(getApplicationContext(),tempClient.get(0).getFirst_name()+" ==== " +E_Name.getText().toString(), Toast.LENGTH_SHORT).show();
                                        }


                                        if(!E_Last_Name.getText().toString().equals(tempClient.get(0).getLast_name())){
                                            DialogFragment AddLastName= new CorfimDialog();
                                            ((CorfimDialog) AddLastName).SetCustomer(2, id, E_Last_Name.getText().toString(), "Apellido");
                                            AddLastName.show(getSupportFragmentManager(), "Espere");
                                        }


                                        if(!E_Phone1.getText().toString().equals(tempClient.get(0).getPhone1())){
                                            DialogFragment AddPhone1 = new CorfimDialog();
                                            ((CorfimDialog) AddPhone1).SetCustomer(3, id, E_Phone1.getText().toString(), "Telefono 1");
                                            AddPhone1.show(getSupportFragmentManager(), "Espere");
                                        }

                                        if(Ch_Phone2.isChecked() && !E_Phone2.getText().toString().equals(tempClient.get(0).getPhone2())){
                                            DialogFragment AddPhone2 = new CorfimDialog();
                                            ((CorfimDialog) AddPhone2).SetCustomer(4, id, E_Phone2.getText().toString(), "Telefono 2");
                                            AddPhone2.show(getSupportFragmentManager(), "Espere");
                                        }

                                        if(Ch_Phone3.isChecked() && !E_Phone3.getText().toString().equals(tempClient.get(0).getPhone3())){
                                            DialogFragment AddPhone3 = new CorfimDialog();
                                            ((CorfimDialog) AddPhone3).SetCustomer(5, id, E_Phone3.getText().toString(), "Telefono 3");
                                            AddPhone3.show(getSupportFragmentManager(), "Espere");
                                        }
                                        if(Ch_Email.isChecked() && !E_Email.getText().toString().equals(tempClient.get(0).getE_mail())){
                                            DialogFragment AddEmail = new CorfimDialog();
                                            ((CorfimDialog) AddEmail).SetCustomer(6, id, E_Email.getText().toString(), "Email");
                                            AddEmail.show(getSupportFragmentManager(), "Espere");
                                        }
                                        if(Ch_Address.isChecked() && !E_Address.getText().toString().equals(tempClient.get(0).getAddress())){
                                            DialogFragment AddEmail = new CorfimDialog();
                                            ((CorfimDialog) AddEmail).SetCustomer(7, id, E_Address.getText().toString(), "Dirección");
                                            AddEmail.show(getSupportFragmentManager(), "Espere");
                                        }
                                    }else{
                                        newFragment2.show(getSupportFragmentManager(), "Espere");
                                    }
                                }else{
                                    newFragment2.show(getSupportFragmentManager(), "Espere");
                                }
                            }else{
                                newFragment2.show(getSupportFragmentManager(), "Espere");
                            }
                        }else{
                            newFragment2.show(getSupportFragmentManager(), "Espere");
                        }
                    }
                }else{
                    if(E_Name.getText().toString().isEmpty() || E_Last_Name.getText().toString().isEmpty() || E_Phone1.getText().toString().isEmpty()){
                        newFragment2.show(getSupportFragmentManager(), "Espere");
                    }else {
                        if(Ch_Phone2.isChecked() && !E_Phone2.getText().toString().isEmpty() || !Ch_Phone2.isChecked()){
                            if(Ch_Phone3.isChecked() && !E_Phone3.getText().toString().isEmpty() || !Ch_Phone3.isChecked()){
                                if(Ch_Email.isChecked() && !E_Email.getText().toString().isEmpty() || !Ch_Email.isChecked()){
                                    if(Ch_Address.isChecked() && !E_Address.getText().toString().isEmpty() || !Ch_Address.isChecked()){

                                        String P2;
                                        String P3;
                                        String Em;
                                        String Ad;
                                        if(E_Phone2 == null){ P2=null; }else{ P2=E_Phone2.getText().toString(); }
                                        if(E_Phone3 == null){ P3=null; }else{ P3=E_Phone3.getText().toString(); }
                                        if(E_Email == null){ Em=null; }else{ Em=E_Email.getText().toString(); }
                                        if(E_Address == null){ Ad=null; }else{ Ad=E_Address.getText().toString(); }
                                        CustomersDao ctDao = db.customersDao();
                                        List<Customers> tempClient = ctDao.getAllCustomers();
                                        int id2 = tempClient.size();
                                        Customers customer = new Customers(id2, E_Name.getText().toString(), E_Last_Name.getText().toString(), Ad, E_Phone1.getText().toString(), P2, P3, Em);
                                        db.customersDao().insertCustomer(customer);

                                    }else{
                                        newFragment2.show(getSupportFragmentManager(), "Espere");
                                    }
                                }else{
                                    newFragment2.show(getSupportFragmentManager(), "Espere");
                                }
                            }else{
                                newFragment2.show(getSupportFragmentManager(), "Espere");
                            }
                        }else{
                            newFragment2.show(getSupportFragmentManager(), "Espere");
                        }
                    }
                }
            }
        });
    }
}
package com.example.proyecto_2;



import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Delete;
import android.content.ClipData;
import android.content.DialogInterface;
import android.media.browse.MediaBrowser;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import android.content.Intent;
import com.facebook.stetho.Stetho;
import android.widget.Button;

import com.example.proyecto_2.db.AppDatabase;
import com.example.proyecto_2.db.Customers;
import com.example.proyecto_2.db.CustomersDao;
import com.example.proyecto_2.db.orders;
import com.example.proyecto_2.db.ordersDao;
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

class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder>{

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView Name;
        private TextView LastName;
        private TextView Phone1;
        private TextView Email;
        private Customers customer;
        private int adapterposition;
        private int Size;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.c_txt_name);
            LastName = itemView.findViewById(R.id.c_txt_last_name);
            Phone1 = itemView.findViewById(R.id.c_txt_number_phone);
            Email = itemView.findViewById(R.id.c_txt_email);

            final View parent = itemView;

            AppDatabase db = AppDatabase.getAppDatabase(parent.getContext());
            final RecyclerPositionDao ReDao = db.recyclerPositionDao();
            final RecyclerPosition recyclerPosition = new RecyclerPosition(0);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        ReDao.nukeTable();
                        recyclerPosition.setPosition(pos);
                        ReDao.InsertPosition(recyclerPosition);
                    }
                    return false;
                }
            });
        }

        @Override

        public void onClick(View v) {
            adapterposition = getAdapterPosition();
        }



        public void bind(Customers customer) {
            this.customer = customer;

            Name.setText(customer.getFirst_name());
            LastName.setText(customer.getLast_name());
            //Phone1.setText(customer.getPhone1());
            Email.setText(customer.getE_mail());

            if(customer.getPhone1() == null){
                Phone1.setText("S/N");
            }else{
                Phone1.setText(customer.getPhone1());
            }
        }



    }

    private List<Customers> customers;

    public CustomersAdapter(List<Customers> customers) {
        this.customers = customers;
    }

    public Customers getItem(int position) {
        return customers.get(position);
    }

    public int getSize(){
        return customers.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customer_item, viewGroup, false);
        ((Activity)viewGroup.getContext()).registerForContextMenu(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(customers.get(i));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

}


//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII



public class CustomersActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private EditText SearchEText;
    private MediaBrowser.ItemCallback SearchButton;
    public List<Customers> customersave = new ArrayList<>();
    Parcelable recyclerViewState;
    Parcelable.Creator<Customers> customer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        Toolbar toolbar = findViewById(R.id.toolbar);
        SearchEText = findViewById(R.id.search_Edit_Text);


        setSupportActionBar(toolbar);

        Stetho.initializeWithDefaults(this);

        final String[] filter = {"Seleccione filtros", "Apellido", "Nombre", "N. Telefono", "Email", "Direccion"};

        spinner = findViewById(R.id.spinner);

        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < filter.length; i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(filter[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        MyAdapter myAdapter = new MyAdapter(CustomersActivity.this, 0,
                listVOs);
        spinner.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Spinner filterSpinner;
        filterSpinner = findViewById(R.id.spinner);

        int StaFlag = 0;

        final String[] filterList = {"Seleccione filtros", "Apellido", "Nombre", "N. Telefono", "Email", "Direccion"};

        filterSpinner.getSelectedItemPosition();
        List<Integer> Status = new ArrayList<>();
        for (int i = 1; i < filterList.length; i++) {
            StateVO stateVO = new StateVO();
            stateVO = (StateVO) filterSpinner.getAdapter().getItem(i);
            if (stateVO.isSelected()) {
                StaFlag = 1;
                Status.add(1);
            } else {
                Status.add(0);
            }
        }

        switch (item.getItemId()) {
            case R.id.search_menu_item:
                SearchEText = findViewById(R.id.search_Edit_Text);
                String SearchText = SearchEText.getText().toString();

                if (StaFlag == 1) {
                    if (!SearchText.isEmpty()) {
                        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                        CustomersDao ctDao = db.customersDao();
                        List<Customers> customers = ctDao.getCustomersByFnLnPhAdEm("%" + SearchText + "%");
                        Toast.makeText(this, SearchText, Toast.LENGTH_SHORT).show();

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByLastName("%" + SearchText + "%");
                        }
                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByName("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByPhone("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByEmail("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnLn("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnLnPh("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 3 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnLnPh("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnLnPhAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByLnPhAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnPhAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnLnAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnLnPhAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnLnPhEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByPhAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnEmAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnPhAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnPhAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByLnEmAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByLnPhAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByLnPhEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByPhAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByLnPh("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnPh("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByLnEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByLnAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnLnAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnPhEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 1 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByFnLnEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 1 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByFnAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 0 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByPhAd("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 0 && Status.get(3) == 1 && Status.get(4) == 1) {
                            customers = ctDao.getCustomersByAdEm("%" + SearchText + "%");
                        }

                        if (Status.get(0) == 0 && Status.get(1) == 0 && Status.get(2) == 1 && Status.get(3) == 1 && Status.get(4) == 0) {
                            customers = ctDao.getCustomersByPhEm("%" + SearchText + "%");
                        }
                        recyclerView = findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        recyclerView.setAdapter(new CustomersAdapter(customers));
                    } else {
                        AlertDialog.Builder dialogoEmpty = new AlertDialog.Builder(this);
                        dialogoEmpty.setMessage("Se recomienda escribir algunas palabras en para realizar la busqueda");
                        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                        CustomersDao ctDao = db.customersDao();
                        List<Customers> customers = ctDao.getAllCustomers();
//
                        recyclerView = findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        recyclerView.setAdapter(new CustomersAdapter(customers));
                    }
                } else {
                    if (SearchText.isEmpty()) {
                        AlertDialog.Builder dialogoEmpty = new AlertDialog.Builder(this);
                        dialogoEmpty.setMessage("Se recomienda escribir algunas palabras en para realizar la busqueda");
                        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                        CustomersDao ctDao = db.customersDao();
                        List<Customers> customers = ctDao.getAllCustomers();
//
                        recyclerView = findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        recyclerView.setAdapter(new CustomersAdapter(customers));
                    }
                }

                return true;

            case R.id.add_menu_item:
                Intent intent = new Intent(CustomersActivity.this, AddCustomer.class);
                startActivity(intent);
                Toast.makeText(this, "Connecting...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler_view);
        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
        RecyclerPositionDao ReDao = db.recyclerPositionDao();
        CustomersAdapter customersAdapter = (CustomersAdapter) recyclerView.getAdapter();
        int position = ReDao.getposition();
        final Customers customers2;
        customers2 = customersAdapter.getItem(position);
        ordersDao CwO = db.OrdersDao();
        List<orders> orders = CwO.getOrdersByCuId(customers2.getId());
        if (!orders.isEmpty()) {
            getMenuInflater().inflate(R.menu.ct_contextual_menu2, menu);
        } else {
            getMenuInflater().inflate(R.menu.ct_contextual_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        RecyclerView recyclerView;

        recyclerView = findViewById(R.id.recycler_view);

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
        RecyclerPositionDao ReDao = db.recyclerPositionDao();

        CustomersAdapter customersAdapter = (CustomersAdapter) recyclerView.getAdapter();
        final int position = ReDao.getposition();

        AppDatabase db2 = AppDatabase.getAppDatabase(getApplicationContext());
        final List<Customers> customers = new ArrayList<>();


        for (int i = 0; i < customersAdapter.getSize(); i++) {
            customers.add(customersAdapter.getItem(i));
        }

        final Customers customers2;
        customers2 = customersAdapter.getItem(position);
        switch (item.getItemId()) {
            case R.id.details_menu_item:
                String num1;
                String num2;
                String num3;
                if (customers2.getPhone1() == null) {
                    num1 = "S/N";
                } else {
                    num1 = customers2.getPhone1();
                }
                if (customers2.getPhone2() == null) {
                    num2 = "S/N";
                } else {
                    num2 = customers2.getPhone2();
                }
                if (customers2.getPhone3() == null) {
                    num3 = "S/N";
                } else {
                    num3 = customers2.getPhone3();
                }

                DialogFragment newFragment2 = new DetailsCustomer();
                ((DetailsCustomer) newFragment2).setMessage("Nombre: " + customers2.getFirst_name() + "\nApellido: " +
                        customers2.getLast_name() + "\nNumero 1: " + num1 + "\nNumero 2: " + num2
                        + "\nNumero 3: " + num3 + "\nEmail: " + customers2.getE_mail() + "\nDireccion: " + customers2.getAddress());
                newFragment2.show(getSupportFragmentManager(), "Espere");
                return true;

            case R.id.edit_menu_item:
                Toast.makeText(this, "Connecting...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CustomersActivity.this, AddCustomer.class);
                intent.putExtra("C_Name", customers2.getFirst_name());
                intent.putExtra("C_Lname", customers2.getLast_name());
                intent.putExtra("C_Phone1", customers2.getPhone1());
                intent.putExtra("C_Phone2", customers2.getPhone2());
                intent.putExtra("C_Phone3", customers2.getPhone3());
                intent.putExtra("C_Email", customers2.getE_mail());
                intent.putExtra("C_Address", customers2.getAddress());
                intent.putExtra("C_Id", customers2.getId());
                startActivity(intent);
                return true;

            case R.id.delete_menu_item:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea eliminar este usuario?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AppDatabase db3 = AppDatabase.getAppDatabase(getApplicationContext());
                                Toast.makeText(getApplicationContext(), "Cliente " + customers2.getFirst_name() + " " + customers2.getLast_name() + " eliminado", Toast.LENGTH_SHORT).show();
                                db3.customersDao().deleteCustomerById(customers2.getId());
                                customers.remove(position);

                                RecyclerView recyclerView;

                                recyclerView = findViewById(R.id.recycler_view);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(new CustomersAdapter(customers));

                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (recyclerView != null) {

            RecyclerView recyclerView1;
            recyclerView1 = findViewById(R.id.recycler_view);
            CustomersAdapter customersAdapter = (CustomersAdapter) recyclerView1.getAdapter();

            for (int i = 0; i < customersAdapter.getSize(); i++) {
                customersave.add(customersAdapter.getItem(i));
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new CustomersAdapter(customersave));

        if (savedInstanceState != null) {
            Toast.makeText(getApplicationContext(), "Si se paso", Toast.LENGTH_SHORT).show();//restore
            recyclerViewState = savedInstanceState.getParcelable("key");
            if(recyclerViewState != null){

            }
        }
    }



    //@Override
    //protected void onPause() {
    //    super.onPause();
    //    recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();//save
    //}
    //@Override
    //protected void onResume()
    //{
    //    super.onResume();
    //    if(recyclerViewState!=null)
    //        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);//restore
    //}
}

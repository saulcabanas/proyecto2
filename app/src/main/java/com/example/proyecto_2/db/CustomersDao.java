package com.example.proyecto_2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Update;
import android.os.Parcelable;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@Dao
public interface CustomersDao {

    @Insert
    public void InsertCustomers(Customers customers);

    @Query("SELECT * FROM customers ORDER BY last_name, first_name")
    public List<Customers> getAllCustomers();

    @Query("DELETE FROM customers WHERE id = :id ")
    public void deleteCustomerById(int id);

    @Query("SELECT * FROM customers WHERE id = :id ")
    public List<Customers> getCustomersById(int id);

    @Query("SELECT * FROM customers WHERE first_name like :name ORDER BY last_name, first_name")
    public List<Customers> getCustomersByName(String name);

    @Query("SELECT * FROM customers WHERE last_name like :last_name ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLastName(String last_name);

    @Query("SELECT * FROM customers WHERE phone1 = :phone1 ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhone1(String phone1);

    @Query("SELECT * FROM customers WHERE phone1 or phone2 or phone3 like :phone ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhone(String phone);

    @Query("SELECT * FROM customers WHERE phone2 = :phone2 ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhone2(String phone2);

    @Query("SELECT * FROM customers WHERE phone3 like :phone3 ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhone3(String phone3);

    @Query("SELECT * FROM customers WHERE e_mail like :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByEmail(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name like :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLn(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name or phone1 or phone2 or phone3 LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLnPh(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name or phone1 or phone2 or phone3 or address LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLnPhAd(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name or phone1 or phone2 or phone3 or address or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLnPhAdEm(String email);

    @Query("SELECT * FROM customers WHERE  first_name or phone1 or phone2 or phone3 or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnPhEm(String email);

    @Query("SELECT * FROM customers WHERE last_name or phone1 or phone2 or phone3 LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnPh(String email);

    @Query("SELECT * FROM customers WHERE last_name or phone1 or phone2 or phone3 or address LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnPhAd(String email);

    @Query("SELECT * FROM customers WHERE last_name or phone1 or phone2 or phone3 or address or e_mail LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnPhAdEm(String email);

    @Query("SELECT * FROM customers WHERE first_name or phone1 or phone2 or phone3 or address or e_mail LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnPhAdEm(String email);


    @Query("SELECT * FROM customers WHERE first_name or phone1 or phone2 or phone3 LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnPh(String email);

    @Query("SELECT * FROM customers WHERE first_name or phone1 or phone2 or phone3 or address LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnPhAd(String email);

    @Query("SELECT * FROM customers WHERE first_name or e_mail or address LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnEmAd(String email);

    @Query("SELECT * FROM customers WHERE first_name or last_name or phone1 or phone2 or phone3 or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLnPhEm(String email);

    @Query("SELECT * FROM customers WHERE last_name or phone1 or phone2 or phone3 or e_mail LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnPhEm(String email);

    @Query("SELECT * FROM customers WHERE first_name or e_mail LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnEm(String email);

    @Query("SELECT * FROM customers WHERE last_name or e_mail LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnEm(String email);


    @Query("SELECT * FROM customers WHERE first_name or address LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnAd(String email);

    @Query("SELECT * FROM customers WHERE last_name or address LIKE :email  ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnAd(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name or address LIKE :email")
    public List<Customers> getCustomersByFnLnAd(String email);

    @Query("SELECT * FROM customers WHERE last_name  or address or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByLnEmAd(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name or address or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLnAdEm(String email);

    @Query("SELECT * FROM customers WHERE first_name or address or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnAdEm(String email);

    @Query("SELECT * FROM customers WHERE last_name or first_name or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByFnLnEm(String email);


    @Query("SELECT * FROM customers WHERE address LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByAd(String email);

    @Query("SELECT * FROM customers WHERE phone1 or phone2 or phone3 or address LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhAd(String email);

    @Query("SELECT * FROM customers WHERE phone1 or phone2 or phone3 or address or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhAdEm(String email);

    @Query("SELECT * FROM customers WHERE phone1 or phone2 or phone3 or e_mail LIKE :email ORDER BY last_name, first_name")
    public List<Customers> getCustomersByPhEm(String email);

    @Query("SELECT * FROM customers WHERE address or e_mail LIKE :email order by first_name and last_name")
    public List<Customers> getCustomersByAdEm(String email);

   @Query("UPDATE customers SET first_name = :first_name WHERE id like :id")
   void setNameById(int id, String first_name);

   @Query("UPDATE customers SET last_name = :last_name WHERE id like :id")
   void setLastNameById(int id, String last_name);

   @Query("UPDATE customers SET phone1 = :phone1 WHERE id like :id")
   public void setPhone1ById(int id, String phone1);

   @Query("UPDATE customers SET phone2 = :phone2 WHERE id like :id")
   void setPhone2ById(int id, String phone2);

   @Query("UPDATE customers SET phone3 = :phone3 WHERE id like :id")
   void setPhone3ById(int id, String phone3);

    @Query("UPDATE customers SET e_mail = :newe_mail WHERE customers.id = :id")
    void setEmailById(int id, String newe_mail);

    @Query("UPDATE customers SET address= :newaddress WHERE customers.id= :id")
    void setAddressById(int id, String newaddress);

    @Insert
    void insertCustomer (Customers customers);
}

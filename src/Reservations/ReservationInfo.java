/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservations;

import Customers.CustomerInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gpm_lalai
 */
public class ReservationInfo {
    private int Id = 0;
    private int customerid;
    private CustomerInfo customer;
    private Date reservedate;
    
    public ReservationInfo(int id, int customerid, Date datereserve){
    
        this.Id = id;
        this.customerid = customerid;
        this.reservedate = datereserve;
    }
    
    public ReservationInfo(int id, CustomerInfo customer, Date reserveon){
    
        this.Id = id;
        this.customerid = customer.getId();
        this.customer = customer;
        SimpleDateFormat formatter =new SimpleDateFormat("MM dd, yyyy"); 
        //var x = Long.parseLong(datereserve);
        //this.reservedate = new Date(x);
        this.reservedate = reserveon;
    }
    
    public void setId(int id){
        this.Id = id;
    }
    public void setCustomerId(int id){
        this.customerid = id;
    }
    
    public void setCustomer(CustomerInfo customer){
        this.customer = customer;
    }
    
    public void setReserveDate(Date date){
        this.reservedate = date;
    }
    public int getId(){
        return this.Id;
    }
    public int getCustomerId(){
        return this.customerid;
    }
    public CustomerInfo getCustomer(){
        return customer;
    }
    public Date getReserveDate(){
        return this.reservedate;
    }
    
}

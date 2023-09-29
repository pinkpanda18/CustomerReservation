package Customers;
import Data.DataConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gpm_lalai
 */
public class CustomerService {
    
    private static ArrayList<CustomerInfo> customers = new ArrayList();
    public static boolean Load() 
    {
        if(!customers.isEmpty())
            return false;
        
        ResultSet resultSet = DataConnection.selectQuery("select * from customers;");
                       
        try{
            while(resultSet.next()){
            customers.add(new CustomerInfo(Integer.parseInt(resultSet.getObject(1).toString()), 
                    resultSet.getObject(2).toString(), 
                     resultSet.getObject(3).toString(), ""));
            }
            System.out.println("loaded with size of " + customers.size());
        }
        catch(SQLException e){
        }
        return true;
        
    }
    public static ArrayList<CustomerInfo> GetList()
    {
        return customers;
    }
    public static void clearArray()
    {
        customers.clear();
    } 
    
    public static CustomerInfo searchByID(int id)
    {
        try
        {
            Load();       
            for(int i=0; i<customers.size(); i++)
                if(customers.get(i).getId()==id)
                    return customers.get(i);
            
        }catch(Exception e){
        }
        return null;        
    }
    
    public static ArrayList<CustomerInfo> search(String search) 
    {
        Load();
        ArrayList<CustomerInfo> searchResult = new ArrayList();
        
        for (int i = 0; i < customers.size(); i++)
        {
            if (String.valueOf(customers.get(i).getId()).toUpperCase().contains(search.toUpperCase()))
                searchResult.add(customers.get(i));
            else if (customers.get(i).getFirstname().toUpperCase().contains(search.toUpperCase()))
                searchResult.add(customers.get(i));
            else if (String.valueOf(customers.get(i).getLastname()).toUpperCase().contains(search.toUpperCase()))
                searchResult.add(customers.get(i));
        }
        clearArray();
        return searchResult; 
    }
    
    public static void Save(CustomerInfo customer) throws SQLException 
     {       
        try {
              
        PreparedStatement ps=  DataConnection.getPreStatement("insert into customers(firstname, lastname, createdon)" +
                 "values ( ?,?,?)");
         ps.setString(1, customer.getFirstname());
         ps.setString(2, customer.getLastname());
         ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));

         ps.execute();
         clearArray(); //clear array to reset values
         }catch(IllegalArgumentException e){
           
            throw new IllegalArgumentException(e.getMessage() + "Record not saved!");
        }
               
     }

    public static void UpdateRecord(CustomerInfo customer)
    {
       try {
       
        Load();

        PreparedStatement ps=  DataConnection.getPreStatement("Update customers SET firstname = ?, lastname = ? , updatedon = ? where id = "+customer.getId()); 
        ps.setString(1, customer.getFirstname());
        ps.setString(2, customer.getLastname());
        ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));

        ps.execute();

        for(int i=0; i<customers.size(); i++)
            if(customers.get(i).getId() == customer.getId())
            {
                customers.set(i, customer);
                break;
            }

        }catch(SQLException e){
           throw new IllegalArgumentException(e.getMessage() + "Record not saved");
        }  
   }
    public static void DeleteRecord(CustomerInfo customer)
    {
        try {
 
             DataConnection.query("DELETE FROM customers WHERE id = "+ customer.getId() + ";");

            for(int i=0; i<customers.size(); i++)
            if(customers.get(i).getId() == customer.getId())
            {
                customers.remove(i);
                break;
            }

        }catch(Exception e)
        {
             throw new IllegalArgumentException(e.getMessage());
        }      

    }
}

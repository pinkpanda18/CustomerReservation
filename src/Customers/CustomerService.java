package Customers;
import Data.DataConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public static void Save(CustomerInfo pi1) throws SQLException 
     {       
        try {
              
        PreparedStatement ps=  DataConnection.getPreStatement("insert into customers(firstname, lastname)" +
                 "values ( ?,?)");
         ps.setString(1, pi1.getFirstname());
         ps.setString(2, pi1.getLastname());

         ps.execute();
         }catch(IllegalArgumentException e){
           
            throw new IllegalArgumentException(e.getMessage() + "Record not saved!");
        }
               
     }
}

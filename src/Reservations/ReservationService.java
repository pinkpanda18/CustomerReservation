package Reservations;

import Customers.CustomerInfo;
import Customers.CustomerService;
import static Customers.CustomerService.clearArray;
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
public class ReservationService {
    private static ArrayList<ReservationInfo> reservations = new ArrayList();
     public static boolean Load() 
    {
        if(!reservations.isEmpty())
            return false;
        
        ResultSet resultSet = DataConnection.selectQuery("select * from reservations;");
                       
        try{
            while(resultSet.next()){
                
             int customerid = Integer.parseInt(resultSet.getObject("customerid").toString());
             //get customerDetails
             CustomerInfo customer = CustomerService.searchByID(customerid);
            reservations.add(new ReservationInfo(resultSet.getInt("id"), 
                    customer,
                    //resultSet.getObject("reservedate").toString())
                    resultSet.getDate("reservedate"))
                    );
            }
            System.out.println("loaded with size of " + reservations.size());
        }
        catch(SQLException e){
        }
        return true;
        
    }
    public static ArrayList<ReservationInfo> GetList()
    {
        return reservations;
    }
    
    public static void clearArray()
    {
        reservations.clear();
    } 
     public static void Save(ReservationInfo info) throws SQLException 
     {       
        try {
              
        PreparedStatement ps=  DataConnection.getPreStatement("insert into reservations(customerid, reservedate, createdon)" +
                 "values ( ?,?,?)");
         ps.setInt(1, info.getCustomerId());
         ps.setTimestamp(2, new java.sql.Timestamp(info.getReserveDate().getTime()));
         ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));  

         ps.execute();
         clearArray(); //clear array to reset values
         }catch(IllegalArgumentException e){
           
            throw new IllegalArgumentException(e.getMessage() + "Record not saved!");
        }
               
     }


     public static void DeleteRecord(ReservationInfo info)
    {
        try {
 
             DataConnection.query("DELETE FROM reservations WHERE id = "+ info.getId() + ";");

            for(int i=0; i<reservations.size(); i++)
            if(reservations.get(i).getId() == info.getId())
            {
                reservations.remove(i);
                break;
            }

        }catch(Exception e)
        {
             throw new IllegalArgumentException(e.getMessage());
        }      

    }
}

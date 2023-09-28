/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customers;

import java.io.ByteArrayOutputStream;

/**
 *
 * @author gpm_lalai
 */
public class CustomerInfo {
    private int id = 0;
    private String name;
    private String firstname;
    private String lastname;
    private String address;
    private String dob;
    private String phone;
    private byte[] pic;
    private String email;
    private ByteArrayOutputStream img;
    
    public CustomerInfo(int id, String firstname, String lastname, String dob){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
    }
    
    public void setId(int id) {
        this.id = id;
    }
     
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public int getId() {
        return id;
    }
     
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
}

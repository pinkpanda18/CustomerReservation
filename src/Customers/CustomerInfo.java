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
    private byte[] photo;
    private String email;
    private ByteArrayOutputStream img;
    
    public CustomerInfo(int id, String firstname, String lastname, String dob, byte[] photo){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.photo = photo;
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
    
    public void setPic(byte[] pic) {
        this.photo = pic;  
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return firstname + " " + lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    
    public byte [] getPic()
    {
        if(img != null)
             return img.toByteArray();
        else 
            return photo;
    }
}

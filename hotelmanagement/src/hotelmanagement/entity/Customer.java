/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dell
 */
public class Customer {
    
    private String customerID;    // MAKH
    private String name;      // HOTEN
    private String password;      // PASSWORD
    private String citizenID;     // CCCD
    private String phoneNumber;   // SDT
    private Date dateOfBirth;     // NGAYSINH
    private String gender;        // GIOITINH
    private String address;       // DIACHI
    private String email;         // EMAIL

    public Customer(){
        
    }
    public Customer(String customerID, String name, String password, String citizenID, String phoneNumber, Date dateOfBirth, String gender, String address, String email) {
        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.citizenID = citizenID;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}



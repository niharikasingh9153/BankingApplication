package com.hdfcLife.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {

    private String customerId;
    private String name;
    private String phone;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

    public Customer(){}

    public Customer(String customerId, String name, String phone, String email, String password, LocalDate dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if (this==obj){
            return true;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(customerId,customer.customerId) && Objects.equals(email,customer.email);
    }

    @Override
    public int hashCode(){
       return Objects.hash(customerId,email);
    }
}

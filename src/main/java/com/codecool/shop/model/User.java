package com.codecool.shop.model;


public class User {
    private String username;
    private String password;
    private String role;
    public int id;
    Customer costumer;


    public User(){}



    public User(String user, String passw){
        this.username=user;
        this.password=passw;
        this.role="user";
        this.costumer=null;
    }

    public User(String user, String passw,String role,Customer customer){
        this.username=user;
        this.password=passw;
        this.role=role;
        this.costumer=customer;
    }



    public void setId(int newid) { this.id=newid; }

    public int getId() { return this.id; }

    public void setCustomer(Customer cust){
        this.costumer=cust;
    }
    public Customer getCostumer(){
        return this.costumer;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin(){
        return this.role.equals("admin");
    }
    public String getUsername() { return this.username; }

    public void setAdmin(){
        this.role="admin";
    }

    public String toString(){
        return this.username+this.password;
    }
}

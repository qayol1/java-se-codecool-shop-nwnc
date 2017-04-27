package com.codecool.shop.model;


public class User {
    public String username;
    public String password;
    public String role;
    public int id;

    public User(String user,String passw){
        this.username=user;
        this.password=passw;
        this.role="user";
    }

    public void setId(int newid) { this.id=newid; }

    public int getId() { return this.id; }

    public void setAdmin(){
        this.role="admin";
    }
}

package com.codecool.shop.customer;

/**
 * Created by abelvaradi on 2017.04.25..
 */
public class Customer {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Address billingAddress;
    private Address shippingAddress;

    public Customer(String name, String email, String phoneNumber, Address billingAddress,
                    Address shippingAddress){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * Created by abelvaradi on 2017.04.25..
 */
public class Customer {

    private int id;

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private Address billingAddress;
    private Address shippingAddress;
    private ArrayList<CreditCard> creditCards;
    private ArrayList<PayPal> payPals;

    public Customer(String firstName, String lastName, String email, String phoneNumber, Address billingAddress,
                    Address shippingAddress){
        this.lastName = lastName;
        this.firstName = firstName;
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

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void addCreditCard(CreditCard card) {
        creditCards.add(card);
    }

    public ArrayList<PayPal> getPayPals() {
        return payPals;
    }

    public void addPayPal(PayPal payPal) {
        payPals.add(payPal);
    }

}


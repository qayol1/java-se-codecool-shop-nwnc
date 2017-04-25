package com.codecool.shop.customer;

/**
 * Created by abelvaradi on 2017.04.25..
 */
public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private Address billingAddress;
    private Address shippingAddress;

    public Customer(String name, String email, String phoneNumber,
                    String billingCountry, String billingCity, String billingZipcode, String billingAddress,
                    String shippingCountry, String shippingCity, String shippingZipcode, String shippingAddress){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = new Address(billingCountry, billingCity, billingZipcode, billingAddress);
        this.shippingAddress = new Address(shippingCountry, shippingCity, shippingZipcode, shippingAddress);
    }

}

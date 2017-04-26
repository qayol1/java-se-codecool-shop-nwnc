package com.codecool.shop.customer;

/**
 * Created by abelvaradi on 2017.04.25..
 */
public class Address {
    private String country;
    private String city;
    private String zipcode;
    private String address;

    public Address(String country, String city, String zipcode, String address){
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.address = address;
    }
}

package com.codecool.shop.model;

/**
 * Created by abelvaradi on 2017.04.25..
 */
public class Address {
    public String country;
    public String city;
    public String zipcode;
    public String address;

    public Address(String country, String city, String zipcode, String address){
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.address = address;
    }
}

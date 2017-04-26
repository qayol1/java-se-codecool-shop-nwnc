package com.codecool.shop.controller;

import com.codecool.shop.customer.Address;
import com.codecool.shop.customer.Customer;
import spark.Request;

/**
 * Created by abelvaradi on 2017.04.26..
 */
public class CustomerController {

    public static Customer createCustomer(Request req) {
        return new Customer(req.queryParams("name"), req.queryParams("email"), req.queryParams("phone"),
                new Address(req.queryParams("billingCountry"), req.queryParams("billingCity"),
                        req.queryParams("billingZipcode"), req.queryParams("billingAddress")),
                new Address(req.queryParams("shippingCountry"), req.queryParams("shippingCity"),
                        req.queryParams("shippingZipcode"), req.queryParams("shippingAddress")));
    }
}

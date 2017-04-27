package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.CustomerDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Customer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CustomerController {

    public static void createCustomer(Request req) {
        CustomerDao customerDao = CustomerDaoMem.getInstance();
        customerDao.remove(1);
        customerDao.add(new Customer(req.queryParams("first_name"), req.queryParams("last_name"), req.queryParams("email"), req.queryParams("phone"),
                new Address(req.queryParams("billingCountry"), req.queryParams("billingCity"),
                        req.queryParams("billingZipcode"), req.queryParams("billingAddress")),
                new Address(req.queryParams("shippingCountry"), req.queryParams("shippingCity"),
                        req.queryParams("shippingZipcode"), req.queryParams("shippingAddress"))));
    }

    public static ModelAndView redirectCustomer(Request req) {
        CustomerDao customerDao = CustomerDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        createCustomer(req);
        System.out.println(customerDao.find(1).getLastName());

        Map params = new HashMap<>();
        params.put("customer", customerDao.find(1));
        params.put("shoppingcart", shoppingCartDataStore);
        return new ModelAndView(params, "product/payment");
    }
}

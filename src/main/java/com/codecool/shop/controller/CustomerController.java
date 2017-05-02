package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.CustomerDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Customer;
import spark.ModelAndView;
import spark.Request;
import java.util.HashMap;
import java.util.Map;
import static com.codecool.shop.model.CurrentUser.currentUser;

public class CustomerController {

    public static void createCustomer(Request req) {
        String checkb = req.queryParams("checkb");
        CustomerDao customerDao = CustomerDaoMem.getInstance();
        customerDao.remove(1);
        System.out.println(checkb);
        if (checkb!=null) {
            Address billingAddress = new Address(req.queryParams("billingCountry"), req.queryParams("billingCity"),
                    req.queryParams("billingZipcode"), req.queryParams("billingAddress"));
            Address shippingAddress = new Address(req.queryParams("billingCountry"), req.queryParams("billingCity"),
                    req.queryParams("billingZipcode"), req.queryParams("billingAddress"));
            customerDao.add(new Customer(req.queryParams("first_name"), req.queryParams("last_name"), req.queryParams("email"), req.queryParams("phone"),
                    billingAddress, shippingAddress));
        } else {
            Address billingAddress = new Address(req.queryParams("billingCountry"), req.queryParams("billingCity"),
                    req.queryParams("billingZipcode"), req.queryParams("billingAddress"));
            Address shippingAddress = new Address(req.queryParams("shippingCountry"), req.queryParams("shippingCity"),
                    req.queryParams("shippingZipcode"), req.queryParams("shippingAddress"));
            customerDao.add(new Customer(req.queryParams("first_name"), req.queryParams("last_name"), req.queryParams("email"), req.queryParams("phone"),
                    billingAddress, shippingAddress));
        }
    }

    public static ModelAndView redirectCustomer(Request req) {

        Object temp=req.session().attribute("currentuser");
        if (temp!=null){
            ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance(req);
            Map<String, Object> model=new HashMap<>();
            model.put("customer",currentUser(req).getCostumer());
            model.put("shoppingcart", shoppingCartDataStore);
            return new ModelAndView(model,"product/payment");
        }

        CustomerDao customerDao = CustomerDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance(req);
        createCustomer(req);

        Map params = new HashMap<>();
        params.put("customer", customerDao.find(1));
        params.put("shoppingcart", shoppingCartDataStore);
        return new ModelAndView(params, "product/payment");
    }
}

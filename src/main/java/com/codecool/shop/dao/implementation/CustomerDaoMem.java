package com.codecool.shop.dao.implementation;

import com.codecool.shop.customer.Customer;
import com.codecool.shop.dao.CustomerDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abelvaradi on 2017.04.26..
 */
public class CustomerDaoMem implements CustomerDao{

    private List<Customer> DATA = new ArrayList<>();
    private static CustomerDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CustomerDaoMem() {
    }

    public static CustomerDaoMem getInstance() {
        if (instance == null) {
            instance = new CustomerDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Customer customer) {
        customer.setId(DATA.size() + 1);
        DATA.add(customer);
    }

    @Override
    public Customer find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Customer> getAll() {
        return DATA;
    }
}

package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.model.Customer;
import com.codecool.shop.dao.CustomerDao;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoMem implements CustomerDao{

    private List<Customer> costumerData = new ArrayList<>();
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
    public int add(Customer customer) {
        customer.setId(costumerData.size() + 1);
        costumerData.add(customer);
        return customer.getId();
    }

    @Override
    public Customer find(int id) {
        return costumerData.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        costumerData.remove(find(id));
    }

    @Override
    public List<Customer> getAll() {
        return costumerData;
    }
}

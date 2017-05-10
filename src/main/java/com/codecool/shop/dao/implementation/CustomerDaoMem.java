package com.codecool.shop.dao.implementation;

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
    public void add(Customer customer) {
        customer.setId(costumerData.size() + 1);
        costumerData.add(customer);
    }

    @Override
    public Customer find(int id) {
        return costumerData.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean remove(int id) {
        if (find(id) != null) {
            costumerData.remove(find(id));
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return costumerData;
    }
}

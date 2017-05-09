package com.codecool.shop.dao;

import com.codecool.shop.model.Customer;
import java.util.List;

/**
 * Created by abelvaradi on 2017.04.26..
 */
public interface CustomerDao {

    void add(Customer supplier);
    Customer find(int id);
    boolean remove(int id);

    List<Customer> getAll();
}

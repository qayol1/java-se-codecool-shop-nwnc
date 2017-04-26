package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;

/**
 * Created by matyi on 2017.04.25..
 */
public interface ShoppingCartDao {

    Product find(int id);
    int getAllProducts();
    float getTotalPrice();
    void add(Product product);
    void decrease(int id);
    void remove(int id);

    HashMap<Product, Integer> getAll();
}

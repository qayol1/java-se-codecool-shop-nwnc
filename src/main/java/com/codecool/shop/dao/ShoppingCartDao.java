package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import spark.Request;


import java.util.HashMap;
import java.util.List;

/**
 * Created by matyi on 2017.04.25..
 */
public interface ShoppingCartDao {

    ShoppingCart find(int id);
    void remove(int id);
    int addNewCartToDb();
    List<ShoppingCart> getAll();
}

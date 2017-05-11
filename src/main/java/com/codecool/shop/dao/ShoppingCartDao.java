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
    void addNewCartElement(ShoppingCart cart,Product product);
    boolean isElementInCart(ShoppingCart cart,Product product);
    int getCartElementCount(ShoppingCart cart,Product product);
    void setElementCount(ShoppingCart cart,int productid,int newamount);
    void remove(int id);
    void removeElementFromCart(Product product,ShoppingCart cart);
    void deleteElementsFromCart(Product product,ShoppingCart cart);
    int addNewCartToDb();
    List<ShoppingCart> getAll();
}

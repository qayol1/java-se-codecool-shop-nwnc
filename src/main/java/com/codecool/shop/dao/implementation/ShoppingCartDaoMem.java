package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import spark.Request;

import java.util.*;


public class ShoppingCartDaoMem implements ShoppingCartDao {

    private List<ShoppingCart> DATA=new ArrayList<>();
    private static  ShoppingCartDaoMem instance = null;


    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance== null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ShoppingCart cart){
        DATA.add(cart);
    }

    @Override
    public ShoppingCart find(int id){
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<ShoppingCart> getAll() {
        return this.DATA;
    }

}

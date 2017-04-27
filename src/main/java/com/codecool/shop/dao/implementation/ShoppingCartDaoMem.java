package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private HashMap<Product, Integer> shoppingCart = new HashMap<>();
    private static  ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        if (shoppingCart.containsKey(product)) {
            shoppingCart.put(product, shoppingCart.get(product) + 1);
        } else {
            shoppingCart.put(product, 1);
        }
    }

    @Override
    public Product find(int id) {
        return shoppingCart.keySet().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public int getAllProducts() {
        int count = 0;
        for (Product prod : shoppingCart.keySet()) {
            count += shoppingCart.get(prod);
        }
        return count;
    }

    @Override
    public float getTotalPrice() {
        float total = 0;
        for (Product prod : shoppingCart.keySet()) {
            total += shoppingCart.get(prod) * prod.getDefaultPrice();
        }
        return total;
    }


    @Override
    public void decrease(int id) {
        if (shoppingCart.get(find(id)) > 1) {
            shoppingCart.put(find(id), shoppingCart.get(find(id)) - 1);
        } else {
            remove(id);
        }
    }

    @Override
    public void remove(int id) {
        shoppingCart.remove(find(id));
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return shoppingCart;
    }
}

package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import spark.Request;
import java.util.HashMap;


public class ShoppingCartDaoMem implements ShoppingCartDao {

    private HashMap<Product, Integer> shoppingCart;
    private static  ShoppingCartDaoMem instance = null;
    private Request req;


    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance(Request req) {

        if (req.session().attribute("shoppingcartdao")== null) {
            instance = new ShoppingCartDaoMem();
            instance.shoppingCart=new HashMap<>();
            instance.req=req;
            instance.saveShopCart();
            req.session().attribute("shoppingcartdao",instance);
        }
        instance.req=req;
        return instance;
    }

    @Override
    public void add(Product product) {
        openShopCart();
        if (shoppingCart.containsKey(product)) {
            shoppingCart.put(product, shoppingCart.get(product) + 1);
        } else {
            shoppingCart.put(product, 1);
        }
        saveShopCart();
    }

    @Override
    public Product find(int id) {
        openShopCart();
        return shoppingCart.keySet().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public int getAllProducts() {
        int count = 0;
       openShopCart();
        for (Product prod : shoppingCart.keySet()) {
            count += shoppingCart.get(prod);
        }
        return count;
    }

    @Override
    public float getTotalPrice() {
        float total = 0;
        openShopCart();
        for (Product prod : shoppingCart.keySet()) {
            total += shoppingCart.get(prod) * prod.getDefaultPrice();
        }
        return total;
    }


    @Override
    public void decrease(int id) {
        openShopCart();
        if (shoppingCart.get(find(id)) > 1) {
            shoppingCart.put(find(id), shoppingCart.get(find(id)) - 1);
            saveShopCart();
        } else {
            remove(id);
            saveShopCart();
        }

    }

    @Override
    public void remove(int id) {
        openShopCart();
        shoppingCart.remove(find(id));
        saveShopCart();
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return req.session().attribute("shoppingcart");
    }

    public void openShopCart(){
        instance.shoppingCart=req.session().attribute("shoppingcart");

    }

    public void saveShopCart(){
        req.session().attribute("shoppingcart", shoppingCart);
    }


}

package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.sun.scenario.effect.impl.prism.PrDrawable;
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
    public int getCartElementCount(ShoppingCart cart,Product product) { return 0;}

    @Override
    public void addNewCartElement(ShoppingCart cart,Product product){}

    @Override
    public boolean isElementInCart(ShoppingCart cart, Product product){
        return true;
    }

    @Override
    public void removeElementFromCart(Product product,ShoppingCart cart) {

    }

    @Override
    public void deleteElementsFromCart(Product product,ShoppingCart cart){}


    @Override
    public int addNewCartToDb(){
        return 1;
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
    public void setElementCount(ShoppingCart cart,int productid,int newamount) {

    }

    @Override
    public List<ShoppingCart> getAll() {
        return this.DATA;
    }

}

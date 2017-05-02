package com.codecool.shop.model;


import java.util.HashMap;

public class ShoppingCart {
    int id;
    HashMap <Integer,Product> Products;
    private static int currentid=1;

    ShoppingCart(){
        this.id=currentid;
        currentid++;
        Products=new HashMap<>();
    }

    Integer sizeOfProducts(){
        return this.Products.size();
    }

    void addProduct(Product product){
        this.Products.put(this.sizeOfProducts(),product);
    }

    HashMap <Integer,Product> getProducts(){
        return this.Products;
    }



}

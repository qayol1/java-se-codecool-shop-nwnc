package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.customer.Customer;
import com.codecool.shop.customer.Address;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        params.put("shoppingcart", shoppingCartDataStore);
        return new ModelAndView(params, "product/index");
    }


    public  static ModelAndView addToCart(Request req, Response res) {

        int id = Integer.parseInt(req.queryParams("id"));

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        shoppingCartDataStore.add(productDataStore.find(id));
        return renderProducts(req, res);
    }

    public static ModelAndView renderCart(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        params.put("shoppingcart", shoppingCartDataStore.getAll());
        return new ModelAndView(params, "product/shoppingcart");
    }

}

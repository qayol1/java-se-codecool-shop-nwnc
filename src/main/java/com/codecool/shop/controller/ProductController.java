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

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.*;

public class ProductController {

    public static ModelAndView renderAllProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoMem.getInstance();
        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("suppliers", supplierDaoDataStore.getAll());
        params.put("shoppingcart", shoppingCartDataStore);
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderFilteredProducts(Request req, Response res) {
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoMem.getInstance();
        Map params = new HashMap<>();
        List<ProductCategory> productCategoryList = storeCheckboxStatus(productCategoryDataStore.getAll(),req);
        List<Supplier> supplierList = storeChechboxStatus(supplierDaoDataStore.getAll(),req);
        params.put("category",productCategoryList);
        params.put("products", filterBySupplier(req,filterByCategory(req, productDataStore.getAll())));
        params.put("suppliers", supplierList);
        params.put("shoppingcart", shoppingCartDataStore);
        return new ModelAndView(params, "product/index");
    }


    public  static ModelAndView addToCart(Request req, Response res) {

        int id = Integer.parseInt(req.queryParams("id"));

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        shoppingCartDataStore.add(productDataStore.find(id));
        return renderAllProducts(req, res);
    }

    public static ModelAndView renderCart(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        params.put("shoppingcart", shoppingCartDataStore);
        return new ModelAndView(params, "product/shoppingcart");
    }


    private static List<Supplier> storeChechboxStatus(List<Supplier> suplierList, Request req) {
        for (Supplier supplier: suplierList){
            if (supplier.getName().equals(req.queryParams(supplier.getName()))) {
                supplier.setCheckedCheckbox(true);
            } else {
                supplier.setCheckedCheckbox(false);
            }
        }
        return suplierList;
    }

    private static List<ProductCategory> storeCheckboxStatus(List<ProductCategory> productCategoryList, Request req) {
        for (ProductCategory category: productCategoryList){
            if (category.getName().equals(req.queryParams(category.getName()))) {
                category.setCheckedCheckbox(true);
            } else {
                category.setCheckedCheckbox(false);
            }
        }
        return productCategoryList;
    }

    private static Set filterByCategory(Request req, List<Product> products) {
        Set<Product> filteredByCategoryProductList = new HashSet<>();
        for (String param: req.queryParams()) {
            for (Product product: products) {
                if (param.equals(product.getProductCategory().getName())) {
                    filteredByCategoryProductList.add(product);
                }
            }
        }
        return filteredByCategoryProductList;
    }

    private static Set filterBySupplier(Request req, Set<Product> filteredByCategoryProductList) {
        Set<Product> filteredProductList = new HashSet<>();
        for (String param : req.queryParams()) {
            for (Product product : filteredByCategoryProductList) {
                if (param.equals(product.getSupplier().getName())) {
                    filteredProductList.add(product);
                }
            }
        }
        return filteredProductList;
    }

}

package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
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
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoMem.getInstance();
        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("suppliers", supplierDaoDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderFilteredProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoMem.getInstance();
        Map params = new HashMap<>();
        for (ProductCategory category: productCategoryDataStore.getAll()){
            if (category.getName().equals(req.queryParams(category.getName()))) {
                category.setCheckedCheckBox(true);
            } else {
                category.setCheckedCheckBox(false);
            }
        }
        for (Supplier supplier: supplierDaoDataStore.getAll()){
            if (supplier.getName().equals(req.queryParams(supplier.getName()))) {
                supplier.setCheckedCheckBox(true);
            } else {
                supplier.setCheckedCheckBox(false);
            }
        }
        params.put("category",productCategoryDataStore.getAll());
        params.put("products", filterBySupplier(req,filterByCategory(req, productDataStore.getAll())));
        params.put("suppliers", supplierDaoDataStore.getAll());
        return new ModelAndView(params, "product/index");
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

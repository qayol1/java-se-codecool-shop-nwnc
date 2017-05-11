package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import com.codecool.shop.util.ProductFilter;
import spark.Request;
import static com.codecool.shop.model.CurrentUser.*;
import spark.Response;
import spark.ModelAndView;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static com.codecool.shop.util.RequestUtil.*;

import java.util.*;

public class ProductController {

    public static Route renderAllProducts = (Request req, Response res) -> {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierDaoDataStore = SupplierDaoJDBC.getInstance();
        ProductFilter filter=new ProductFilter();
        Map params = new HashMap<>();

        if (getSessionShoppingCart(req) == null) {
                setSessionShoppingcart(req, new ShoppingCart());
            }

        if (req.queryString() != null && req.queryString().length()!=0){

            String[] categoryNameList = req.queryMap().toMap().get("category");
            String[] supplierNameList = req.queryMap().toMap().get("supplier");
            filter.init(categoryNameList,supplierNameList);
            List<ProductCategory> productCategoryList = getRequestedCategories(categoryNameList);
            List<Supplier> productSupplierList = getRequestedSuppliers(supplierNameList);
            String username=req.session().attribute("currentuser");

            if (username!=null) {

                System.out.println("itt");
                UserDao userDataBase=UserDaoJDBC.getInstance();
                User usr= userDataBase.find(username);

                ShoppingCart cart=usr.getCostumer().getShoppingCart();


                System.out.println(cart.getAllProducts());
                params.put("products", filterBySupplier(productSupplierList));
                params.put("category", productCategoryList);
                params.put("filter",filter);
                params.put("shoppingcart", cart);

            return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
            }
            Set<Product> products = filterBySupplier(productSupplierList);

            params.put("products", filterBySupplier(productSupplierList));
            params.put("category", productCategoryList);
            params.put("filter",filter);
            params.put("shoppingcart", getSessionShoppingCart(req));

            return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
        } else {

            filter.init();
            params.put("category", productCategoryDataStore.getAll());
            params.put("products", productDataStore.getAll());
            params.put("filter",filter);
            params.put("shoppingcart", getSessionShoppingCart(req));
            return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/index"));
        }
    };



    public static Route addToCart = (Request req, Response res) -> {
        int id = Integer.parseInt(req.queryParams("id"));
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        ShoppingCart cart = req.session().attribute("cart");
        cart.add(productDataStore.find(id));
        req.session().attribute("cart", cart);
        return true;
    };

    public static Route categoryListSize= (Request req,Response res) -> {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        return productCategoryDataStore.getAll().size();

    };

    public static Route removeFromCart = (Request req, Response res) -> {
        int id = Integer.parseInt(req.queryParams("id"));
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ShoppingCart cart = getSessionShoppingCart(req);
        cart.decrease(id);
        setSessionShoppingcart(req,cart);
        return true;
    };

    public static Route deleteFromCart = (Request req, Response res) -> {
        int id = Integer.parseInt(req.queryParams("id"));
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ShoppingCart cart = getSessionShoppingCart(req);
        cart.remove(id);
        setSessionShoppingcart(req,cart);
        return true;
    };


    public static Route renderCart = (Request req, Response res) -> {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
        ShoppingCart cart = getSessionShoppingCart(req);
        Map params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        params.put("shoppingcart", cart);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/shoppingcart"));
    };




    private static  List<ProductCategory> getRequestedCategories(String[] categoryNames){
        if (categoryNames==null)
        {
            return null;
        }
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        List<ProductCategory> allProductCategories = productCategoryDataStore.getAll();

        for (ProductCategory category : allProductCategories) {
            for (String categoryName : categoryNames) {
                if (category.getName().equals(categoryName)) {
                    productCategoryList.add(category);
                }
            }
        }
        return productCategoryList;
    }

    private static  List<Supplier> getRequestedSuppliers(String[] supplierNames){

        if (supplierNames==null){
            return null;
        }

        List<Supplier> supplierList=new ArrayList<>();
        SupplierDao supplierDao = SupplierDaoJDBC.getInstance();
        List<Supplier> allSuppliers=supplierDao.getAll();

        for (Supplier supplier : allSuppliers) {
            for (String supplierName : supplierNames) {
                if (supplier.getName().equals(supplierName)){
                    supplierList.add(supplier);
                }
            }
        }
        return supplierList;
    };


    private static Set filterBySupplier(List<Supplier> suppliers) {
        if (suppliers==null){
            return null;
        }
        System.out.println(suppliers);
        ProductDaoJDBC productDaoJDBC=ProductDaoJDBC.getInstance();
        Set<Product> filteredProductList = new HashSet<>();
        for (Supplier supplier : suppliers) {
            for (Product product : productDaoJDBC.getAll()) {
                if (supplier.getName().equals(product.getSupplier().getName())) {
                    filteredProductList.add(product);
                }
            }
        }
        return filteredProductList;
    }

    public static Route shoppingCartSize = (Request req,Response res) -> {
        return getSessionShoppingCart(req).getAllProducts();
    };

}

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.CustomerController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {
        final String SESSION_NAME = "username";

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        get("/shoppingcart", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.renderCart(req, res) );
        });

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Equivalent with above
        get("/", (Request req, Response res) -> {
            String name = req.session().attribute(SESSION_NAME);
            System.out.println(name);
            if (name == null) {
                req.session().attribute(SESSION_NAME, "Anonymus");
                System.out.println(name);
            }
            String name2 = req.session().attribute(SESSION_NAME);
            System.out.println(name2);
           return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });

        post("/checkout", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( CustomerController.redirectCustomer(req) );
        });

        post("/add-to-cart", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.addToCart(req, res) );
        });

        post("/remove-from-cart", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.removeFromCart(req, res) );
        });

        post("/delete-from-cart", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.deleteFromCart(req, res) );
        });


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CustomerDao CustomerDataStore = CustomerDaoMem.getInstance();


        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

    }


}

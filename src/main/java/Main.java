import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();


        // Always add generic routes to the end
        get("/", ProductController::renderAllProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
           return new ThymeleafTemplateEngine().render( ProductController.renderAllProducts(req, res) );
        });
        post("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.renderFilteredProducts(req, res) );
        });

        post("/getCategoryListSize", (Request req, Response res) -> {
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            return productCategoryDataStore.getAll().size();
        });

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "High-tech electronics manufacturing and digital media.");
        supplierDataStore.add(samsung);
        Supplier gigabyte = new Supplier("Gigabyte", "International manufacturer and distributor of computer hardware products.");
        supplierDataStore.add(gigabyte);
        Supplier msi = new Supplier("MSI", "International manufacturer and distributor of computer hardware products.");
        supplierDataStore.add(msi);
        Supplier asus = new Supplier("Asus", "International manufacturer and distributor of computer hardware products.");
        supplierDataStore.add(asus);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "Is a small, portable personal computer with a \"clamshell\" form factor.");
        productCategoryDataStore.add(laptop);
        ProductCategory videoCard = new ProductCategory("Video Card", "Hardware", "A video card (also called a display card, graphics card) is an expansion card which generates a feed of output images to a display ");
        productCategoryDataStore.add(videoCard);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Chromebook 3 ", 178, "USD", "Unleash the power of modern computing with this Samsung Chromebook laptop.", laptop, samsung));
        productDataStore.add(new Product("Samsung Notebook 9 pro", 1299, "USD", "You can watch movies and TV shows in rich detail on the 15.6 touch screen, which displays a 4K UHD picture.", laptop, samsung));
        productDataStore.add(new Product("Gigabyte Geforce GTX 1070 G1", 389, "USD", "GIGABYTE G1 Gaming Series graphics cards are crafted for perfection in pursuit of the ultimate graphics experience for gaming enthusiasts.", videoCard, gigabyte));
        productDataStore.add(new Product("MSI Radeon RX 470 Gaming", 219, "USD", "The 4th generation GCN architecture is engineered for gamers who play anything from the latest MOBA’s to the most popular AAA titles.", videoCard, msi));
        productDataStore.add(new Product("Asus ZenPad 8", 149, "USD", "The design of ASUS ZenPad 8.0 carries modern influences and a simple, clean look that gives it an universal and stylish appeal.", tablet, asus));
        productDataStore.add(new Product("Asus ZenBook UX530UX", 749, "USD", "Asus - ZenBook Flip UX360CA 2-in-1 13.3\" Touch-Screen Laptop - Intel Core m3 - 8GB Memory - 512GB Solid State Drive - Mineral gray", laptop, asus));
        productDataStore.add(new Product("Asus Dual GeForce GTX 580", 1499, "USD", "The MARS II is the first dual GeForce GTX 580 card, and is part of ASUS Republic of Gamers (ROG) brand of premium products targeting the gamer-overclocker market. ", videoCard, asus));


    }


}

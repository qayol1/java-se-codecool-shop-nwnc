import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;
import com.codecool.shop.controller.CustomerController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.login.LoginHandler;
import com.codecool.shop.util.ExampleData;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        ExampleData.populateData();

        get("/", ProductController.renderAllProducts);
        get("/index", ProductController.renderAllProducts);

        post("/login", LoginHandler.manageLogin);
        get("/admin",LoginHandler.adminPage);
        post("/isuserlogged", LoginHandler.isUserLogged);
        get("/logout", LoginHandler.logout);

        get("/shoppingcart", ProductController.renderCart);
        post("/checkout", CustomerController.redirectCustomer);
        get("/checkout", CustomerController.redirectCustomer);

        post("/getCategoryListSize", ProductController.categoryListSize);

        post("/add-to-cart", ProductController.addToCart);
        post("/remove-from-cart", ProductController.removeFromCart);
        post("/delete-from-cart", ProductController.deleteFromCart);
        post("/get-shoppingcart-size",ProductController.shoppingCartSize);

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }
}

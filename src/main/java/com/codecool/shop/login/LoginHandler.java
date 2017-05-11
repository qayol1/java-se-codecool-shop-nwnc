package com.codecool.shop.login;
import static com.codecool.shop.model.CurrentUser.*;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.database.ShoppingCartDaoJDBC;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import com.codecool.shop.util.RequestUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class LoginHandler {

    public static Route manageLogin = (Request request, Response response) -> {

        String username = request.queryParams("username");
        RequestUtil.setSessionUser(request,username);

        User user=currentUser(request);
        if (user == null) {
            response.redirect("/index");
            return null;
        }

        if (user.isAdmin()) {
            response.redirect("/admin");
            return null;
        }

        String fromPage = request.queryParams("place");
        ShoppingCartDao shoppingCartDao= ShoppingCartDaoJDBC.getInstance();

        ShoppingCart sessionSC=RequestUtil.getSessionShoppingCart(request);
        ShoppingCart cartDatabase=user.getCostumer().getShoppingCart();
        shoppingCartDao.mergeCarts(sessionSC,cartDatabase);
        RequestUtil.setSessionShoppingcart(request,new ShoppingCart());
        if (fromPage.equals("main")) {
            response.redirect("/index");
            return null;
        }
        response.redirect("/checkout");
        return null;


    };

    public static Route adminPage = (Request request, Response response) -> {

        if (currentUser(request) == null) {
            response.redirect("/index");
            return null;
        }

        if (!currentUser(request).isAdmin()) {
            response.redirect("/index");
            return null;
        }
        Map<String, Object> model = new HashMap<>();
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "product/admin"));
    };

    public static Route isUserLogged = (Request request, Response response) -> {
        if (isUserLoggedIn(request)) {
            return currentUser(request).getCostumer().getFirstName();
        }
        return "nulluser";
    };

    public static Route logout = (Request req, Response res) -> {
        req.session().removeAttribute("currentuser");
        res.redirect("/index");
        return null;
    };

}

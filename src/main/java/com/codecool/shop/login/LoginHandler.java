package com.codecool.shop.login;


import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
import static com.codecool.shop.model.CurrentUser.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class LoginHandler {

    public static Route manageLogin = (Request request, Response response) -> {

        String username=request.queryParams("username");
        request.session().attribute("currentuser",username);


        if (currentUser(request)== null){
            response.redirect("/index");
            return null;
        }
        if (currentUser(request).isAdmin()){
            response.redirect("/admin");
            return null;
        }

        String fromPage=request.queryParams("place");
        if (fromPage.equals("main")) {
            response.redirect("/index");
        }
        response.redirect("/checkout");
        return null;


    };

    public static Route adminPage = (Request request, Response response) -> {

        if (currentUser(request)==null){
            response.redirect("/index");
         return null;
        }

        if (!currentUser(request).isAdmin()) {
         response.redirect("/index");
         return null;
        }
        Map<String, Object> model=new HashMap<>();
        return new ThymeleafTemplateEngine().render(new ModelAndView(model,"product/admin"));
    };

    public static Route  isUserLogged = (Request request, Response response) -> {

        if (currentUser(request)==null){
            return false;
        }
        response.redirect("/checkout");
        return true;
    };

}

package com.codecool.shop.login;


import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
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
        Map<String, Object> model=new HashMap<>();
        User currentUser = UserDaoMem.getInstance().find(username);
        request.session().attribute("current",currentUser);

        if (currentUser.getUsername()== null){
            response.redirect("/index");
            return null;
        }
        if (currentUser.isAdmin()){
            response.redirect("/admin");
            return null;
        }
        response.redirect("/checkout");
        return null;


    };

    public static Route adminPage = (Request request, Response response) -> {

        if (request.session().attribute("current")==null){
            response.redirect("/index");
         return null;
        }
        User currentuser=request.session().attribute("current");
        if (!currentuser.isAdmin()) {
         response.redirect("/index");
         return null;
        }
        Map<String, Object> model=new HashMap<>();
        return new ThymeleafTemplateEngine().render(new ModelAndView(model,"product/admin"));
    };
}

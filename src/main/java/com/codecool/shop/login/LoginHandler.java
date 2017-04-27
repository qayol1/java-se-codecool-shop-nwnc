package com.codecool.shop.login;


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
        if (currentUser.getUsername()== null){
            response.redirect("/index");
            return null;
        }
        System.out.println("itt");
        request.session().attribute("current",currentUser);
        User temp=request.session().attribute("current");
        model.put("username",temp.getUsername());
        return new ThymeleafTemplateEngine().render(new ModelAndView(model,"product/login"));
    };

    public static Route showLogin = (Request request, Response response) -> {

        Map<String, Object> model=new HashMap<>();
        User temp=request.session().attribute("current");
        System.out.println(temp);
        model.put("username",temp.getUsername());
        return new ThymeleafTemplateEngine().render(new ModelAndView(model,"product/login"));
    };
}

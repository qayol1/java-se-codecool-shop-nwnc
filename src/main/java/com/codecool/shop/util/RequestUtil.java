package com.codecool.shop.util;



import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import spark.Request;

public class RequestUtil {

    public static String getSessionUser(Request request){
        return request.session().attribute("currentuser");
    }

    public static void setSessionUser(Request request,String username){
        request.session().attribute("currentuser",username);
    }

    public static ShoppingCart getSessionShoppingCart(Request request){
        return request.session().attribute("cart");
    }

    public static void setSessionShoppingcart(Request request, ShoppingCart cart){
        request.session().attribute("cart",cart);
    }

}

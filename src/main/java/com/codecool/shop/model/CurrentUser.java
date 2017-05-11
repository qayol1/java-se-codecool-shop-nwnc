package com.codecool.shop.model;



import com.codecool.shop.dao.implementation.database.UserDaoJDBC;
import com.codecool.shop.util.RequestUtil;
import spark.Request;

public class CurrentUser {
    public static User currentUser(Request request){

        String username = RequestUtil.getSessionUser(request);
        return UserDaoJDBC.getInstance().find(username);
    }

    public static boolean isUserLoggedIn(Request request) {
        if (RequestUtil.getSessionUser(request)==null){
            return false;
        } else {
            return true;
        }
    }
}

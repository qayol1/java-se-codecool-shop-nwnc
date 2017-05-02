package com.codecool.shop.model;



import com.codecool.shop.dao.implementation.UserDaoMem;
import spark.Request;

public class CurrentUser {
    public static User currentUser(Request request){

        String username=request.session().attribute("currentuser");
        return UserDaoMem.getInstance().find(username);
    }
}

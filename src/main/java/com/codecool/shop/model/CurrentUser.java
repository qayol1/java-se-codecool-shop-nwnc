package com.codecool.shop.model;



import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.util.RequestUtil;
import spark.Request;
import com.codecool.shop.util.RequestUtil.*;

public class CurrentUser {
    public static User currentUser(Request request){

        String username = RequestUtil.getSessionUser(request);
        return UserDaoMem.getInstance().find(username);
    }
}

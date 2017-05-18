package com.codecool.shop.model;

/**
 * Created by peter on 2017.04.27..
 * Base model for pay pal paying
 */
public class PayPal {

    private String username;
    private String password;

    /**
     *
     * @return username gives back the username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username sets the paypal paying user name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return returns the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password sets the paypal aying the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

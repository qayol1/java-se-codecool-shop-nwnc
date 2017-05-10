package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import com.codecool.shop.util.DbConnect;

import java.sql.*;
import java.util.List;


public class UserDaoJDBC implements UserDao {

    private static UserDaoJDBC instance = null;


    private UserDaoJDBC() {
    }

    public static UserDaoJDBC getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(User user) {
       try {
            PreparedStatement stmt;
            stmt = DbConnect.getConnection().prepareStatement(
                    ("INSERT INTO users" +
                            "(username," +
                            "password," +
                            "role,"+
                            "customerid"+") VALUES (?, ?, ?,?)"));
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getCostumer().getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public User find(String username) {
       String query = "SELECT * FROM product WHERE username ='" + username + "';";

        try (Connection connection = DbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ){
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


            while (resultSet.next()){

                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                        //resultSet.getString("role"));
                        //resultSet.getString("description"),
                       // shoppingcart.find(resultSet.getInt("productcategory")),
                       // supplierDataStore.find(resultSet.getInt("supplier")));
                        user.setId(resultSet.getInt(1));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}

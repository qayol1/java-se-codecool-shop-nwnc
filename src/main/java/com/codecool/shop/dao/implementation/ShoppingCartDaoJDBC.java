package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.util.DbConnect;
import jdk.management.resource.internal.inst.SocketRMHooks;

import java.sql.*;
import java.util.HashMap;
import java.util.List;


public class ShoppingCartDaoJDBC implements ShoppingCartDao {
    private static ShoppingCartDaoJDBC instance = null;


    private ShoppingCartDaoJDBC() {
    }

    public static ShoppingCartDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoJDBC();
        }
        return instance;
    }

    @Override
    public void addNewCartElement(ShoppingCart cart,Product product){
        try (
                PreparedStatement stmt = DbConnect.getConnection().prepareStatement(
                    ("INSERT INTO shoppingcarelements" +
                            "(shoppingcartid," +
                            "productid," +
                            "productcount"+") VALUES (?, ?, ?)"),Statement.RETURN_GENERATED_KEYS);
            ) {

                stmt.setInt(1, cart.getId());
                stmt.setInt(2, product.getId());
                stmt.setInt(3, 1);
                System.out.println("lefut");
                stmt.executeUpdate();
            } catch (SQLException e){
                e.getStackTrace();
        }

    }

    @Override
    public int addNewCartToDb(){

           try (
            Connection connection = DbConnect.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO shoppingcarts DEFAULT VALUES;",Statement.RETURN_GENERATED_KEYS);

           ) {
            stmt.executeUpdate();
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }






    @Override
    public ShoppingCart find(int id) {
       String query = "SELECT * FROM shoppingcarelements WHERE shoppingcartid ='" + id + "';";

        try (Connection connection = DbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ){
             ProductDao productDataStore=ProductDaoJDBC.getInstance();
             HashMap<Product,Integer> cartelements=new HashMap<>();

            while (resultSet.next()) {
                    cartelements.put(productDataStore.find(resultSet.getInt("productid")),resultSet.getInt("productcount"));
            }

            if (cartelements.size()==0){
                return null;
            }

            return new ShoppingCart(id,cartelements);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public List<ShoppingCart> getAll() {
        return null;
    }
}

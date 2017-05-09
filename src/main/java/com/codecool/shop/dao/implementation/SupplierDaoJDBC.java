package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abelvaradi on 2017.05.09..
 */
public class SupplierDaoJDBC implements SupplierDao {
    private static SupplierDaoJDBC instance = null;

    private DbConnect dbConnect = new DbConnect("src/main/resources/connection/properties/connectionProperties.txt");

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        try {
            PreparedStatement stmt;
            stmt = dbConnect.getConnection().prepareStatement(
                    ("INSERT INTO supplier" +
                            "(name," +
                            "description) VALUES (?, ?)"));
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM supplier WHERE id ='" + id + "';";

        try (Connection connection = dbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)

        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                supplier.setId(resultSet.getInt(1)));
                return supplier;
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
    public List<Supplier> getAll() {

    }
}




package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abelvaradi on 2017.05.09..
 */
public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private static ProductCategoryDaoJDBC instance = null;

    private DbConnect dbConnect = new DbConnect("src/main/resources/connection/properties/connectionProperties.txt");

    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory productCategory) {
        try {
            PreparedStatement stmt;
            stmt = dbConnect.getConnection().prepareStatement(
                    ("INSERT INTO productcategory (name, department, description) VALUES (?, ?, ?)"));
            stmt.setString(1, productCategory.getName());
            stmt.setString(2, productCategory.getDepartment());
            stmt.setString(3, productCategory.getDescription());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {

        String query = "SELECT * FROM productcategory WHERE id ='" + id + "';";

        try (Connection connection = dbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)

        ){
            while (resultSet.next()){
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                productCategory.setId(resultSet.getInt(1));
                return productCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM productcategory WHERE id=" + id + ";";
        try (Connection connection = dbConnect.getConnection();
             Statement statement = connection.createStatement();
        ) {             statement.executeQuery(query) ; }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM productcategory;";

        List<ProductCategory> productCategoryList = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                productCategory.setId(resultSet.getInt(1));
                productCategoryList.add(productCategory);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategoryList;

    }
}


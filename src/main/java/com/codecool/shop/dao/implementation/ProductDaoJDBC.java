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
 * Created by abelvaradi on 2017.05.08..
 */
public class ProductDaoJDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;

    private DbConnect dbConnect = new DbConnect("src/main/resources/connection/properties/connectionProperties.txt");

    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        try {
            PreparedStatement stmt;
            stmt = dbConnect.getConnection().prepareStatement(;
                    ("INSERT INTO product" +
                            "(name," +
                            "description," +
                            "defaultprice," +
                            "currencystring," +
                            "productcategory," +
                            "supplier) VALUES (?, ?, ?, ?, ?, ?)"));
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setFloat(3, product.getDefaultPrice());
            stmt.setString(4, String.valueOf(product.getDefaultCurrency()));
            stmt.setInt(5, product.getProductCategory().getId());
            stmt.setInt(6, product.getSupplier().getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM product WHERE id ='" + id + "';";

        try (Connection connection = dbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ){
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


            while (resultSet.next()){


                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultprice"),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        productCategoryDataStore.find(resultSet.getInt("productcategory")),
                        supplierDataStore.find(resultSet.getInt("supplier")));
                product.setId(resultSet.getInt(1));
                return product;
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
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";
        return this.getProducts(query);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product WHERE supplier ='" + supplier.getId() + "';";
        return this.getProducts(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE productcategory ='" + productCategory.getId() + "';";
        return this.getProducts(query);
    }


}

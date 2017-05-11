package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
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
            stmt = DbConnect.getConnection().prepareStatement(
                    ("INSERT INTO supplier (name, description) VALUES (?, ?)"));
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next()) {
                supplier.setId(resultSet.getInt(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM supplier WHERE id ='" + id + "';";

        try (Connection connection = DbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)

        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt(1));
                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM supplier WHERE id=" + id + ";";
        try (Connection connection = DbConnect.getConnection();
             Statement statement = connection.createStatement();
        ) {             statement.executeQuery(query) ; }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier;";

        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = DbConnect.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                supplier.setId(resultSet.getInt(1));
                supplierList.add(supplier);
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
            }
            return supplierList;

    }
}




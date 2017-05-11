package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.util.DbConnect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matyi on 2017.05.10..
 */
class SupplierDaoJDBCTest {

    private DbConnect dbConnect = new DbConnect("src/main/resources/connection/properties/connectionPropertiesForTest.txt");

    @BeforeEach
    void setUp() {
        String query = "TRUNCATE TABLE productcategory, supplier, product";
        try {
            Connection connection = dbConnect.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsProductCategoryDaoMemIsSingletone () {
        SupplierDao supplierDataStore1 = SupplierDaoJDBC.getInstance();
        SupplierDao supplierDataStore2 = SupplierDaoJDBC.getInstance();
        assertEquals(supplierDataStore1.hashCode(),supplierDataStore2.hashCode());
    }

    @Test
    void getInstance() {
    }

    @Test
    void add() {
    }

    @Test
    void find() {
    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }

}
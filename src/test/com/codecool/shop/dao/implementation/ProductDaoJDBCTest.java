package com.codecool.shop.dao.implementation;

import com.codecool.shop.util.DbConnect;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matyi on 2017.05.10..
 */
class ProductDaoJDBCTest {

    private DbConnect dbConnect = new DbConnect("src/main/resources/connection/properties/connectionPropertiesForTest.txt");

    @BeforeEach
    void setUp() {
        String query = "TRUNCATE TABLE productcategory, supplier, product";
        try {
            Statement statement =dbConnect.getConnection().createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
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
    void getAll() {
    }

    @Test
    void getBy() {
    }

    @Test
    void getBy1() {
    }

    @Test
    void remove() {
    }

}
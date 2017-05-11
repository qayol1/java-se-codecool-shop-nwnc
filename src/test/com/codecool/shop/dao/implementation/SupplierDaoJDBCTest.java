package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matyi on 2017.05.10..
 */
class SupplierDaoJDBCTest {

    private static String filepath = "src/main/resources/connection/properties/connectionPropertiesForTest.txt";

    @Test
    public void testIsProductCategoryDaoMemIsSingletone () {
        SupplierDao supplierDataStore1 = SupplierDaoJDBC.getInstance();
        SupplierDao supplierDataStore2 = SupplierDaoJDBC.getInstance(filepath);
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
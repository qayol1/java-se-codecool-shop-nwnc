package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by peter on 2017.05.09..
 */
class ProductCategoryDaoMemTest {

    @Test
    public void testSameCategoryNameCannotAdd() {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductCategory tablet1 = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet1);
        ProductCategory tablet2 = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet2);
        int size = productCategoryDataStore.getAll().size();
        System.out.println(productCategoryDataStore.getAll());
        assertEquals(1,size);
    }

}
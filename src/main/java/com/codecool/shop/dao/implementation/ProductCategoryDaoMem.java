package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;


public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        ArrayList<String> categoryNames = new ArrayList<>();
        for (ProductCategory productCategory:DATA)  {
            categoryNames.add(productCategory.getName());
        }
        if (!categoryNames.contains(category.getName())) {
            category.setId(DATA.size() + 1);
            DATA.add(category);
        }
    }

    @Override
    public ProductCategory find(int id) {
        return DATA.stream().filter(t -> t.getId()==id).findFirst().orElse(null);
    }

    @Override
    public boolean remove(int id) {
        if(find(id )!=null) {
            DATA.remove(find(id));
            return true;
        }
        return false;
    }

    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }
}

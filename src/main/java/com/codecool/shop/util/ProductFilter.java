package com.codecool.shop.util;


import java.util.ArrayList;
import java.util.List;


public class ProductFilter {
    private List<FilterCategory> filterCategories;

    public ProductFilter(){
        this.filterCategories=new ArrayList<>();
    }


    public void add(FilterCategory filterCategory) {
        this.filterCategories.add(filterCategory);
    }

    public List<FilterCategory> getFilterCategories(){
        return this.filterCategories;
    }

    public void init(String[] categories,String[] suppliers){
        FilterCategory category= initCategory(categories);
        FilterCategory supplier=initSuppliers(suppliers);
        add(category);
        add(supplier);
    }

    FilterCategory initCategory(String[] categories){
        FilterCategory category=new FilterCategory("category","Product category:");

        category.add(new FilterCategoryElement("Tablet"));
        category.add(new FilterCategoryElement("Laptop"));
        category.add(new FilterCategoryElement("Video Card"));

        if (categories==null){
            return category;
        }
        for (FilterCategoryElement element : category.getElements()){
            for (String s:categories){
                if (element.getName().equals(s)){
                    element.setChecked(true);
                }
            }
        }
        return category;
    }

    FilterCategory initSuppliers(String[] suppliers){
        FilterCategory supplier=new FilterCategory("supplier","Suppliers:");
        supplier.add(new FilterCategoryElement("Amazon"));
        supplier.add(new FilterCategoryElement("Lenovo"));
        supplier.add(new FilterCategoryElement("Samsung"));
        supplier.add(new FilterCategoryElement("Gigabyte"));
        supplier.add(new FilterCategoryElement("MSI"));
        supplier.add(new FilterCategoryElement("Asus"));

        if (suppliers==null){
            return supplier;
        }

        for (FilterCategoryElement element : supplier.getElements()){
            for (String s:suppliers){
                if (element.getName().equals(s)){
                    element.setChecked(true);
                }
            }
        }
        return supplier;
    }

    public void init(){
        FilterCategory category= initCategory();
        FilterCategory supplier= initSuppliers();
        add(category);
        add(supplier);
    }

    FilterCategory initCategory(){
        FilterCategory category=new FilterCategory("category","Product category:");

        category.add(new FilterCategoryElement("Tablet"));
        category.add(new FilterCategoryElement("Laptop"));
        category.add(new FilterCategoryElement("Video Card"));
        return category;
    }

    FilterCategory initSuppliers(){
        FilterCategory supplier=new FilterCategory("supplier","Suppliers:");
        supplier.add(new FilterCategoryElement("Amazon"));
        supplier.add(new FilterCategoryElement("Lenovo"));
        supplier.add(new FilterCategoryElement("Samsung"));
        supplier.add(new FilterCategoryElement("Gigabyte"));
        supplier.add(new FilterCategoryElement("MSI"));
        supplier.add(new FilterCategoryElement("Asus"));
        return supplier;
    }

}

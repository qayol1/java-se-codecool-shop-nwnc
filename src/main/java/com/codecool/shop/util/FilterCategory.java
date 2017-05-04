package com.codecool.shop.util;

import java.util.ArrayList;
import java.util.List;


public class FilterCategory{
        String classname;
        String value;
        List<FilterCategoryElement> elements;

        FilterCategory(String classname,String value){
            this.classname=classname;
            this.value=value;
            this.elements=new ArrayList<>();
        }

        public String getClassName(){
            return this.classname;
        }

        public String getValue(){
            return this.value;
        };


        public void add(FilterCategoryElement element){
            this.elements.add(element);

        }

        public List<FilterCategoryElement> getElements (){
            return this.elements;
        }


    }

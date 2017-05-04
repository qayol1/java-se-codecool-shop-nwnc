package com.codecool.shop.util;

public class FilterCategoryElement{
    public String name;
    public boolean checked=false;

    public FilterCategoryElement(String name){
        this.name=name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }

}

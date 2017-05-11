package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        ArrayList<String> supplierNames = new ArrayList<>();
        for (Supplier existingSupplier:DATA)  {
            supplierNames.add(existingSupplier.getName());
        }
        if (!supplierNames.contains(supplier.getName())) {
            supplier.setId(DATA.size() + 1);
            DATA.add(supplier);
        }
    }

    @Override
    public Supplier find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
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
    public boolean empty() {
        DATA.clear();
        return true;
    }

    @Override
    public List<Supplier> getAll() {
        return DATA;
    }
}

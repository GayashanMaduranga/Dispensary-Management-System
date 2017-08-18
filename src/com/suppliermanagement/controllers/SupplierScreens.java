package com.suppliermanagement.controllers;

/**
 * Based on 'Myscreens' Created by gayashan on 8/14/2017.
 */
public enum SupplierScreens {

    PURCHASE_SCREEN("purchase", "/com/suppliermanagement/views/supView_Purchase.fxml"),
    SUPPLIER_SCREEN("suppliers", "/com/suppliermanagement/views/supView_Suppliers.fxml");

    String path;
    String id;

    SupplierScreens(String id, String path) {
        this.path = path;
        this.id = id;
    }

    ;

    public String getPath() {
        return path;
    }

    public String getId() {
        return id;
    }
}

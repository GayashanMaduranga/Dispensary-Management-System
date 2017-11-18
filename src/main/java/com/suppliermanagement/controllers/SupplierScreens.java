package com.suppliermanagement.controllers;

import com.common.BaseEnum;

/**
 * Based on 'Myscreens' Created by gayashan on 8/14/2017.
 */
public enum SupplierScreens implements BaseEnum {

    DASHBOARD_SCREEN("dashboard", "/com/suppliermanagement/suplyView_Dashboard.fxml"),
    SUPPLIER_SCREEN_VIEW("supplier_view", "/com/suppliermanagement/Suppliers_View/Supplier_view.fxml"),
    SUPPLIER_SCREEN_NEW("new_supplier","/com/suppliermanagement/Suppliers_View/Add_Supplier.fxml"),
    SUPPLIER_SCREEN_EDIT("edit_supplier","/com/suppliermanagement/Suppliers_View/Edit_Supplier.fxml"),
    STOCK_CONTROL_VIEW("stock_control_view","/com/suppliermanagement/Stock_Control_View/Stock_Control.fxml"),
    STOCK_CONTROL_NEW("stock_control_add","/com/suppliermanagement/Stock_Control_View/Stock_Control_New_Purchase.fxml"),
    STOCK_CONTROL_EDIT("stock_control_edit","/com/suppliermanagement/Stock_Control_View/Stock_Control_Edit_Purchase.fxml"),
    INVENTORY_VIEW("invent_view","/com/suppliermanagement/Inventory_View/Inventory_View.fxml"),
    INVENTORY_VIEW_NEW_PRODUCT("invent_view","/com/suppliermanagement/Inventory_View/Inventory_view_New_product.fxml"),
    INVENTORY_VIEW_NEW_EQUIPMENT("invent_view","/com/suppliermanagement/Inventory_View/Inventory_view_New_Equipment.fxml"),

    SUPPLIER_MAIN_VIEW("Main","/com/suppliermanagement/Supplier_MainScreen.fxml");

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

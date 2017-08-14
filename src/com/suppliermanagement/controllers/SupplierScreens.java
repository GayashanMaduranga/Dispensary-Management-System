package com.suppliermanagement.controllers;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum SupplierScreens {

    PURCHASE_SCREEN("purchase", "/com/suppliermanagement/views/supView_Purchase.fxml"),
    SUPPLIER_SCREEN("suppliers", "/com/suppliermanagement/views/supView_Suppliers.fxml");

//    ADDEMPLOYEE_SCREEN("addEmployee", "/com/employeemanagement/views/AddEmployee.fxml"),
//    ATTENDENCE_SCREEN("attendence", "/com/employeemanagement/views/Attendence.fxml"),
//    UPDATEADDEMPLOYEE_SCREEN("updateEmployee", "/com/employeemanagement/views/UpdateEmployee.fxml"),
//    PAYROLL_SCREEN("payroll", "/com/employeemanagement/views/Payroll.fxml");


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

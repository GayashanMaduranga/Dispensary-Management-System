package com.employeemanagement.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MyScreens implements BaseEnum{

    DASHBOARD_SCREEN("dashboard", "/com/employeemanagement/views/DashBoard.fxml"),
    ADDEMPLOYEE_SCREEN("addEmployee", "/com/employeemanagement/views/AddEmployee.fxml"),
    ATTENDENCE_SCREEN("attendence", "/com/employeemanagement/views/Attendence.fxml"),
    UPDATEADDEMPLOYEE_SCREEN("updateEmployee", "/com/employeemanagement/views/UpdateEmployee.fxml"),
    PAYROLL_SCREEN("payroll", "/com/employeemanagement/views/Payroll.fxml"),
    LOAN_SCREEN("loan", "/com/employeemanagement/views/Loan.fxml");


    String path;
    String id;

    MyScreens(String id, String path) {
        this.path = path;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public String getId() {
        return id;
    }
}

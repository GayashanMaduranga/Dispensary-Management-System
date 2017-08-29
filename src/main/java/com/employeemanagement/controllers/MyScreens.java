package com.employeemanagement.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MyScreens implements BaseEnum{

    DASHBOARD_SCREEN("dashboard", "/employeemanagement/DashBoard.fxml"),
    ADDEMPLOYEE_SCREEN("addEmployee", "/employeemanagement/AddEmployee.fxml"),
    ATTENDENCE_SCREEN("attendence", "/employeemanagement/Attendence.fxml"),
    UPDATEADDEMPLOYEE_SCREEN("updateEmployee", "/employeemanagement/UpdateEmployee.fxml"),
    PAYROLL_SCREEN("payroll", "/employeemanagement/Payroll.fxml"),
    LOAN_SCREEN("loan", "/employeemanagement/Loan.fxml");
///com/employeemanagement/views/Loan.fxml

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

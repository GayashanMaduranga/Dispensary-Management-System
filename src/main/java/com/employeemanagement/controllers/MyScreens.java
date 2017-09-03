package com.employeemanagement.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MyScreens implements BaseEnum{

    DASHBOARD_SCREEN("dashboard", "/com/employeemanagement/DashBoard.fxml"),
    ADDEMPLOYEE_SCREEN("addEmployee", "/com/employeemanagement/AddEmployee.fxml"),
    ATTENDENCE_SCREEN("attendence", "/com/employeemanagement/Attendence.fxml"),
    UPDATEADDEMPLOYEE_SCREEN("updateEmployee", "/com/employeemanagement/UpdateEmployee.fxml"),
    PAYROLL_SCREEN("payroll", "/com/employeemanagement/Payroll.fxml"),
    LOAN_SCREEN("loan", "/com/employeemanagement/Loan.fxml");
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

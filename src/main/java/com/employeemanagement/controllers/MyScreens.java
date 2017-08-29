package com.employeemanagement.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MyScreens implements BaseEnum{

    DASHBOARD_SCREEN("dashboard", "/views/DashBoard.fxml"),
    ADDEMPLOYEE_SCREEN("addEmployee", "/views/AddEmployee.fxml"),
    ATTENDENCE_SCREEN("attendence", "/views/Attendence.fxml"),
    UPDATEADDEMPLOYEE_SCREEN("updateEmployee", "/views/UpdateEmployee.fxml"),
    PAYROLL_SCREEN("payroll", "/views/Payroll.fxml"),
    LOAN_SCREEN("loan", "/views/Loan.fxml");
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

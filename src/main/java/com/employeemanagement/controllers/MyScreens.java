package com.employeemanagement.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MyScreens implements BaseEnum {

    MAIN_SCREEN("main", "/com/employeemanagement/MainScreen.fxml"),
    DASHBOARD_SCREEN("dashboard", "/com/employeemanagement/DashBoard.fxml"),
    ADDEMPLOYEE_SCREEN("addEmployee", "/com/employeemanagement/AddEmployee.fxml"),
    ATTENDENCE_SCREEN("attendence", "/com/employeemanagement/Attendance.fxml"),
    SEARCH_EMPLOYEE_SCREEN("searchEmployee", "/com/employeemanagement/SearchEmployee.fxml"),
    PAYROLL_SCREEN("payroll", "/com/employeemanagement/Payroll.fxml"),
    VIEW_EMPLOYEE_SCREEN("viewEmployee", "/com/employeemanagement/ViewEmployeeDetails.fxml"),
    ADD_DOCTOR_SCREEN("searchEmployee", "/com/employeemanagement/AddDoctor.fxml"),
    SEARCH_DOCTOR_SCREEN("searchEmployee", "/com/employeemanagement/SearchDoctor.fxml"),
    VIEW_DOCTOR_SCREEN("payroll", "/com/employeemanagement/ViewDoctorDetails.fxml"),

    LOADING_SCREEN("loan", "/com/employeemanagement/LoadingScreen.fxml");
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

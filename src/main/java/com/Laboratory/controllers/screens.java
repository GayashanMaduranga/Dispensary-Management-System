package com.Laboratory.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum screens implements BaseEnum{

//    DASHBOARD_SCREEN("dashboardBT", "/com/employeemanagement/views/DashBoard.fxml"),
//    ADDEMPLOYEE_SCREEN("addEmployee", "/com/employeemanagement/views/AddEmployee.fxml"),
//    ATTENDENCE_SCREEN("attendence", "/com/employeemanagement/views/Attendence.fxml"),
//    UPDATEADDEMPLOYEE_SCREEN("updateEmployee", "/com/employeemanagement/views/UpdateEmployee.fxml"),
//    PAYROLL_SCREEN("payroll", "/com/employeemanagement/views/Payroll.fxml");


    DASHBOARD_SCREEN("dashBoard", "/com/Laboratory/dashboard.fxml"),
    ORDERTEST_SCREEN("orderTest", "/com/Laboratory/orderTest.fxml"),
    ENTERRESULTS_SCREEN("enterResults", "/com/Laboratory/enterResults.fxml"),
    VIEWDB_SCREEN("viewDB", "/com/Laboratory/viewDB.fxml"),
    EXTRA_SCREEN("extra", "/com/Laboratory");

    String path;
    String id;

    screens(String id, String path) {
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

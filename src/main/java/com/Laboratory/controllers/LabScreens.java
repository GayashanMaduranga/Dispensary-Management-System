package com.Laboratory.controllers;

import com.common.BaseEnum;

/**
 * Created by AmilaWC on 8/20/2017.
 */
public enum LabScreens implements BaseEnum{


    HOME_SCREEN("home", "/com/Laboratory/home.fxml"),

    DASHBOARD_SCREEN("dashBoard", "/com/Laboratory/dashBoard.fxml"),
    ORDERTEST_SCREEN("orderTest", "/com/Laboratory/orderTest.fxml"),
    ENTERRESULTS_SCREEN("enterResults", "/com/Laboratory/enterResults.fxml"),
    ADDTEST_SCREEN("addTest","/com/Laboratory/addTest.fxml"),
    VIEWDB_SCREEN("viewDB", "/com/Laboratory/viewDB.fxml"),
    EXTRA_SCREEN("extra", "/com/Laboratory/extra.fxml"),
    REFER_SCREEN("refer","/com/Laboratory/AddReferenceValue.fxml"),



    SELECT_TEST_SCREEN("selectTest", "/com/Laboratory/selectTest.fxml");

    String path;
    String id;

    LabScreens(String id, String path) {
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

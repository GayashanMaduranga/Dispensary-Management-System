package com.Laboratory.controllers;

import com.common.BaseEnum;

/**
 * Created by AmilaWC on 8/20/2017.
 */
public enum screens implements BaseEnum{



    DASHBOARD_SCREEN("dashBoard", "/com/Laboratory/views/dashBoard.fxml"),
    ORDERTEST_SCREEN("orderTest", "/com/Laboratory/views/orderTest.fxml"),
    ENTERRESULTS_SCREEN("enterResults", "/com/Laboratory/views/enterResults.fxml"),
    VIEWDB_SCREEN("viewDB", "/com/Laboratory/views/viewDB.fxml"),
    EXTRA_SCREEN("extra", "/com/Laboratory/views/extra.fxml");



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

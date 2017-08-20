package com.financemanagement.controllers;

import com.common.BaseEnum;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum FinanceScreens implements BaseEnum{

    DASHBOARD_SCREEN("...add a name for the screen...", "....add path to the screen....");

    String path;
    String id;

    FinanceScreens(String id, String path) {
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

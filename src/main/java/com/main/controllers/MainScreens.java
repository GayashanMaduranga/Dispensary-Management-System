package com.main.controllers;

import com.common.BaseEnum;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MainScreens implements BaseEnum{

    HOME_SCREEN("home", "/com/main/MainScreen.fxml"),
    NO_ACCESS_SCREEN("home", "/com/main/NoAccess.fxml"),
    FIRST_ADMIN_SCREEN("home", "/com/main/FirstAdmin.fxml"),
    ADMIN_PORTAL_SCREEN("home", "/com/main/AdminWindow.fxml"),
    LOGIN_SCREEN("login", "/com/main/Login.fxml");


    String path;
    String id;

    MainScreens(String id, String path) {
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

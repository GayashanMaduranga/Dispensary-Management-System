package com.main.controllers;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum MainScreens {

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

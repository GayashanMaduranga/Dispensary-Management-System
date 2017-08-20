package com.appointmentscheduling.controllers;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum AppointmentScreens {

    VIEW_APPOINTMENTS_SCREEN("view", "/com/appointmentscheduling/views/AppointmentScheduling.fxml");


    String path;
    String id;

    AppointmentScreens(String id, String path) {
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

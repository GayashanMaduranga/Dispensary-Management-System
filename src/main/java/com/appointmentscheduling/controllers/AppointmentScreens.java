package com.appointmentscheduling.controllers;

import com.common.BaseEnum;

import javax.swing.text.View;

/**
 * Created by gayashan on 8/14/2017.
 */
public enum AppointmentScreens implements BaseEnum{

    VIEW_APPOINTMENTS_SCREEN("view", "/com/appointmentscheduling/AppointmentScheduling.fxml"),
    VIEW_ADD_APPOINTMENTS("AddAppointment", "/com/appointmentscheduling/AddAppointment.fxml");



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

package com.patientmanagement.controllers;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum PatientScreens {

    DASHBOARD_SCREEN("ViewPatients", "/com/patientmanagement/views/DoctorsAssistant.fxml");

    String path;
    String id;

    PatientScreens(String id, String path) {
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

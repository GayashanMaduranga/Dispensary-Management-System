package com.patientmanagement.controllers;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum PatientScreens {

    VIEW_PATIENTS_SCREEN("ViewPatients", "/com/patientmanagement/views/ViewPatients.fxml"),
    REGISTER_PATIENTS_SCREEN("RegisterPatients", "/com/patientmanagement/views/PatientRegistration.fxml");


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

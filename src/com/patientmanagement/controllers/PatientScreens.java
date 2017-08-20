package com.patientmanagement.controllers;

import com.common.BaseEnum;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum PatientScreens implements BaseEnum{

    DASHBOARD_SCREEN("ViewPatients", "/com/patientmanagement/views/DoctorsAssistant.fxml"),
    PRESCRIPTION_SCREEN("MakePrescription", "/com/patientmanagement/views/MakePrescription.fxml"),
    BILL_SCREEN("MakePrescription", "/com/patientmanagement/views/MakeBill.fxml"),
    REGISTER_PATIENT_SCREEN("RegisterPatients", "/com/patientmanagement/views/RegisterPatient.fxml");

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

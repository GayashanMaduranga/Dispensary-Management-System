package com.patientmanagement.controllers;

import com.common.BaseEnum;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum PatientScreens implements BaseEnum{

    MAIN_DASHBOARD_SCREEN("MainScreen", "/com/patientmanagement/patientDashboard.fxml"),
    DASHBOARD_SCREEN("ViewPatients", "/com/patientmanagement/PatientWindow.fxml"),
    PATIENT_SUMMARY_SCREEN("PatientSummary", "/com/patientmanagement/PatientSummary.fxml"),
    MEDICATION_SCREEN("MakePrescription", "/com/patientmanagement/Medication.fxml"),
    BILL_SCREEN("MakePrescription", "/com/patientmanagement/Encounter.fxml"),
    REGISTER_PATIENT_SCREEN("RegisterPatients", "/com/patientmanagement/RegisterPatient.fxml");

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

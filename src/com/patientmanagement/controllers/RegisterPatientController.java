package com.patientmanagement.controllers;


import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class RegisterPatientController implements Initializable,ControlledScreen {

    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private JFXButton sidebarRegisterBtn;

    @FXML
    private JFXButton sidebarPrescriptionBtn;

    @FXML
    private JFXButton sidebarBillBtn;

    @FXML
    private JFXButton titlebtn;

    @FXML
    void changeScene(Event event){

        switch(((JFXButton)event.getSource()).getId()){

            case "sidebarRegisterBtn":
                ScreenController.changeScreen(controller, PatientScreens.REGISTER_PATIENT_SCREEN, PatientScreens.REGISTER_PATIENT_SCREEN);
                break;
            case "titlebtn":
                ScreenController.changeScreen(controller, PatientScreens.REGISTER_PATIENT_SCREEN, PatientScreens.DASHBOARD_SCREEN);
                break;
            case "sidebarPrescriptionBtn":
                ScreenController.changeScreen(controller, PatientScreens.REGISTER_PATIENT_SCREEN, PatientScreens.PRESCRIPTION_SCREEN);
                break;
            case "sidebarBillBtn":
                ScreenController.changeScreen(controller, PatientScreens.REGISTER_PATIENT_SCREEN, PatientScreens.BILL_SCREEN);
                break;
        }
    }
}

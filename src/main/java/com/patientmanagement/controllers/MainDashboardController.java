package com.patientmanagement.controllers;


import com.Laboratory.controllers.LabScreens;
import com.PharmacyMgt.Controllers.PharmacyScreens;
import com.appointmentscheduling.controllers.AppointmentScreens;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import com.financemanagement.controllers.FinanceScreens;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreens;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainDashboardController implements ControlledScreen, Initializable {

    ScreenController controller;
    @FXML
    private Label userLbl;

    @FXML
    private JFXButton doctorPortalBtn;

    @FXML
    private JFXButton assistantPortalBtn;

    @FXML
    private JFXButton apoointmentBtn;

    @FXML
    private JFXButton backToHomeBtn;

    @FXML
    void changeScene(ActionEvent event) {

        switch (((Button)event.getSource()).getId()){

            case "doctorPortalBtn":
                ScreenController.changeScreen(controller, PatientScreens.MAIN_DASHBOARD_SCREEN, PatientScreens.PATIENT_SUMMARY_SCREEN);
                break;

            case "assistantPortalBtn":
                ScreenController.changeScreen(controller, PatientScreens.MAIN_DASHBOARD_SCREEN, PatientScreens.DASHBOARD_SCREEN);
                break;

            case "apoointmentBtn":
                ScreenController.changeScreen(controller, PatientScreens.MAIN_DASHBOARD_SCREEN, AppointmentScreens.VIEW_APPOINTMENTS_SCREEN);
                break;

            case "backToHomeBtn":
                ScreenController.changeScreen(controller, PatientScreens.MAIN_DASHBOARD_SCREEN, MainScreens.HOME_SCREEN);
                break;

        }
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

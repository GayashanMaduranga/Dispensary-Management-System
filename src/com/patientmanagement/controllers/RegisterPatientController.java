package com.patientmanagement.controllers;


import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.models.LoginModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

        userLbl.setText(LoginModel.user);
    }

    @FXML
    private Label userLbl;

    @FXML
    private JFXButton sidebarRegisterBtn;

    @FXML
    private JFXButton sidebarPrescriptionBtn;

    @FXML
    private JFXButton sidebarBillBtn;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton logoutBtn;

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

    @FXML
    void logout(){

        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage)logoutBtn.getScene().getWindow();
            s.close();
        }
    }
}

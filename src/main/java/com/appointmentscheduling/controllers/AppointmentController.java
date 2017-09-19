package com.appointmentscheduling.controllers;

import com.EntityClasses.Appointment;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreens;
import com.patientmanagement.controllers.PatientScreens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kalan on 8/12/2017.
 */
public class AppointmentController implements Initializable, ControlledScreen {

    private ScreenController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {

        this.controller = screenParent;
    }


    @FXML
    private JFXButton logoutBtn;
    @FXML
    void changeScene(ActionEvent event) {

        switch (((Button)event.getSource()).getId()){

            case "logoutBtn":
                ScreenController.changeScreen(controller, AppointmentScreens.VIEW_APPOINTMENTS_SCREEN, PatientScreens.MAIN_DASHBOARD_SCREEN);
                break;

            case "assistantPortalBtn":
//                ScreenController.changeScreen(controller, PatientScreens.MAIN_DASHBOARD_SCREEN, PatientScreens.DASHBOARD_SCREEN);
                break;
        }
    }

    public void setadd(){
        //ScreenController.changeScreen(controller, AppointmentScreens.VIEW_ADD_APPOINTMENTS, AppointmentScreens.VIEW_ADD_APPOINTMENTS};

    }
}


package com.suppliermanagement.controllers;

import com.appointmentscheduling.controllers.AppointmentScreens;
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
 * Created by Naveen Luke Fernando on 2017-08-21.
 */
public class suplyView_Dashboard_CTRL implements ControlledScreen, Initializable {

    ScreenController controller;


    @FXML
    private JFXButton invent_button;

    @FXML
    private JFXButton stock_button;

    @FXML
    private JFXButton supplier_button;

    @FXML
    private JFXButton backToHomeBtn;

    @FXML
    void changeScene(ActionEvent event) {

        switch (((Button)event.getSource()).getId()){

            case "invent_button":
                ScreenController.changeScreen(controller, SupplierScreens.DASHBOARD_SCREEN, SupplierScreens.INVENTORY_VIEW);
                break;

            case "stock_button":
                ScreenController.changeScreen(controller, SupplierScreens.DASHBOARD_SCREEN, SupplierScreens.STOCK_CONTROL_VIEW);
                break;

            case "supplier_button":
                ScreenController.changeScreen(controller, SupplierScreens.DASHBOARD_SCREEN,SupplierScreens.SUPPLIER_MAIN_VIEW);
                break;

            case "backToHomeBtn":
                ScreenController.changeScreen(controller, SupplierScreens.DASHBOARD_SCREEN, MainScreens.HOME_SCREEN);
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

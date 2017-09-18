package com.financemanagement.controllers;


import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.labinventory.controlers.LabInventoryScreens;
import com.main.controllers.MainScreens;
import com.patientmanagement.controllers.PatientScreens;
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
    private JFXButton financeBtn;

    @FXML
    private JFXButton equipmentBtn;

    @FXML
    private JFXButton backHomeBtn;

    @FXML
    private JFXButton userManagementBtn;

    @FXML
    void openFinanceScreen(){

        ScreenController.changeScreen(controller, FinanceScreens.MAIN_DASHBOARD_SCREEN, FinanceScreens.FINANCE_MAIN_SCREEN);

    }
    @FXML
    void changeScene(ActionEvent event) {

        switch (((Button)event.getSource()).getId()){

            case "financeBtn":
                ScreenController.changeScreen(controller, FinanceScreens.MAIN_DASHBOARD_SCREEN, FinanceScreens.FINANCE_MAIN_SCREEN);
                break;

            case "equipmentBtn":
                ScreenController.changeScreen(controller, FinanceScreens.MAIN_DASHBOARD_SCREEN, LabInventoryScreens.LAB_EQUIPMENT_SCREEN);
                break;

//            case "userManagementBtn":
//                ScreenController.changeScreen(controller, PatientScreens.MAIN_DASHBOARD_SCREEN, PatientScreens.DASHBOARD_SCREEN);
//                break;

            case "backHomeBtn":
                ScreenController.changeScreen(controller, FinanceScreens.MAIN_DASHBOARD_SCREEN, MainScreens.HOME_SCREEN);
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

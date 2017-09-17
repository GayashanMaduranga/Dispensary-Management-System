package com.PharmacyMgt.Controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dilshanka on 13/08/2017.
 */
public class PharmacyController implements Initializable,ControlledScreen{


    ScreenController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }


    @FXML
    private GridPane topPane;

    @FXML
    private Label userLbl;

    @FXML
    private JFXButton msgBtn;

    @FXML
    private JFXButton billingBtn;

    @FXML
    private JFXButton stockBtn;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private JFXButton logBtn;

    @FXML
    void changeScene(ActionEvent event) {


        switch (((JFXButton) event.getSource()).getId()){
            case "billingBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.DASHBOARD_SCREEN, PharmacyScreens.PHARMACY_BILLING_SCREEN);
                break;


            case "stockBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.DASHBOARD_SCREEN, PharmacyScreens.PHARMACY_STOCK_SCREEN);
                break;


            case "msgBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.DASHBOARD_SCREEN, PharmacyScreens.PHARMACY_MESSAGE_SCREEN);
                break;

//            case "logBtn":
//
//                ScreenController.changeScreen(controller, PharmacyScreens.DASHBOARD_SCREEN, PharmacyScreens.PHARMACY_LOG_SCREEN);
//                break;
            case "paymentBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.DASHBOARD_SCREEN, PharmacyScreens.PHARMACY_PAYMENT_SCREEN);
                break;






        }


    }
}

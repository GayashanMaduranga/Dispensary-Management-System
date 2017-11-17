package com.PharmacyMgt.Controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Dilshanka on 10/09/2017.
 */
public class MessageController implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private GridPane topPane;

    @FXML
    private Label userLbl;

    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton billingBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private JFXButton stockBtn;

    @FXML
    private JFXButton logBtn;

    @FXML
    private JFXButton msgPortalBtn;

    @FXML
    void changeScene(ActionEvent event) {

        switch (((JFXButton) event.getSource()).getId()) {
            case "titlebtn":

                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_MESSAGE_SCREEN, PharmacyScreens.DASHBOARD_SCREEN);
                break;

            case "billingBtn ":
                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_MESSAGE_SCREEN,PharmacyScreens.PHARMACY_BILLING_SCREEN);
                break;
            case "paymentBtn":
                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_MESSAGE_SCREEN, PharmacyScreens.PHARMACY_PAYMENT_SCREEN);
                break;


//            case "logNavBtn":
//                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_MESSAGE_SCREEN, PharmacyScreens.P);
//                break;
//





        }
    }



    @FXML
    void logout(ActionEvent event) {

    }


    @Override
    public void setScreenParent(ScreenController screenParent) {

            controller = screenParent;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

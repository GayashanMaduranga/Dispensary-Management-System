package com.PharmacyMgt.Controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dilshanka on 10/09/2017.
 */
public class InvoiceCheckController implements Initializable,ControlledScreen {


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
    private JFXButton logBtn;

    @FXML
    private JFXButton stockBtn;

    @FXML
    private JFXButton msgBtn;

    @FXML
    private JFXButton backBtn;


    @FXML
    void changeScene(ActionEvent event) {

        switch (((JFXButton) event.getSource()).getId()) {
            case "titlebtn":

                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_PAYMENT_SCREEN, PharmacyScreens.DASHBOARD_SCREEN);
                break;

            case "billingBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_PAYMENT_SCREEN, PharmacyScreens.PHARMACY_BILLING_SCREEN);
                break;

            case "stockBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_PAYMENT_SCREEN, PharmacyScreens.PHARMACY_STOCK_SCREEN);
                break;

            case "backBtn":

                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_PAYMENT_SCREEN, PharmacyScreens.DASHBOARD_SCREEN);
                break;





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

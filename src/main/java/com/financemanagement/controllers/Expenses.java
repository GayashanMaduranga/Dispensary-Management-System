package com.financemanagement.controllers;

import com.EntityClasses.OtherExpenses;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.beans.property.*;
import com.patientmanagement.controllers.PatientScreens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static jfxtras.labs.repeatagenda.scene.control.repeatagenda.Settings.resources;

/**
 * Created by Kavindu on 9/12/2017.
 */
public class Expenses implements Initializable,ControlledScreen {

    ScreenController controller;


    @FXML
    private JFXButton resetbtn;

    @FXML
    private Label userLbl;

    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton homeBtn;

    @FXML
    void showHome(){
        ScreenController.changeScreen(controller, FinanceScreens.FINANCE_MAIN_SCREEN, FinanceScreens.MAIN_DASHBOARD_SCREEN);
    }

    @FXML
    void changeScene(ActionEvent event) {

        switch (((JFXButton) event.getSource()).getId()){
            case "FinanceMainBtn":
                ScreenController.changeScreen(controller, FinanceScreens.EXPENSES_SCREEN, FinanceScreens.FINANCE_MAIN_SCREEN);
                break;
            //case "":
              //  break;

        }


    }

    @FXML
    void logout(ActionEvent event) {

        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) homeBtn.getScene().getWindow();
            s.close();
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

package com.employeemanagement.controllers;

import com.common.ConfirmDialog;
import com.common.ScreenController;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton addEmployeeBtn;

    @FXML
    private JFXButton updateEmployeeBtn;

    @FXML
    private JFXButton attendenceBtn;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton payrollBtn;

    @FXML
    private JFXButton reportsBtn;

    @FXML
    private GridPane topPane;

    @FXML
    private HBox must;

    @FXML
    private StackPane content;


    private JFXButton selectedBtn;
//    private JFXButton btn2;

    @FXML
    void changeScene(MouseEvent event) {

        selectedBtn.setDisable(false);
        leftPane.requestFocus();
        selectedBtn = (JFXButton) event.getSource();
        selectedBtn.setDisable(true);

        switch (selectedBtn.getId()) {
            case "dashBoardBtn":
                ScreenController.changeScreen(MyScreens.DASHBOARD_SCREEN,content);
                break;
            case "addEmployeeBtn":
                ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN,content);
                break;

            case "updateEmployeeBtn":
                ScreenController.changeScreen(MyScreens.UPDATEADDEMPLOYEE_SCREEN,content);

                break;
            case "attendenceBtn":
                ScreenController.changeScreen(MyScreens.ATTENDENCE_SCREEN,content);

                break;
            case "payrollBtn":
                ScreenController.changeScreen(MyScreens.PAYROLL_SCREEN,content);

                break;
            case "reportsBtn":
//                ScreenController.changeScreen(MyScreens.R,content);
                break;
        }


    }

    @FXML
    void logout(ActionEvent event) {
        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) logoutBtn.getScene().getWindow();
            s.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedBtn = dashBoardBtn;
        selectedBtn.setDisable(true);

        ScreenController.changeScreen(MyScreens.DASHBOARD_SCREEN,content);

    }


}

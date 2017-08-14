package com.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class PayrollController implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private VBox aside;
    @FXML
    private JFXButton dashBoardBtn;

    @Override
    public void setScreenParent(ScreenController screenParent) {
            controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void changeScene(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){
            case "dashBoardBtn":
                ScreenController.changeScreen(controller, MyScreens.PAYROLL_SCREEN, MyScreens.DASHBOARD_SCREEN);
                break;
            case "addEmployeeBtn":
                ScreenController.changeScreen(controller, MyScreens.PAYROLL_SCREEN, MyScreens.ADDEMPLOYEE_SCREEN);
                break;
            case "updateEmployeeBtn":
                ScreenController.changeScreen(controller, MyScreens.PAYROLL_SCREEN, MyScreens.UPDATEADDEMPLOYEE_SCREEN);
                break;
            case "attendenceBtn":
                ScreenController.changeScreen(controller, MyScreens.PAYROLL_SCREEN, MyScreens.ATTENDENCE_SCREEN);
                break;
            case "payrollBtn":

                break;
            case "reportsBtn":
                //ScreenController.changeScreen(controller, MyScreens.ADDEMPLOYEE_SCREEN, MyScreens.R);
                System.out.println("null");
                break;
        }
    }
}

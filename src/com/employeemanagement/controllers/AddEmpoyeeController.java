package com.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class AddEmpoyeeController implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private VBox aside;
    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton addEmployeeBtn;

    @FXML
    private JFXButton updateEmployeeBtn;

    @FXML
    private JFXButton attendenceBtn;

    @FXML
    private JFXButton payrollBtn;

    @FXML
    private JFXButton reportsBtn;


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
                ScreenController.changeScreen(controller, MyScreens.ADDEMPLOYEE_SCREEN, MyScreens.DASHBOARD_SCREEN);
                break;
            case "addEmployeeBtn":
                break;
            case "updateEmployeeBtn":
                ScreenController.changeScreen(controller, MyScreens.ADDEMPLOYEE_SCREEN, MyScreens.UPDATEADDEMPLOYEE_SCREEN);
                break;
            case "attendenceBtn":
                ScreenController.changeScreen(controller, MyScreens.ADDEMPLOYEE_SCREEN, MyScreens.ATTENDENCE_SCREEN);
                break;
            case "payrollBtn":
                ScreenController.changeScreen(controller, MyScreens.ADDEMPLOYEE_SCREEN, MyScreens.PAYROLL_SCREEN);
                break;
            case "reportsBtn":
                //ScreenController.changeScreen(controller, SupplierScreens.ADDEMPLOYEE_SCREEN, SupplierScreens.R);
                System.out.println("null");
                break;
        }


    }

    @FXML
    void logout(ActionEvent event) {

    }


}

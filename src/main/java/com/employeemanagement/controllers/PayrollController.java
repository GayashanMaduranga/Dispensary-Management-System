package com.employeemanagement.controllers;

import com.common.ConfirmDialog;
import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private JFXButton logoutBtn;

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
                //ScreenController.changeScreen(controller, SupplierScreens.ADDEMPLOYEE_SCREEN, SupplierScreens.R);
                System.out.println("null");
                break;
        }
    }


    @FXML
    void changeSubScene(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){
            case "advancedPaymentBtn":
                // ScreenController.changeScreen(controller, MyScreens.LOAN_SCREEN, MyScreens.DASHBOARD_SCREEN);
                break;
            case "applyLoanBtn":

                ScreenController.changeScreen(controller, MyScreens.PAYROLL_SCREEN, MyScreens.LOAN_SCREEN);
                break;
            case "calculatePayrollBtn":
                //ScreenController.changeScreen(controller, MyScreens.LOAN_SCREEN, MyScreens.UPDATEADDEMPLOYEE_SCREEN);
                break;

        }
    }
    @FXML
    void logout(ActionEvent event) {
        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage)logoutBtn.getScene().getWindow();
            s.close();
        }
    }
}

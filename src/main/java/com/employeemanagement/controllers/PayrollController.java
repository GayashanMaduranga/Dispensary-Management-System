package com.employeemanagement.controllers;

import com.common.ConfirmDialog;
import com.common.SessionListener;
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
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class PayrollController implements Initializable,SessionListener {





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void changeSubScene(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){
            case "advancedPaymentBtn":
                // ScreenController.changeScreen(controller, MyScreens.LOAN_SCREEN, MyScreens.DASHBOARD_SCREEN);
                break;
            case "applyLoanBtn":
//                ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN,content);
//                ScreenController.changeScreen(controller, MyScreens.PAYROLL_SCREEN, MyScreens.LOAN_SCREEN);
                break;
            case "calculatePayrollBtn":
                //ScreenController.changeScreen(controller, MyScreens.LOAN_SCREEN, MyScreens.UPDATEADDEMPLOYEE_SCREEN);
                break;

        }
    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {

    }
}

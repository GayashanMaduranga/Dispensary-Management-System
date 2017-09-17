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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class DashBoardController implements Initializable,SessionListener {



    @FXML
    private GridPane grid;

    @FXML
    private JFXButton addEmployeeBtn;

    @FXML
    private JFXButton employeeDetailsBtn;

    @FXML
    private JFXButton addDoctorBtn;

    @FXML
    private JFXButton doctorDetailsBtn;

    private MainScreenController mainScreenController;

    @FXML
    void changeScreen(ActionEvent event) {

        JFXButton selectedBtn = (JFXButton) event.getSource();
        switch (selectedBtn.getId()) {
            case "addEmployeeBtn":
                ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN,mainScreenController.getContent(),mainScreenController);

                break;
            case "employeeDetailsBtn":
                ScreenController.changeScreen(MyScreens.SEARCH_EMPLOYEE_SCREEN,mainScreenController.getContent(),mainScreenController);

                break;

            case "addDoctorBtn":
                ScreenController.changeScreen(MyScreens.ADD_DOCTOR_SCREEN,mainScreenController.getContent(),mainScreenController);


                break;
            case "doctorDetailsBtn":
                ScreenController.changeScreen(MyScreens.VIEW_EMPLOYEE_SCREEN,mainScreenController.getContent(),mainScreenController);


                break;

        }

        if(mainScreenController==null)
        System.out.println("ON");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {
            mainScreenController=(MainScreenController)controller;
    }

//    @FXML
//    void changeScene(MouseEvent event) {
//
//
//
//    }


}

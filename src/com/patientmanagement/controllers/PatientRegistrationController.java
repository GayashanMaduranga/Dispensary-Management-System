package com.patientmanagement.controllers;


import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.models.LoginModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class PatientRegistrationController implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private Label lblUser;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUser.setText(LoginModel.user);
    }
}
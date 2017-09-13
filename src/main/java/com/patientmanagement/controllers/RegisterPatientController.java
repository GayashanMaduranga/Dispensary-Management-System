package com.patientmanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Damma on 8/26/2017.
 */
public class RegisterPatientController {

    @FXML
    private GridPane patientCard;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancelBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void register(){
        Main.dialogCanceled = false;
        Stage s = (Stage)registerBtn.getScene().getWindow();
        s.close();
    }
}

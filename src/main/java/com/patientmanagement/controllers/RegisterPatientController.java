package com.patientmanagement.controllers;

import com.EntityClasses.Patient;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by Damma on 8/26/2017.
 */
public class RegisterPatientController implements Initializable,ControlledScreen {

    ScreenController controller;

    static Patient patient;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtOccupation;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private Button resetBtn;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private Button registerBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancelBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void register(){


        patient.setDOB(Date.valueOf(txtDOB.getValue()));
        patient.setPname(txtName.getText().toLowerCase());
        patient.setGender(((RadioButton)genderGroup.getSelectedToggle()).getText());
        patient.setNIC(txtNIC.getText());
        patient.setContactNumber(txtPhone.getText());
        patient.setEmail(txtEmail.getText().toLowerCase());
        patient.setOccupation(txtOccupation.getText());

        Main.dialogCanceled = false;
        Stage s = (Stage)registerBtn.getScene().getWindow();
        s.close();
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        patient =  new Patient();
    }
}

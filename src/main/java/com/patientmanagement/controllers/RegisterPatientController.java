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

    private ScreenController controller;

    static Patient patient;

    @FXML
    private Label txtMsg;
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
    void reset(){

        txtName.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtNIC.clear();
        txtDOB.setValue(null);
        txtOccupation.clear();

    }

    @FXML
    void register(){

        if (fieldsAreComplete()) {
            if (isValidNIC()) {
                if (isValidPhoneNumber()) {
                    if (isValidEmail()) {
                        if (isValidOccupation()) {
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
                    }
                }
            }
        }
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        patient =  new Patient();

    }

    private boolean fieldsAreComplete(){

        if(txtName.getText().isEmpty() | txtPhone.getText().isEmpty() |
                txtEmail.getText().isEmpty() | txtNIC.getText().isEmpty() |
                txtOccupation.getText().isEmpty()){

            txtMsg.setText("*Complete all fields before registering");


            return false;
        }

        return true;
    }

    private boolean isValidPhoneNumber(){

        boolean flag = false;

        String regex = "^[0-9]{10}$";

        if(txtPhone.getText().matches(regex)){

            flag = true;
        }else{

            txtMsg.setText("*Please enter a 10 digit phone number");
        }
        return flag;
    }
    private boolean isValidNIC(){

        boolean flag = false;

        String regex = "^[0-9]{9}[a-zA-Z]$";

        if(txtNIC.getText().matches(regex)){

            flag = true;
        }else{

            txtMsg.setText("*Please enter a valid NIC number");
        }
        return flag;
    }

    private boolean isValidEmail(){

        boolean flag = false;

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(txtEmail.getText());

        if(m.matches()){

            flag = true;

        }else{

            txtMsg.setText("*Please enter a valid email address");
        }
        return flag;
    }

    private boolean isValidOccupation(){

        boolean flag = false;

        String regex = "^[a-zA-Z]+$";

        if(txtOccupation.getText().matches(regex)){

            flag = true;
        }else{

            txtMsg.setText("*There cannot be any numbers or special characters in the occupation field");
        }
        return flag;
    }



}

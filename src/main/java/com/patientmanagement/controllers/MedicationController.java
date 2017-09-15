package com.patientmanagement.controllers;


import com.EntityClasses.Medication;
import com.EntityClasses.Patient;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.models.LoginModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class MedicationController implements Initializable,ControlledScreen {

    ScreenController controller;

    static Medication medication;


    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        medication = new Medication();

        dosageChoiceBox.getItems().addAll("tablets", "ml", "mg");
        frequencyChoiceBox.getItems().addAll("once daily", "twice daily", "thrice daily");
        ArrayList<String> values = new ArrayList<>();
        values.add("roomba");
        values.add("zoomba");
        values.add("koromba");
        values.add("cocoomba");

        TextFields.bindAutoCompletion(txtMedication, values);
    }

    @FXML
    private Button addMedBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField txtMedication;

    @FXML
    private Spinner<Integer> dosageSpinner;

    @FXML
    private ChoiceBox<String> dosageChoiceBox;

    @FXML
    private ChoiceBox<String> frequencyChoiceBox;


    @FXML
    void cancel(){

        Main.dialogCanceled = true;
        Stage s = (Stage)cancelBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void addMedication(){

        int dosage = dosageSpinner.getValue();
        String dosageType = dosageChoiceBox.getValue();
        String frequency = frequencyChoiceBox.getValue();
        String name = txtMedication.getText();

        medication.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        medication.setDosage(dosage);
        medication.setDosageType(dosageType);
        medication.setFrequency(frequency);
        medication.setMedication(name);
        medication.setDiscontinued(false);


        Main.dialogCanceled = false;
        Stage s = (Stage)addMedBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void reset(){

        dosageSpinner.getValueFactory().setValue(0);
        dosageChoiceBox.getSelectionModel().clearSelection();
        frequencyChoiceBox.getSelectionModel().clearSelection();
        txtMedication.setText("");

    }
}

package com.patientmanagement.controllers;


import com.EntityClasses.Medication;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class MedicationController implements Initializable,ControlledScreen {

    ScreenController controller;


    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dosageChoiceBox.getItems().addAll("tablets", "ml", "mg");
        frequencyChoiceBox.getItems().addAll("once daily", "twice daily", "thrice daily");
        ArrayList<String> values = new ArrayList<>();
        values.add(0,"roomba");
        values.add(1,"zoomba");
        values.add(2,"koromba");
        values.add(3,"cocoomba");

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

        Medication med = new Medication();
        med.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        med.setDosage(dosage);
        med.setDosageType(dosageType);
        med.setFrequency(frequency);
        med.setMedication(name);
        med.setDiscontinued(false);


        Session session = Main.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(med);
        session.getTransaction().commit();

        Main.dialogCanceled = false;
        Stage s = (Stage)addMedBtn.getScene().getWindow();
        s.close();
    }
}

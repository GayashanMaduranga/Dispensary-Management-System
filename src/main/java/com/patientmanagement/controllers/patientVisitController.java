package com.patientmanagement.controllers;

import com.EntityClasses.DoctorSession;
import com.EntityClasses.Measure;
import com.EntityClasses.MeasureValue;
import com.EntityClasses.Patient;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Damma on 8/26/2017.
 */
public class patientVisitController implements Initializable,ControlledScreen {

    ScreenController controller;


    static DoctorSession doctorSession;

    static Measure measure;

    static List<Measure> updatedMeasures;
    static List<Measure> addedMeasures;

    private Patient patient;

    // for measures table ************************************
    @FXML
    private TreeTableView<Measure> measureTable;

    @FXML
    private TreeTableColumn<Measure, String> measureNameCol;

    @FXML
    private TreeTableColumn<Measure, Boolean> measureValueCol;

    private List<TreeItem<Measure>> measuresList;

    // for measures table ************************************

    @FXML
    private Label lblCurrentDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddMeasure;

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
    private Button addMedBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancelBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void completeSession(){


//        patient.setDOB(Date.valueOf(txtDOB.getValue()));
//        patient.setPname(txtName.getText().toLowerCase());
//        patient.setGender(((RadioButton)genderGroup.getSelectedToggle()).getText());
//        patient.setNIC(txtNIC.getText());
//        patient.setContactNumber(txtPhone.getText());
//        patient.setEmail(txtEmail.getText().toLowerCase());
//        patient.setOccupation(txtOccupation.getText());

        Main.dialogCanceled = false;
        Stage s = (Stage)addMedBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void addMeasure(){

        Measure m = new Measure();
        m.setName(txtAddMeasure.getText());
        m.setDateUpdated(java.sql.Date.valueOf(java.time.LocalDate.now()));

        addedMeasures.add(m);

        measuresList.add(new TreeItem<>(m));

        measureTable.getRoot().getChildren().clear();
        measureTable.getRoot().getChildren().addAll(measuresList);

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        doctorSession =  new DoctorSession();
        lblCurrentDate.setText(java.sql.Date.valueOf(java.time.LocalDate.now()).toString());
        measuresList = new ArrayList<>();
        updatedMeasures = new ArrayList<>();
        addedMeasures = new ArrayList<>();

        updateMeasureTable();

    }

    private void updateMeasureTable(){

//for measure table******************************************************************************************//

        List<Measure> measures = PatientSummaryController.summaryPatient.getMeasures();

        for (Measure measure : measures){

            measuresList.add(new TreeItem<>(measure));
        }

//for measure table**********************************************************************************************            //
        measureNameCol.setCellValueFactory(param -> param.getValue().getValue().nameProperty());                                      //         //
        measureValueCol.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));                           //
        measureValueCol.setCellFactory(param -> new MeasureActionCell(measureTable));             //
//for measure table**********************************************************************************************            //

        Measure m = new Measure();
        m.setName("null");
        m.setDateUpdated(java.sql.Date.valueOf(java.time.LocalDate.now()));

        TreeItem<Measure> root = new TreeItem<>(m);
        root.getChildren().addAll(measuresList);
        measureTable.setRoot(root);
        measureTable.setShowRoot(false);

    }

    private class MeasureActionCell extends TreeTableCell<Measure, Boolean> {

        private Button addButton = new Button("   Set   ");
        private TextField txtValue = new TextField();
        final HBox paddedButton = new HBox(15);

        /**
         * EditMedicationCell constructor
         * @param fromTable the Table in which button resides.
         */

        MeasureActionCell(final TreeTableView<Measure> fromTable /*, final TreeTableView<Medication> toTable, boolean discontinue*/ ) {

            addButton.getStyleClass().clear();
            addButton.getStyleClass().add("button-blue-table");

            paddedButton.setPadding(new Insets(3));
            paddedButton.setAlignment(Pos.CENTER);
            paddedButton.getChildren().addAll(txtValue, addButton);
            addButton.setOnAction(actionEvent -> {

                fromTable.getSelectionModel().select(getTreeTableRow().getIndex());

                TreeItem<Measure> m = fromTable.getSelectionModel().getSelectedItem();

                int val = Integer.parseInt(txtValue.getText());

                MeasureValue mesVal = new MeasureValue();
                mesVal.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
                mesVal.setValue(val);

                Measure mes = m.getValue();
                mes.getValues().add(mesVal);

                updatedMeasures.add(mes);

                addButton.setText(" updated ");
                addButton.setDisable(true);
                txtValue.setEditable(false);

//                for (Measure mm: updatedMeasures) {
//
//                    List <MeasureValue>mesvals = mm.getValues();
//
//                    for(int i = 0; i < mesvals.size(); i++) {
//                        System.out.println(mesvals.get(i).getValue());
//                        System.out.println(mesvals.get(i).getDate());
//                    }
//                }


            });
        }

        /** places an add button in the row only if the row is not empty. */
        @Override protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
}

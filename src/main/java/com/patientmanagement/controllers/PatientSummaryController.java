package com.patientmanagement.controllers;


import com.EntityClasses.Measure;
import com.EntityClasses.MeasureValue;
import com.EntityClasses.Medication;
import com.EntityClasses.Patient;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.controllers.MainScreens;
import com.main.models.LoginModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Created by Damsith on 8/1/2017.
 */

public class PatientSummaryController implements Initializable,ControlledScreen {

    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }


// for medication table ************************************
    @FXML
    private TreeTableView<Medication> medTable;

    @FXML
    private TreeTableColumn<Medication, String> date_col;

    @FXML
    private TreeTableColumn<Medication, String> name_col;

    @FXML
    private TreeTableColumn<Medication, String> dosage_col;

    @FXML
    private TreeTableColumn<Medication, String> frequency_col;

    @FXML
    private TreeTableColumn<Medication, Boolean> action_col;

    private List<TreeItem<Medication>> mediList;

// for medication table ************************************

// for discontinued table ************************************

    @FXML
    private TreeTableView<Medication> discontinued_med_Table;

    @FXML
    private TreeTableColumn<Medication, String> start_date_col;

    @FXML
    private TreeTableColumn<Medication, String> discontinued_name_col;

    @FXML
    private TreeTableColumn<Medication, String> discontinued_dosage_col;

    @FXML
    private TreeTableColumn<Medication, String> discontinued_frequency_col;

    @FXML
    private TreeTableColumn<Medication, Boolean> discontinued_action_col;

    private List<TreeItem<Medication>> discontinuedMediList;

// for discontinued table ************************************

// for measures table ************************************
    @FXML
    private TreeTableView<Measure> measuresTable;

    @FXML
    private TreeTableColumn<Measure, String> measureCol;

    @FXML
    private TreeTableColumn<Measure, String> lastUpdateCol;

    @FXML
    private TreeTableColumn<Measure, Number> HighestCol;

    @FXML
    private TreeTableColumn<Measure, Boolean> measureActionCol;

    private List<TreeItem<Measure>> measuresList;

// for measures table ************************************


    private Session session;

    private List<Patient> patients;

    static Patient summaryPatient;


    @FXML
    private Label lblPname;

    @FXML
    private Label lblPage;

    @FXML
    private Label lblPid;

    @FXML
    private Label lblPoccupation;

    @FXML
    private Label lblPphone;

    @FXML
    private Label lblPemail;

    @FXML
    private Label userLbl;

    @FXML
    private TextField txtGetpatient;

    @FXML
    private TextField txtAddMeasure;

    @FXML
    private Button addMeasureBtn;

    @FXML
    private Button newSessionBtn;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private Button addMedBtn;


    @FXML
    void showHome(){
        ScreenController.changeScreen(controller, PatientScreens.DASHBOARD_SCREEN, PatientScreens.MAIN_DASHBOARD_SCREEN);
    }

    @FXML
    void logout(){

        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage)homeBtn.getScene().getWindow();
            s.close();
        }
    }

    @FXML
    void addMedication(){


        Stage s = (Stage) medTable.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/patientmanagement/Medication.fxml"))){

            Medication m = MedicationController.medication;

            summaryPatient.getMedications().add(m);

            session.beginTransaction();
            session.save(summaryPatient);
            session.getTransaction().commit();

            int pid = summaryPatient.getpId();

            mediList.add(new TreeItem<>(m));
            medTable.getRoot().getChildren().clear();
            medTable.getRoot().getChildren().addAll(mediList);

            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }
    }

    @FXML
    void newSession(){


        Stage s = (Stage) medTable.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/patientmanagement/PatientVisit.fxml"))){

            session.beginTransaction();

            for (Measure mess: patientVisitController.addedMeasures) {
                summaryPatient.getMeasures().add(mess);
                measuresList.add(new TreeItem<>(mess));
            }
            for (Measure mess: patientVisitController.updatedMeasures) {
                session.update(mess);
            }

            session.save(summaryPatient);
            session.getTransaction().commit();

            measuresTable.getRoot().getChildren().clear();
            measuresTable.getRoot().getChildren().addAll(measuresList);

//            Medication m = MedicationController.medication;
//
//            summaryPatient.getMedications().add(m);
//
//            session.beginTransaction();
//            session.save(summaryPatient);
//            session.getTransaction().commit();
//
//            int pid = summaryPatient.getpId();
//
//            mediList.add(new TreeItem<>(m));
//            medTable.getRoot().getChildren().clear();
//            medTable.getRoot().getChildren().addAll(mediList);

            System.out.println("new session");

            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }
    }

    @FXML
    void addMeasure(){

        Measure m = new Measure();
        m.setName(txtAddMeasure.getText());
        m.setDateUpdated(java.sql.Date.valueOf(java.time.LocalDate.now()));

        summaryPatient.getMeasures().add(m);

        session.beginTransaction();
        session.save(summaryPatient);
        session.getTransaction().commit();

        int pid = summaryPatient.getpId();

        measuresList.add(new TreeItem<>(m));
        measuresTable.getRoot().getChildren().clear();
        measuresTable.getRoot().getChildren().addAll(measuresList);

    }

    @FXML
    void populateDetails(){

        String patientName = txtGetpatient.getText();

        patients.clear();

        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p from Patient p where p.pname = '"+patientName+"'");
        patients = patientNameQuery.list();
        session.getTransaction().commit();

        summaryPatient = patients.get(0);

        lblPid.setText(Integer.toString(summaryPatient.getpId()));
        lblPoccupation.setText(summaryPatient.getOccupation());
        lblPemail.setText(summaryPatient.getEmail());
        lblPphone.setText(summaryPatient.getContactNumber());
        lblPage.setText(summaryPatient.getDOB().toString());
        lblPname.setText(summaryPatient.getPname());

        updateMedTable();
        updateDiscTable();
        updateMeasureTable();

    }

    private void updateMeasureTable(){

//for measure table******************************************************************************************//

        List<Measure> measures = summaryPatient.getMeasures();

        for (Measure measure : measures){

                measuresList.add(new TreeItem<>(measure));
        }

//for measure table**********************************************************************************************            //

        measureCol.setCellValueFactory(param -> param.getValue().getValue().nameProperty());                                      //
        lastUpdateCol.setCellValueFactory(param -> param.getValue().getValue().dateUpdatedProperty());                                //
//        dosage_col.setCellValueFactory(param -> param.getValue().getValue().dosageStringProperty());                            //
//        frequency_col.setCellValueFactory(param -> param.getValue().getValue().frequencyProperty());                            //
        measureActionCol.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));                           //
        measureActionCol.setCellFactory(param -> new MeasureActionCell(measuresTable));              //
//for measure table**********************************************************************************************            //

        Measure m = new Measure();
        m.setName("null");
        m.setDateUpdated(java.sql.Date.valueOf(java.time.LocalDate.now()));

        TreeItem<Measure> root = new TreeItem<>(m);
        root.getChildren().addAll(measuresList);
        measuresTable.setRoot(root);
        measuresTable.setShowRoot(false);

    }

    private void updateMedTable(){

//for medication table******************************************************************************************//

        List<Medication> meds = summaryPatient.getMedications();

        for (Medication medication : meds){

            if(!(medication.isDiscontinued())){
                mediList.add(new TreeItem<>(medication));
            }
        }

//for medication table**********************************************************************************************            //

        date_col.setCellValueFactory(param -> param.getValue().getValue().dateProperty());                                      //
        name_col.setCellValueFactory(param -> param.getValue().getValue().medicationProperty());                                //
        dosage_col.setCellValueFactory(param -> param.getValue().getValue().dosageStringProperty());                            //
        frequency_col.setCellValueFactory(param -> param.getValue().getValue().frequencyProperty());                            //
        action_col.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));                           //
        action_col.setCellFactory(param -> new DiscontinueMedicationCell(medTable, discontinued_med_Table, true));              //

//for medication table**********************************************************************************************            //

        Medication m = new Medication();
        m.setDosage(0);
        m.setMedication("null");
        m.setFrequency("null");
        m.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        m.setDiscontinued(false);

        TreeItem<Medication> root = new TreeItem<>(m);
        root.getChildren().addAll(mediList);
        medTable.setRoot(root);
        medTable.setShowRoot(false);

    }

    private void updateDiscTable(){

//for discontinued medication table***********************************************************//

        List<Medication> discMeds = summaryPatient.getMedications();

        for (Medication medication : discMeds){

            if(medication.isDiscontinued()){
                discontinuedMediList.add(new TreeItem<>(medication));
            }
        }

//*********  setting cell value factories   ************************************************************************************

        start_date_col.setCellValueFactory(param -> param.getValue().getValue().dateProperty());
        discontinued_name_col.setCellValueFactory(param -> param.getValue().getValue().medicationProperty());
        discontinued_dosage_col.setCellValueFactory(param -> param.getValue().getValue().dosageStringProperty());
        discontinued_frequency_col.setCellValueFactory(param -> param.getValue().getValue().frequencyProperty());
        discontinued_action_col.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
        discontinued_action_col.setCellFactory(param -> new DiscontinueMedicationCell(discontinued_med_Table, medTable, false));

//for discontinued medication table**********************************************************************************************

        Medication d = new Medication();
        d.setDosage(0);
        d.setMedication("null");
        d.setFrequency("null");
        d.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        d.setDiscontinued(true);

        TreeItem<Medication> root = new TreeItem<>(d);
        root.getChildren().addAll(discontinuedMediList);
        discontinued_med_Table.setRoot(root);
        discontinued_med_Table.setShowRoot(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(LoginModel.getUser());

        session = Main.getSessionFactory().openSession();

        patients = new ArrayList<>();
        mediList = new ArrayList<>();
        measuresList = new ArrayList<>();
        discontinuedMediList = new ArrayList<>();

        List<String> values;

//############ POPULATING PATIENT SEARCH BAR ###############################################

        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p.pname  from Patient p");
        values = patientNameQuery.list();
        session.getTransaction().commit();

        TextFields.bindAutoCompletion(txtGetpatient, values);

//###########################################################################################


//for measures table******************************************************************************************//
                                                                                                                //
//        session.beginTransaction();                                                                             //
//        measuresList = new ArrayList<>();                                                                           //
//        Query measuresTableQuery = session.createQuery("select mes from Measure mes");            //
//        List<Measure> measures = measuresTableQuery.list();                                                           //
//        session.getTransaction().commit();                                                                      //
                                                                                                                //
                                                                                                                //
//for measures table******************************************************************************************//

//##############################################################################################################//

//
//        for (Measure mes : measures){
//            measuresList.add(new TreeItem<>(mes));
//        }


//##############   CELL FACTORIES   ############################################################################################//
//for measures table********************************************************************************************                //

//        dateCol.setCellValueFactory(param -> param.getValue().getValue().dateProperty());                                       //
//        weightCol.setCellValueFactory(param -> param.getValue().getValue().weightProperty());                                   //
//        HeightCol.setCellValueFactory(param -> param.getValue().getValue().heightProperty());                                   //
//        tempCol.setCellValueFactory(param -> param.getValue().getValue().tempProperty());                                       //
//        BPCol.setCellValueFactory(param -> param.getValue().getValue().bpProperty());                                           //
//        respRateCol.setCellValueFactory(param -> param.getValue().getValue().respRateProperty());                               //
//        pulseRateCol.setCellValueFactory(param -> param.getValue().getValue().pulseRateProperty());
//        glucoseCol.setCellValueFactory(param -> param.getValue().getValue().bloodGlucoseProperty());

//for measures table********************************************************************************************                //
//##############################################################################################################################//

//
//        Measure meas = new Measure();
//        meas.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
//        meas.setBloodGlucose(0);
//        meas.setBp(0);
//        meas.setHeight(0);
//        meas.setPulseRate(0);
//        meas.setRespRate(0);
//        meas.setTemp(0);
//        meas.setWeight(0);
//
//        TreeItem<Measure> root3 = new TreeItem<>(meas);
//
//        root3.getChildren().addAll(measuresList);
//
//        measuresTable.setRoot(root3);
//        measuresTable.setShowRoot(false);
    }

    private class MeasureActionCell extends TreeTableCell<Measure, Boolean> {

        private Button viewButton = new Button(" Graph ");
        private Button removeButton = new Button(" delete ");
        final HBox paddedButton = new HBox(10);

        /**
         * EditMedicationCell constructor
         * @param fromTable the Table in which button resides.
         */

        MeasureActionCell(final TreeTableView<Measure> fromTable /*, final TreeTableView<Medication> toTable, boolean discontinue*/ ) {

            viewButton.getStyleClass().clear();
            viewButton.getStyleClass().add("button-blue");

            removeButton.getStyleClass().clear();
            removeButton.getStyleClass().add("button-red");

            paddedButton.setPadding(new Insets(3));
            paddedButton.setAlignment(Pos.CENTER);
            paddedButton.getChildren().addAll(viewButton, removeButton);
            removeButton.setOnAction(actionEvent -> {

                fromTable.getSelectionModel().select(getTreeTableRow().getIndex());

                TreeItem<Measure> m = fromTable.getSelectionModel().getSelectedItem();
                String measureName = m.getValue().getName();

                measuresList.removeIf(measureTreeItem -> {
                    boolean flag = false;
                    if(measureTreeItem.getValue().getName() == measureName)
                        flag = true;

                    return flag;
                });

                measuresTable.getRoot().getChildren().clear();
                measuresTable.getRoot().getChildren().addAll(measuresList);

                summaryPatient.getMeasures().removeIf(measure -> {

                    boolean flag = false;
                    if(measure.getName() == measureName)
                        flag = true;

                    return flag;
                });

                session.beginTransaction();
                session.update(summaryPatient);
                session.getTransaction().commit();

//                if(discontinue){
//                    m.getValue().setDiscontinued(true);
//                }else{
//                    m.getValue().setDiscontinued(false);
//                }
//
//

//
//                toTable.getRoot().getChildren().clear();
//                fromTable.getRoot().getChildren().clear();
//
//                if(discontinue){
//                    mediList.remove(m);
//                    discontinuedMediList.add(m);
//                    fromTable.getRoot().getChildren().addAll(mediList);
//                    toTable.getRoot().getChildren().addAll(discontinuedMediList);
//                }else{
//                    discontinuedMediList.remove(m);
//                    mediList.add(m);
//                    fromTable.getRoot().getChildren().addAll(discontinuedMediList);
//                    toTable.getRoot().getChildren().addAll(mediList);
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
    private class DiscontinueMedicationCell extends TreeTableCell<Medication, Boolean> {
        // a button for adding a new person.
        private Button discontinueButton = new Button("Stop");
        // a button for adding a new person.
        private Button editButton = new Button(" Edit ");
        // pads and centers the buttons in the cell.
        final HBox paddedButton = new HBox(10);

        /**
         * EditMedicationCell constructor
         * @param fromTable the Table in which button resides.
         * @param toTable the Table to which the record will go
         */

        DiscontinueMedicationCell(final TreeTableView<Medication> fromTable, final TreeTableView<Medication> toTable, boolean discontinue ) {

            editButton.getStyleClass().clear();
            editButton.getStyleClass().add("button-blue");
            if(discontinue){
                discontinueButton.setText(" Stop ");
                discontinueButton.getStyleClass().clear();
                discontinueButton.getStyleClass().add("button-red");
            }else{
                discontinueButton.setText(" Start ");
                discontinueButton.getStyleClass().clear();
                discontinueButton.getStyleClass().add("button-green");
            }

            paddedButton.setPadding(new Insets(3));
            paddedButton.setAlignment(Pos.CENTER);
            paddedButton.getChildren().addAll(discontinueButton, editButton);
            discontinueButton.setOnAction(actionEvent -> {

                fromTable.getSelectionModel().select(getTreeTableRow().getIndex());

                TreeItem<Medication> m = fromTable.getSelectionModel().getSelectedItem();
                if(discontinue){
                    m.getValue().setDiscontinued(true);
                }else{
                    m.getValue().setDiscontinued(false);
                }


                session.beginTransaction();
                session.update(m.getValue());
                session.getTransaction().commit();

                toTable.getRoot().getChildren().clear();
                fromTable.getRoot().getChildren().clear();

                if(discontinue){
                    mediList.remove(m);
                    discontinuedMediList.add(m);
                    fromTable.getRoot().getChildren().addAll(mediList);
                    toTable.getRoot().getChildren().addAll(discontinuedMediList);
                }else{
                    discontinuedMediList.remove(m);
                    mediList.add(m);
                    fromTable.getRoot().getChildren().addAll(discontinuedMediList);
                    toTable.getRoot().getChildren().addAll(mediList);
                }

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


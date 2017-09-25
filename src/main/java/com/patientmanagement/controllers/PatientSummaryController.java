package com.patientmanagement.controllers;


import com.EntityClasses.*;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.patientmanagement.DiscontinuedReasonBox;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Created by Damsith on 8/1/2017.
 */

@SuppressWarnings("unchecked")
public class PatientSummaryController implements Initializable,SessionListener {

// for allergy table ************************************
    @FXML
    private TreeTableView<Allergy> allergyTable;

    @FXML
    private TreeTableColumn<Allergy, String> causeCol;

    @FXML
    private TreeTableColumn<Allergy, String> descriptionCol;

    private List<TreeItem<Allergy>> allergyList;

// for allergy table ************************************

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
    private TreeTableColumn<Medication, String> discontinued_reason_col;

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

    static Measure selectedMeasure;


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
    public TitledPane pastVisitPane12;

    @FXML
    public TitledPane pastVisitPane;

    @FXML
    public TitledPane pastVisitPane11;

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

    private MainScreenController mainScreenController;


//    @FXML
//    void showHome(){
//        ScreenController.changeScreen(controller, PatientScreens.DASHBOARD_SCREEN, PatientScreens.MAIN_DASHBOARD_SCREEN);
//    }

//    @FXML
//    void logout(){
//
//        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
//            Main.createLogin(new Stage());
//            Stage s = (Stage)homeBtn.getScene().getWindow();
//            s.close();
//        }
//    }

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

            int ID = summaryPatient.getpId();
            summaryPatient = (Patient) session.get(Patient.class, ID);

            session.getTransaction().commit();

            updateMedTable();
            updateDiscTable();
            updateMeasureTable();

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
        updateAlelrgyTable();

    }

    private void updateAlelrgyTable(){

//for allergy table******************************************************************************************//

        List<Allergy> allergies = summaryPatient.getAllergies();

        allergyList.clear();

        for (Allergy allergy : allergies){

            allergyList.add(new TreeItem<>(allergy));
        }

//for allergy table**********************************************************************************************

        causeCol.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        descriptionCol.setCellValueFactory(param -> param.getValue().getValue().descriptionProperty());

//for allergy table**********************************************************************************************

        Allergy m = new Allergy();
        m.setDescription("null");
        m.setCause("null");

        TreeItem<Allergy> root = new TreeItem<>(m);
        root.getChildren().addAll(allergyList);
        allergyTable.setRoot(root);
        allergyTable.setShowRoot(false);

    }

    private void updateMeasureTable(){

//for measure table******************************************************************************************//

        List<Measure> measures = summaryPatient.getMeasures();

        measuresList.clear();

        for (Measure measure : measures){

            measuresList.add(new TreeItem<>(measure));
        }

//for measure table**********************************************************************************************

        measureCol.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        lastUpdateCol.setCellValueFactory(param -> param.getValue().getValue().dateUpdatedProperty());
        measureActionCol.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
        measureActionCol.setCellFactory(param -> new MeasureActionCell(measuresTable));              //
//for measure table**********************************************************************************************

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

        mediList.clear();

        for (Medication medication : meds){

            if(!(medication.isDiscontinued())){
                mediList.add(new TreeItem<>(medication));
            }
        }

//for medication table**********************************************************************************************

        date_col.setCellValueFactory(param -> param.getValue().getValue().dateProperty());
        name_col.setCellValueFactory(param -> param.getValue().getValue().medicationProperty());
        dosage_col.setCellValueFactory(param -> param.getValue().getValue().dosageStringProperty());
        frequency_col.setCellValueFactory(param -> param.getValue().getValue().frequencyProperty());
        action_col.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
        action_col.setCellFactory(param -> new DiscontinueMedicationCell(medTable, discontinued_med_Table, true));

//for medication table**********************************************************************************************

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

        discontinuedMediList.clear();

        for (Medication medication : discMeds){

            if(medication.isDiscontinued()){
                discontinuedMediList.add(new TreeItem<>(medication));
            }
        }

//*********  setting cell value factories   ************************************************************************************

        start_date_col.setCellValueFactory(param -> param.getValue().getValue().dateProperty());
        discontinued_name_col.setCellValueFactory(param -> param.getValue().getValue().medicationProperty());
        discontinued_reason_col.setCellValueFactory(param -> param.getValue().getValue().discontinuedReasonProperty());
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

        session = ScreenController.getSession();

        patients = new ArrayList<>();
        mediList = new ArrayList<>();
        measuresList = new ArrayList<>();
        discontinuedMediList = new ArrayList<>();
        allergyList = new ArrayList<>();

//############ POPULATING PATIENT SEARCH BAR ###############################################

        List<String> values;
        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p.pname  from Patient p");
        values = patientNameQuery.list();
        session.getTransaction().commit();

        TextFields.bindAutoCompletion(txtGetpatient, values);

//###########################################################################################

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;


    }

    private class MeasureActionCell extends TreeTableCell<Measure, Boolean> {

        private final Button viewButton = new Button(" Graph ");
        private final Button removeButton = new Button(" delete ");
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

            });

            viewButton.setOnAction(actionEvent -> {

                fromTable.getSelectionModel().select(getTreeTableRow().getIndex());

                TreeItem<Measure> m = fromTable.getSelectionModel().getSelectedItem();

                selectedMeasure = m.getValue();

                createMeasureChart();


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
        private final Button discontinueButton = new Button("Stop");
        // a button for adding a new person.
        private final Button editButton = new Button(" Edit ");
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

                    if (discontinue) {

                        if (DiscontinuedReasonBox.show()) {

                            m.getValue().setDiscontinued(true);
                            m.getValue().setDiscontinuedReason(DiscontinuedReasonBox.getDiscontinuedAnswer());

                            if(DiscontinuedReasonBox.getDiscontinuedAnswer().equals("Allergic Reaction")){

                                Allergy a = new Allergy();
                                a.setCause("Medication");
                                a.setDescription("Allergic reaction to "+m.getValue().getMedication());

                                summaryPatient.getAllergies().add(a);

                                session.beginTransaction();
                                session.save(summaryPatient);
                                session.getTransaction().commit();

                                allergyList.add(new TreeItem<>(a));
                                allergyTable.getRoot().getChildren().clear();
                                allergyTable.getRoot().getChildren().addAll(allergyList);

                            }

                            session.beginTransaction();
                            session.update(m.getValue());
                            session.getTransaction().commit();

                            toTable.getRoot().getChildren().clear();
                            fromTable.getRoot().getChildren().clear();

                            mediList.remove(m);
                            discontinuedMediList.add(m);
                            fromTable.getRoot().getChildren().addAll(mediList);
                            toTable.getRoot().getChildren().addAll(discontinuedMediList);

                        } else {

                            System.out.println(DiscontinuedReasonBox.getDiscontinuedAnswer());

                        }

                    }else{

                        m.getValue().setDiscontinued(false);

                        session.beginTransaction();
                        session.update(m.getValue());
                        session.getTransaction().commit();

                        toTable.getRoot().getChildren().clear();
                        fromTable.getRoot().getChildren().clear();

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

    private void createMeasureChart(){

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/patientmanagement/measureChart.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Welcome New Dispensary");
            stage.setMaximized(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(1200.0);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


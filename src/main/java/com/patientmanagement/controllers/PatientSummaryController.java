package com.patientmanagement.controllers;


import com.EntityClasses.Medication;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.models.LoginModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    private Session session;

    @FXML
    private Label userLbl;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private Button addMedBtn;

    @FXML
    void logout(){

        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage)logoutBtn.getScene().getWindow();
            s.close();
        }
    }

    @FXML
    void addMedication(){

        Stage s = (Stage) medTable.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/patientmanagement/Medication.fxml"))){

            mediList.clear();

            session.beginTransaction();
            Query medTableQuery = session.createQuery("from Medication m where m.discontinued = false");              //
            List<Medication> meds = medTableQuery.list();                                                           //
            session.getTransaction().commit();

            for (Medication medication : meds){
                mediList.add(new TreeItem<>(medication));
            }
            medTable.getRoot().getChildren().clear();
            medTable.getRoot().getChildren().addAll(mediList);
            Main.dialogCanceled = true;
        }else{
            System.out.println("canceled");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(LoginModel.getUser());

        session=Main.getSessionFactory().openSession();

//##############   TRANSACTIONS/POPULATING ARRAY-LISTS   #######################################################//
//for medication table******************************************************************************************//
                                                                                                                //
        session.beginTransaction();                                                                             //
        mediList = new ArrayList<>();                                                                           //
        Query medTableQuery = session.createQuery("from Medication m where m.discontinued = false");              //
        List<Medication> meds = medTableQuery.list();                                                           //
        session.getTransaction().commit();                                                                      //
                                                                                                                //
                                                                                                                //
//for medication table******************************************************************************************//
//for discontinued table****************************************************************************************//
                                                                                                                //
        session.beginTransaction();                                                                             //
        discontinuedMediList = new ArrayList<>();                                                               //
        Query discTableQuery = session.createQuery("select d from Medication d where d.discontinued = true");              //
        List<Medication> discMeds = discTableQuery.list();                                                      //
        session.getTransaction().commit();                                                                      //
                                                                                                                //
                                                                                                                //
//for discontinued table****************************************************************************************//
//##############################################################################################################//

        for (Medication medication : meds){
            mediList.add(new TreeItem<>(medication));
        }

        for (Medication discontinued : discMeds){
            discontinuedMediList.add(new TreeItem<>(discontinued));
        }


//##############   CELL FACTORIES   ############################################################################################//
//for medication table**********************************************************************************************            //
        date_col.setCellValueFactory(param -> param.getValue().getValue().dateProperty());                                      //
        name_col.setCellValueFactory(param -> param.getValue().getValue().medicationProperty());                                //
        dosage_col.setCellValueFactory(param -> param.getValue().getValue().dosageStringProperty());                                  //
        frequency_col.setCellValueFactory(param -> param.getValue().getValue().frequencyProperty());                            //
        action_col.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));                           //
        action_col.setCellFactory(param -> new DiscontinueMedicationCell(medTable, discontinued_med_Table, true));              //
//for medication table**********************************************************************************************            //
//for discontinued table********************************************************************************************            //
        start_date_col.setCellValueFactory(param -> param.getValue().getValue().dateProperty());                                //
        discontinued_name_col.setCellValueFactory(param -> param.getValue().getValue().medicationProperty());                   //
        discontinued_dosage_col.setCellValueFactory(param -> param.getValue().getValue().dosageStringProperty());                     //
        discontinued_frequency_col.setCellValueFactory(param -> param.getValue().getValue().frequencyProperty());               //
        discontinued_action_col.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));              //
        discontinued_action_col.setCellFactory(param -> new DiscontinueMedicationCell(discontinued_med_Table, medTable, false));//
//for discontinued table********************************************************************************************            //
//##############################################################################################################################//

        Medication m = new Medication();
        m.setDosage(0);
        m.setMedication("null");
        m.setFrequency("null");
        m.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        m.setDiscontinued(false);

        Medication d = new Medication();
        d.setDosage(0);
        d.setMedication("null");
        d.setFrequency("null");
        d.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        d.setDiscontinued(true);

        TreeItem<Medication> root = new TreeItem<>(m);
        TreeItem<Medication> root2 = new TreeItem<>(d);

        root.getChildren().addAll(mediList);
        root2.getChildren().addAll(discontinuedMediList);

        medTable.setRoot(root);
        medTable.setShowRoot(false);

        discontinued_med_Table.setRoot(root2);
        discontinued_med_Table.setShowRoot(false);
    }

    private class DiscontinueMedicationCell extends TreeTableCell<Medication, Boolean> {
        // a button for adding a new person.
        private Button discontinueButton = new Button("Stop");
        // a button for adding a new person.
        private Button editButton = new Button(" Edit ");
        // pads and centers the add button in the cell.
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


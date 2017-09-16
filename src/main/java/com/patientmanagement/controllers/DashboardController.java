package com.patientmanagement.controllers;


import com.EntityClasses.Patient;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.models.LoginModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Created by Damsith on 8/1/2017.
 */

public class DashboardController implements Initializable,ControlledScreen {

    ScreenController controller;

    ObservableList<Patient> patientList = FXCollections.observableArrayList();

    TreeItem<Patient> root;

    Session session;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(LoginModel.getUser());
        session = Main.getSessionFactory().openSession();

        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p from Patient p");
        List<Patient> patients = patientNameQuery.list();
        session.getTransaction().commit();

        for (Patient p : patients){

            patientList.add(p);
        }

        JFXTreeTableColumn<Patient, String> nameCol =  new JFXTreeTableColumn<>("Name");
        nameCol.setCellValueFactory(param -> param.getValue().getValue().pnameProperty());

        JFXTreeTableColumn<Patient, String> NICCol =  new JFXTreeTableColumn<>("NIC");
        NICCol.setCellValueFactory(param -> param.getValue().getValue().NICProperty());

        JFXTreeTableColumn<Patient, String> DOBCol =  new JFXTreeTableColumn<>("D.O.B");
        DOBCol.setCellValueFactory(param -> param.getValue().getValue().DOBProperty());

        JFXTreeTableColumn<Patient, String> occuCol =  new JFXTreeTableColumn<>("Occupation");
        occuCol.setCellValueFactory(param -> param.getValue().getValue().occupationProperty());

        JFXTreeTableColumn<Patient, String> phoneCol =  new JFXTreeTableColumn<>("Contact");
        phoneCol.setCellValueFactory(param -> param.getValue().getValue().contactNumberProperty());

        JFXTreeTableColumn<Patient, String> emailCol =  new JFXTreeTableColumn<>("Email");
        emailCol.setCellValueFactory(param -> param.getValue().getValue().emailProperty());

        root = new RecursiveTreeItem<Patient>(patientList, RecursiveTreeObject::getChildren);

        patientTable.getColumns().setAll(nameCol, NICCol, DOBCol, occuCol, phoneCol, emailCol);
        patientTable.setRoot(root);
        patientTable.setShowRoot(false);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> patientTable.setPredicate(patientTreeItem -> {
            boolean flag = patientTreeItem.getValue().pnameProperty().getValue().contains(newValue.toLowerCase());
            return flag;
        }));

    }

    @FXML
    private Label userLbl;

    @FXML
    private TextField searchBar;

    @FXML
    private JFXTreeTableView<Patient> patientTable;

    @FXML
    private JFXButton sidebarRegisterBtn;

    @FXML
    private JFXButton sidebarPrescriptionBtn;

    @FXML
    private JFXButton sidebarBillBtn;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    void changeScene(Event event){

        switch(((JFXButton)event.getSource()).getId()) {

//            case "sidebarRegisterBtn":
//                Stage s = (Stage) logoutBtn.getScene().getWindow();
//                if(!(Main.createFadedWindow(new Stage(), s,"/com/patientmanagement/RegisterPatient.fxml"))){
//                    System.out.println("added patient");
//                    Main.dialogCanceled = true;
//                }else{
//                    System.out.println("canceled");
//                }
//                break;

            case "titlebtn":
                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.PATIENT_SUMMARY_SCREEN);
                break;
            case "sidebarPrescriptionBtn":
                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.MEDICATION_SCREEN);
                break;
            case "sidebarBillBtn":
                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.BILL_SCREEN);
                break;

        }
    }

    @FXML
    void addPatient(){

        Stage s = (Stage) sidebarRegisterBtn.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/patientmanagement/RegisterPatient.fxml"))){

            Patient p = RegisterPatientController.patient ;

            session.beginTransaction();

            session.save(p);

            session.getTransaction().commit();

            root = new RecursiveTreeItem<Patient>(patientList, RecursiveTreeObject::getChildren);

            patientList.add(p);

            patientTable.refresh();


            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }
    }

    @FXML
    void logout(){

        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage)logoutBtn.getScene().getWindow();
            s.close();
        }
    }
}

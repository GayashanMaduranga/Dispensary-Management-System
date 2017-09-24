package com.patientmanagement.controllers;


import com.EntityClasses.Patient;
import com.common.ConfirmDialog;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreenController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.models.LoginModel;
import db.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class DashboardController implements Initializable,SessionListener {

    private Patient p;

//    ScreenController controller;

    ObservableList<Patient> patientList = FXCollections.observableArrayList();

    TreeItem<Patient> root;

    private Session session;
    private MainScreenController mainScreenController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();

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
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtOccupation;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker datePickerDOB;

    @FXML
    private JFXTreeTableView<Patient> patientTable;

    @FXML
    private JFXButton addPatientBtn;


    @FXML
    void addPatient(){

        Stage s = (Stage) addPatientBtn.getScene().getWindow();

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
    void setFields() {

        p = patientTable.getSelectionModel().getSelectedItem().getValue();

        txtName.setText(p.getPname());
        txtID.setText(Integer.toString(p.getpId()));
        txtEmail.setText(p.getEmail());
        txtOccupation.setText(p.getOccupation());
        txtPhone.setText(p.getContactNumber());
        datePickerDOB.setValue(p.getDOB().toLocalDate());

    }
    @FXML
    void updatepatient(){

        p.setPname(txtName.getText());
        p.setOccupation(txtOccupation.getText());
        p.setEmail(txtEmail.getText());
        p.setContactNumber(txtPhone.getText());
        p.setDOB(Date.valueOf(datePickerDOB.getValue()));

        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();

        patientList.removeIf(patient -> {
            boolean flag = false;
                if(patient.getpId() == p.getpId())
                    flag = true;
            return flag;
        });

        patientList.add(p);

        patientTable.refresh();

    }

    @FXML
    void removePatient() {

        if(ConfirmDialog.show("", "Are you sure?")){

            session.beginTransaction();
            session.delete(p);
            session.getTransaction().commit();

            patientList.remove(p);

            patientTable.refresh();

            p = null;

            txtName.setText("");
            txtID.setText("");
            txtEmail.setText("");
            txtOccupation.setText("");
            txtPhone.setText("");
            datePickerDOB.setValue(null);

        }


    }

    @FXML
    void logout(){

        if(ConfirmDialog.show("", "Are you sure you want to logout?")){
            Main.createLogin(new Stage());
            Stage s = (Stage) addPatientBtn.getScene().getWindow();
            s.close();
        }
    }

    @Override
    public void setSession(Session session) {
//        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;


    }
}

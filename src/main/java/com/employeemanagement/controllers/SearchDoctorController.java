package com.employeemanagement.controllers;

import com.EntityClasses.Doctor;
import com.EntityClasses.Staff;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.print.Doc;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by gayashan on 8/13/2017.
 */
@SuppressWarnings("Duplicates")
public class SearchDoctorController implements Initializable,SessionListener {


    @FXML
    private JFXTextField searchtxt;

    @FXML
    private TreeTableView<Doctor> staffTable;

    @FXML
    private TreeTableColumn<Doctor, Number> colId;

    @FXML
    private TreeTableColumn<Doctor, String> colName;

    private List<TreeItem<Doctor>> staffList;
    private Session session;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField nic;

    @FXML
    private JFXTextField contactNumber;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField jobRole;

    private MainScreenController mainController;



    @FXML
    void setSelectionDetails(MouseEvent event) {

        Doctor doctor = staffTable.getSelectionModel().getSelectedItem().getValue();
        fullName.setText(doctor.getName());
        nic.setText(doctor.getNic());
        contactNumber.setText(doctor.getContactNumber());
        email.setText(doctor.getEmail());
        //jobRole.setText(doctor.getSLMCRegNO());
        jobRole.setText(doctor.getSLMCRegNO());

        mainController.setEmployee(doctor);


    }

    @FXML
    void viewProfile(ActionEvent event) {

        ScreenController.changeScreen(MyScreens.VIEW_DOCTOR_SCREEN,mainController.getContent(),mainController);
//        System.out.println(mainController.getEmployee().getName());

    }

    @FXML
    void searchEmployees(KeyEvent event) {

        String name = searchtxt.getText();

        session.beginTransaction();

        Query query = session.createQuery("from Doctor d where name like :keyword ");
        query.setParameter("keyword","%" +name+ "%");

        List<Doctor> staffMemberList = query.list();

        session.getTransaction().commit();

        staffList.clear();
        for (Doctor d : staffMemberList){
            staffList.add(new TreeItem<>(d));
        }


        staffTable.getRoot().getChildren().clear();
        staffTable.getRoot().getChildren().addAll(staffList);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        staffList = new ArrayList<>();
        initTable();

//        initDB();
          new Thread(() -> {

                Platform.runLater(() ->initDB() );

          }).start();


    }

    private void initDB(){

//        session = UserSession.getSession();



        session.beginTransaction();


        Query query = session.createQuery("select s from Doctor s");
        List<Doctor> staffMemberList = query.list();

        session.getTransaction().commit();

        for (Doctor d : staffMemberList){
            staffList.add(new TreeItem<>(d));
        }

        staffTable.getRoot().getChildren().addAll(staffList);
    }

    private void initTable(){
        colId.setCellValueFactory(param -> param.getValue().getValue().employeeidProperty());
        colName.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        Doctor doctor = new Doctor();
        doctor.setEmployeeid(0);
        doctor.setName("Name");

        TreeItem<Doctor> root = new TreeItem<>(doctor);

        staffTable.setRoot(root);
        staffTable.setShowRoot(false);

    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainController = (MainScreenController) controller;

    }
}

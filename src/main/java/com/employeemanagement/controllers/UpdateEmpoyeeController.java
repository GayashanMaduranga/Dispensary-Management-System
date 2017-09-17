package com.employeemanagement.controllers;

import com.EntityClasses.Employee;
import com.EntityClasses.Staff;
import com.common.ConfirmDialog;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXTextField;
import com.main.Main;
import db.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



/**
 * Created by gayashan on 8/13/2017.
 */
public class UpdateEmpoyeeController implements Initializable,SessionListener {


    @FXML
    private JFXTextField searchtxt;

    @FXML
    private TreeTableView<Staff> staffTable;

    @FXML
    private TreeTableColumn<Staff, Number> colId;

    @FXML
    private TreeTableColumn<Staff, String> colName;

    private List<TreeItem<Staff>> staffList;
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



    @FXML
    void setSelectionDetails(MouseEvent event) {

    }

    @FXML
    void viewProfile(ActionEvent event) {

    }

    @FXML
    void searchEmployees(KeyEvent event) {

        String name = searchtxt.getText();

        session.beginTransaction();

        Query query = session.createQuery("from Staff s where name like :keyword ");
        query.setParameter("keyword","%" +name+ "%");

        List<Staff> staffMemberList = query.list();

        session.getTransaction().commit();

        staffList.clear();
        for (Staff s : staffMemberList){
            staffList.add(new TreeItem<>(s));
        }


        staffTable.getRoot().getChildren().clear();
        staffTable.getRoot().getChildren().addAll(staffList);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        staffList = new ArrayList<>();
        initTable();

          new Thread(() -> {

                Platform.runLater(() ->initDB() );

          }).start();


    }

    private void initDB(){
//        session = UserSession.getSession();



        session.beginTransaction();


        Query query = session.createQuery("select s from Staff s");
        List<Staff> staffMemberList = query.list();

        session.getTransaction().commit();

        for (Staff s : staffMemberList){
            staffList.add(new TreeItem<>(s));
        }

        staffTable.getRoot().getChildren().addAll(staffList);
    }

    private void initTable(){
        colId.setCellValueFactory(param -> param.getValue().getValue().employeeidProperty());
        colName.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        Staff staff = new Staff();
        staff.setEmployeeid(0);
        staff.setName("Name");

        TreeItem<Staff> root = new TreeItem<>(staff);

        staffTable.setRoot(root);
        staffTable.setShowRoot(false);

    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}

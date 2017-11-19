package com.employeemanagement.controllers;

import com.EntityClasses.Employee;
import com.EntityClasses.Staff;
import com.common.ConfirmDialog;
import com.common.SessionListener;
import com.employeemanagement.models.SearchEmployeeModel;
import com.employeemanagement.models.StaffID;
import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXTextField;
import com.main.Main;
import db.UserSession;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.MaskerPane;
import org.hibernate.Query;
import org.hibernate.Session;
import com.main.controllers.MainScreenController;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by gayashan on 8/13/2017.
 */
public class SearchEmpoyeeController implements Initializable, SessionListener {


    @FXML
    private JFXButton genarateStaffIdBtn;

    @FXML
    private JFXTextField searchtxt;

    @FXML
    private TreeTableView<Employee> staffTable;

    @FXML
    private TreeTableColumn<Employee, Number> colId;

    @FXML
    private TreeTableColumn<Employee, String> colName;

    @FXML
    private MaskerPane maskerPane;


    private List<TreeItem<Employee>> staffList;
    private Session session;

    private MainScreenController mainController;


    @FXML
    void setSelectionDetails(MouseEvent event) {

        Employee staff = staffTable.getSelectionModel().getSelectedItem().getValue();
//        fullName.setText(staff.getName());
//        nic.setText(staff.getNic());
//        contactNumber.setText(staff.getContactNumber());
//        email.setText(staff.getEmail());
//        jobRole.setText(staff.getJobRole());

        mainController.setEmployee(staff);


    }

    @FXML
    void viewProfile(ActionEvent event) {

        ScreenController.changeScreen(MyScreens.VIEW_EMPLOYEE_SCREEN, mainController.getContent(), mainController);
//        System.out.println(mainController.getEmployee().getName());

    }

    @FXML
    void searchEmployees(KeyEvent event) {

        String name = searchtxt.getText();

        session.beginTransaction();

        Query query = session.createQuery("from Employee s where name like :keyword ");
        query.setParameter("keyword", "%" + name + "%");

        List<Employee> staffMemberList = query.list();

        session.getTransaction().commit();

        staffList.clear();
        for (Employee s : staffMemberList) {
            staffList.add(new TreeItem<>(s));
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
            Platform.runLater(() -> initDB());

        }).start();


    }

    private void initDB() {

        List<Employee> staffMemberList = SearchEmployeeModel.getEmployees();

        staffList.clear();
        // staffTable
        for (Employee s : staffMemberList) {
            staffList.add(new TreeItem<>(s));
        }

        staffTable.getRoot().getChildren().clear();
        staffTable.getRoot().getChildren().addAll(staffList);
    }

    private void initTable() {
        colId.setCellValueFactory(param -> param.getValue().getValue().employeeidProperty());
        colName.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        Employee staff = new Staff();
        staff.setEmployeeid(0);
        staff.setName("Name");

        TreeItem<Employee> root = new TreeItem<>(staff);

        staffTable.setRoot(root);
        staffTable.setShowRoot(false);

    }

    @FXML
    void calculatePayroll(ActionEvent event) {

//        ScreenController.changeScreen(MyScreens.VIEW_EMPLOYEE_SCREEN, mainController.getContent(), mainController);

        ScreenController.changeScreen(MyScreens.PAYROLL_SCREEN, mainController.getContent(), mainController);

    }

    @FXML
    void deleteProfile(ActionEvent event) {
        Employee emp = staffTable.getSelectionModel().getSelectedItem().getValue();

        SearchEmployeeModel.deleteEmployee(emp);
        initDB();

    }

    @FXML
    void genarateStaffID(ActionEvent event) {
        maskerPane.setVisible(true);

        java.util.Date d = new java.util.Date();

        Employee emp = staffTable.getSelectionModel().getSelectedItem().getValue();
        StaffID id = new StaffID(emp);

        //        String path = "c:/Reports/convertPPTSlidesIntoPDFImages.pdf";
//
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(genarateStaffIdBtn.getScene().getWindow());
        id.saveStraffIdImages(file);
        maskerPane.setVisible(false);


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

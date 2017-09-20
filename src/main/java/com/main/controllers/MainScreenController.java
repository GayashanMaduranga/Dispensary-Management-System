package com.main.controllers;

import com.EntityClasses.Employee;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable,SessionListener,ControlledScreen {

    @FXML
    private GridPane leftPane;

    @FXML
    private TreeView<String> navigationTree;

    @FXML
    private JFXButton addEmployeeBtn;

    @FXML
    private JFXButton updateEmployeeBtn;

    @FXML
    private JFXButton attendenceBtn;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton payrollBtn;

    @FXML
    private JFXButton reportsBtn;

    @FXML
    private GridPane topPane;

    @FXML
    private HBox must;

    @FXML
    private StackPane content;

    private ScreenController controller;


    private MainScreenController mainScreenController;

    public String test = "Hello";

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public StackPane getContent() {
        return content;
    }

    public void setContent(StackPane content) {
        this.content = content;
    }

    private JFXButton selectedBtn;


    @FXML
    void changeScene(MouseEvent event) {

        selectedBtn.setDisable(false);
        leftPane.requestFocus();
        selectedBtn = (JFXButton) event.getSource();
        selectedBtn.setDisable(true);

        switch (selectedBtn.getId()) {
            case "dashBoardBtn":
                ScreenController.changeScreen(MyScreens.DASHBOARD_SCREEN,content,this);
                break;
            case "addEmployeeBtn":
                ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN,content,this);
                break;

            case "updateEmployeeBtn":
                ScreenController.changeScreen(MyScreens.SEARCH_EMPLOYEE_SCREEN,content,this);

                break;
            case "attendenceBtn":
               // ScreenController.changeScreen(MyScreens.ATTENDENCE_SCREEN,content,this);

                break;
            case "payrollBtn":
                //ScreenController.changeScreen(MyScreens.PAYROLL_SCREEN,content,this);
                break;
        }


    }

    @FXML
    void logout(ActionEvent event) {
        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) logoutBtn.getScene().getWindow();
            s.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        selectedBtn = dashBoardBtn;
//        selectedBtn.setDisable(true);
//        ScreenController.changeScreen(MyScreens.DASHBOARD_SCREEN,content);

        TreeItem<String> root, employeeTree, financeTree, patientTree, pharmacyTree, laboratoryTree, supplierTree, appointmentTree, labInventoryTree ;

        root = new TreeItem<String>("");
        root.setExpanded(true);

        employeeTree = new TreeItem<String>("Employees");
        patientTree = new TreeItem<String>("Patients");
        pharmacyTree = new TreeItem<String>("Pharmacy");
        laboratoryTree = new TreeItem<String>("Laboratory");
        supplierTree = new TreeItem<String>("Suppliers");
        appointmentTree = new TreeItem<String>("Appointments");
        labInventoryTree = new TreeItem<String>("Equipment");
        financeTree = new TreeItem<String>("Finances");

        FontAwesomeIconView ambulanceIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);

        employeeTree.getChildren().addAll(
                new TreeItem<String>("Add Employee", ambulanceIcon),
                new TreeItem<String>("Attendance"),
                new TreeItem<String>("Loans")
        );

        patientTree.getChildren().addAll(
                new TreeItem<String>("Add Patient"),
                new TreeItem<String>("Patient Summary"),
                new TreeItem<String>("Doctor Portal")
        );

        pharmacyTree.getChildren().addAll(
                new TreeItem<String>("Add Pharmacy Item"),
                new TreeItem<String>("Billing"),
                new TreeItem<String>("Dashboard")
        );

        laboratoryTree.getChildren().addAll(
                new TreeItem<String>("Add Test"),
                new TreeItem<String>("Enter Results"),
                new TreeItem<String>("order Test")
        );

        supplierTree.getChildren().addAll(
                new TreeItem<String>("Purchase"),
                new TreeItem<String>("Suppliers"),
                new TreeItem<String>("Warehouse")
        );

        appointmentTree.getChildren().addAll(
                new TreeItem<String>("Schedule Appointment"),
                new TreeItem<String>("Update Appointment"),
                new TreeItem<String>("Doctors")
        );

        labInventoryTree.getChildren().addAll(
                new TreeItem<String>("Add Equipment"),
                new TreeItem<String>("Machines")
        );

        financeTree.getChildren().addAll(
                new TreeItem<String>("Expenses"),
                new TreeItem<String>("Revenue"),
                new TreeItem<String>("Final Balance")
        );

        root.getChildren().addAll(employeeTree, patientTree, pharmacyTree, laboratoryTree, supplierTree, appointmentTree, labInventoryTree, financeTree);

        navigationTree.setRoot(root);
        navigationTree.setShowRoot(false);

    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent){

        TreeItem<String> item = new TreeItem<String>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);

        return item;
    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {
                mainScreenController = this;
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @FXML
    void showHome(ActionEvent event) {
        ScreenController.changeScreen(controller, MyScreens.MAIN_SCREEN, MainScreens.HOME_SCREEN);
    }

}

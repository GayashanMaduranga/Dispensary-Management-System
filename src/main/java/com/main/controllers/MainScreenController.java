package com.main.controllers;

import com.EntityClasses.Employee;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.JFXButton;
import com.labinventory.controlers.LabInventoryScreens;
import com.main.Main;
import com.main.models.LoginModel;
import com.patientmanagement.controllers.PatientScreens;
import com.suppliermanagement.controllers.SupplierScreens;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unchecked")
public class MainScreenController implements Initializable,SessionListener,ControlledScreen {

    @FXML
    private Label userLbl;

    @FXML
    private GridPane leftPane;

    @FXML
    private TreeView<String> navigationTree;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private StackPane content;

    private ScreenController controller;

    private Session session;

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
    void logout() {
        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) logoutBtn.getScene().getWindow();
            s.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(LoginModel.getUser());

        //Icons for TreeItems
        FontAwesomeIconView employeeIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        employeeIcon.setSize("20px");
        FontAwesomeIconView patientIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        patientIcon.setSize("20px");
        FontAwesomeIconView pharmacyIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        pharmacyIcon.setSize("20px");
        FontAwesomeIconView laboratoryIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        laboratoryIcon.setSize("20px");
        FontAwesomeIconView supplierIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        supplierIcon.setSize("20px");
        FontAwesomeIconView appointmentIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        appointmentIcon.setSize("20px");
        FontAwesomeIconView equipmentIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        equipmentIcon.setSize("20px");
        FontAwesomeIconView financeIcon = new FontAwesomeIconView(FontAwesomeIcon.AMBULANCE);
        financeIcon.setSize("20px");

        //list of TreeItems for navigation panel
        TreeItem<String> root, employeeTree, financeTree, patientTree, pharmacyTree, laboratoryTree, supplierTree, appointmentTree, labInventoryTree ;

        //root TreeItem to contain all other TreeItems
        root = new TreeItem<>("");
        root.setExpanded(true);

        //Main Branches
        employeeTree = new TreeItem<>("Employees", employeeIcon);
        patientTree = new TreeItem<>("Patients", patientIcon);
        pharmacyTree = new TreeItem<>("Pharmacy", pharmacyIcon);
        laboratoryTree = new TreeItem<>("Laboratory", laboratoryIcon);
        supplierTree = new TreeItem<>("Suppliers", supplierIcon);
        appointmentTree = new TreeItem<>("Appointments", appointmentIcon);
        labInventoryTree = new TreeItem<>("Equipment", equipmentIcon);
        financeTree = new TreeItem<>("Finances", financeIcon);

        //adding SubBranches

        employeeTree.getChildren().addAll(
                new TreeItem<>("Add Employee"),
                new TreeItem<>("Attendance"),
                new TreeItem<>("Loans")
        );

        patientTree.getChildren().addAll(
                new TreeItem<>("Add Patient"),
                new TreeItem<>("Patient Summary"),
                new TreeItem<>("Doctor Portal")
        );

        pharmacyTree.getChildren().addAll(
                new TreeItem<>("Add Pharmacy Item"),
                new TreeItem<>("Billing"),
                new TreeItem<>("Dashboard")
        );

        laboratoryTree.getChildren().addAll(
                new TreeItem<>("Add Test"),
                new TreeItem<>("Enter Results"),
                new TreeItem<>("order Test")
        );

        supplierTree.getChildren().addAll(
                new TreeItem<>("Inventory"),
                new TreeItem<>("Stock Control"),
                new TreeItem<>("Suppliers")
        );

        appointmentTree.getChildren().addAll(
                new TreeItem<>("Schedule Appointment"),
                new TreeItem<>("Update Appointment"),
                new TreeItem<>("Doctors")
        );

        labInventoryTree.getChildren().addAll(
                new TreeItem<>("Lab Equipment"),
                new TreeItem<>("Machines")
        );

        financeTree.getChildren().addAll(
                new TreeItem<>("Expenses"),
                new TreeItem<>("Revenue"),
                new TreeItem<>("Final Balance")
        );

        //adding main branches to root branch

        root.getChildren().addAll(employeeTree, patientTree, pharmacyTree, laboratoryTree, supplierTree, appointmentTree, labInventoryTree, financeTree);

        //setting up TreeView
        navigationTree.setRoot(root);
        navigationTree.setShowRoot(false);

        //Listener for change in selected Item
        navigationTree.getSelectionModel().selectedItemProperty().addListener((treeItem, OldValue, NewValue) -> changeScene(NewValue.getValue()));

    }

    private void changeScene(String ButtonName) {

        switch (ButtonName) {
            case "Attendance":
                ScreenController.changeScreen(MyScreens.DASHBOARD_SCREEN,content,this);
                break;
            case "Add Employee":
                ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN,content,this);
                break;

            case "Lab Equipment":
                ScreenController.changeScreen(LabInventoryScreens.LAB_EQUIPMENT_SCREEN,content,this);
                break;

            case "payrollBtn":
                //ScreenController.changeScreen(MyScreens.PAYROLL_SCREEN,content,this);
                break;

            case "Doctor Portal":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(PatientScreens.PATIENT_SUMMARY_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Patient Summary":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(PatientScreens.DASHBOARD_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Inventory":
                ScreenController.changeScreen(SupplierScreens.INVENTORY_VIEW,content, this);
                break;
            case "Stock Control":
                ScreenController.changeScreen(SupplierScreens.STOCK_CONTROL_VIEW,content, this);
                break;
            case "Suppliers":
                ScreenController.changeScreen(SupplierScreens.SUPPLIER_SCREEN_VIEW,content, this);
                break;
        }


    }

    @Override
    public void setSession(Session session) {
        this.session = session;
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
    void showHome() {
        ScreenController.changeScreen(controller, MyScreens.MAIN_SCREEN, MainScreens.HOME_SCREEN);
    }


}

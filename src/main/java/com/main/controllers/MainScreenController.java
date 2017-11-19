package com.main.controllers;

import com.EntityClasses.Employee;
import com.Laboratory.controllers.LabScreens;
import com.PharmacyMgt.Controllers.PharmacyScreens;
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
    private TreeView<String> navigationTree;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private StackPane content;

    private ScreenController controller;

    private MainScreenController mainScreenController;

    public String test = "Hello";

    private Employee employee;

    private Session session;

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

    public Session getSession() {
        return session;
    }

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
        FontAwesomeIconView employeeIcon = new FontAwesomeIconView(FontAwesomeIcon.USER);
        employeeIcon.setSize("20px");
        FontAwesomeIconView patientIcon = new FontAwesomeIconView(FontAwesomeIcon.USER_MD);
        patientIcon.setSize("20px");
        FontAwesomeIconView pharmacyIcon = new FontAwesomeIconView(FontAwesomeIcon.HEART);
        pharmacyIcon.setSize("20px");
        FontAwesomeIconView laboratoryIcon = new FontAwesomeIconView(FontAwesomeIcon.FLASK);
        laboratoryIcon.setSize("20px");
        FontAwesomeIconView supplierIcon = new FontAwesomeIconView(FontAwesomeIcon.DROPBOX);
        supplierIcon.setSize("20px");
        FontAwesomeIconView appointmentIcon = new FontAwesomeIconView(FontAwesomeIcon.LIST_ALT);
        appointmentIcon.setSize("20px");
        FontAwesomeIconView equipmentIcon = new FontAwesomeIconView(FontAwesomeIcon.COGS);
        equipmentIcon.setSize("20px");
        FontAwesomeIconView adminIcon = new FontAwesomeIconView(FontAwesomeIcon.USER_SECRET);
        adminIcon.setSize("20px");

        //list of TreeItems for navigation panel
        TreeItem<String> root, employeeTree, patientTree, pharmacyTree, laboratoryTree, supplierTree, appointmentTree, labInventoryTree, adminTree ;

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
        adminTree = new TreeItem<>("Administration", adminIcon);

        //adding SubBranches
        employeeTree.getChildren().addAll(
                new TreeItem<>("Add Employee"),
                new TreeItem<>("Search Employee"),
                new TreeItem<>("Attendance")
        );

        patientTree.getChildren().addAll(
                new TreeItem<>("Patient Summary"),
                new TreeItem<>("Doctor Portal")
        );

        pharmacyTree.getChildren().addAll(
                new TreeItem<>("Pharmacy Items"),
                new TreeItem<>("Billing"),
                new TreeItem<>("Dashboard")
        );

        laboratoryTree.getChildren().addAll(

                new TreeItem<>("Order Test"),
                new TreeItem<>("Enter Results"),
                new TreeItem<>("Add Test"),
                new TreeItem<>("Add Reference Values"),
                new TreeItem<>("Fill Results"),
                new TreeItem<>("Statistics")

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
                new TreeItem<>("Machines"),
                new TreeItem<>("Maintenance")
        );

        adminTree.getChildren().addAll(
                new TreeItem<>("Manage Users")
        );

        //adding main branches to root branch

        root.getChildren().addAll(employeeTree, patientTree, pharmacyTree, laboratoryTree, supplierTree, appointmentTree, labInventoryTree, adminTree);

        //setting up TreeView
        navigationTree.setRoot(root);
        navigationTree.setShowRoot(false);

        //Listener for change in selected Item
        navigationTree.getSelectionModel().selectedItemProperty().addListener((treeItem, OldValue, NewValue) -> changeScene(NewValue.getValue()));

    }

    private void changeScene(String ButtonName) {

        switch (ButtonName) {
            case "Search Employee":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(MyScreens.SEARCH_EMPLOYEE_SCREEN,content,this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Attendance":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(MyScreens.ATTENDENCE_SCREEN,content,this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Add Employee":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN,content,this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Lab Equipment":
                if (LoginModel.getAccessLevel() == 4 || LoginModel.getAccessLevel() == 1) {
                    ScreenController.changeScreen(LabInventoryScreens.LAB_EQUIPMENT_SCREEN,content,this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Machines":
                if (LoginModel.getAccessLevel() == 4 || LoginModel.getAccessLevel() == 1) {
                    ScreenController.changeScreen(LabInventoryScreens.LAB_MACHINE_SCREEN,content,this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Maintenance":
                if (LoginModel.getAccessLevel() == 4 || LoginModel.getAccessLevel() == 1) {
                    ScreenController.changeScreen(LabInventoryScreens.LAB_MAINTENAANCE_SCREEN,content,this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
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
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(SupplierScreens.INVENTORY_VIEW,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Stock Control":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(SupplierScreens.STOCK_CONTROL_VIEW,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Suppliers":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(SupplierScreens.SUPPLIER_SCREEN_VIEW,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Billing":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(PharmacyScreens.PHARMACY_BILLING_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Pharmacy Items":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(PharmacyScreens.PHARMACY_STOCK_SCREEN2,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;
            case "Manage Users":
                if (LoginModel.getAccessLevel() <= 1) {
                    ScreenController.changeScreen(MainScreens.ADMIN_PORTAL_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Add Test":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.ADDTEST_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Add Reference Values":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.REFER_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Enter Results":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.ENTERRESULTS_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Select Test":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.SELECT_TEST_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "DataBase":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.INPUTRESULT_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Statistics":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.EXTRA_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;

            case "Order Test":
                if (LoginModel.getAccessLevel() <= 2) {
                    ScreenController.changeScreen(LabScreens.ORDERTEST_SCREEN,content, this);
                } else {
                    ScreenController.changeScreen(MainScreens.NO_ACCESS_SCREEN,content, this);
                }
                break;


//            new TreeItem<>("Order Test"),
//                    new TreeItem<>("Select Test"),
//                    new TreeItem<>("Enter Results"),
//                    new TreeItem<>("Add Test"),
//                    new TreeItem<>("Add Reference Values"),
//                    new TreeItem<>("DataBase"),
//                    new TreeItem<>("Statistics")
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

}

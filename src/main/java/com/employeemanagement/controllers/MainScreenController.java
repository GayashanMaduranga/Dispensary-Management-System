package com.employeemanagement.controllers;

import com.EntityClasses.Employee;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.financemanagement.controllers.MainController.MainController;
import com.main.Main;
import com.main.controllers.MainScreens;
import com.patientmanagement.controllers.PatientScreens;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable,SessionListener,ControlledScreen {

    @FXML
    private GridPane leftPane;

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
//    private JFXButton btn2;

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
            case "reportsBtn":
//                ScreenController.changeScreen(MyScreens.R,content);
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

        selectedBtn = dashBoardBtn;
        selectedBtn.setDisable(true);


        ScreenController.changeScreen(MyScreens.DASHBOARD_SCREEN,content);


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

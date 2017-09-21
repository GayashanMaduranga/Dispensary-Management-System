package com.patientmanagement.controllers;


import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.employeemanagement.controllers.MainScreenController;
import com.main.Main;
import com.main.models.LoginModel;
import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class DoctorsAssistantController implements Initializable,SessionListener {

    ScreenController controller;

    private Session session;
    private MainScreenController mainScreenController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(LoginModel.getUser());
    }

    @FXML
    private Label userLbl;

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

        switch(((JFXButton)event.getSource()).getId()){

            case "sidebarRegisterBtn":
                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.REGISTER_PATIENT_SCREEN);
                break;
//            case "titlebtn":
//                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.PATIENT_SUMMARY_SCREEN);
//                break;
            case "sidebarPrescriptionBtn":
                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.MEDICATION_SCREEN);
                break;
            case "sidebarBillBtn":
                ScreenController.changeScreen(controller, PatientScreens.PATIENT_SUMMARY_SCREEN, PatientScreens.BILL_SCREEN);
                break;
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

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;


    }
}

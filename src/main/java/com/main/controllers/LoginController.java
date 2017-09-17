package com.main.controllers;


import com.EntityClasses.User;
import com.appointmentscheduling.controllers.AppointmentScreens;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import com.common.AlertDialog;
import com.common.ConfirmDialog;
import com.main.Main;
import com.main.models.LoginModel;
import com.patientmanagement.controllers.PatientScreens;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements ControlledScreen, Initializable{

    private Session session;

    ScreenController mainContainer = new ScreenController();

    @Override
    public void setScreenParent(ScreenController screenParent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = Main.getSessionFactory().openSession();

    }

    @FXML
    public Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void closeWindow(ActionEvent actionEvent){
        Stage s = (Stage) txtUsername.getScene().getWindow();
        s.close();
        System.exit(0);
    }

    @FXML
    public void login(ActionEvent actionEvent){


        session.beginTransaction();
        Query query = session.createQuery("from User where username ='"+txtUsername.getText()+"'");
        List<User> users = query.list();
        session.getTransaction().commit();

        if(users.isEmpty()){
            AlertDialog.show("", "No such user");
        }
        else{

            if(users.get(0).getPassword().equals(txtPassword.getText())){
                LoginModel.setUser(users.get(0).getUsername());
                LoginModel.setAccessLevel(users.get(0).getAccessLevel());
                makeStage(users.get(0).getAccessLevel());
//                session.close();
                Stage s = (Stage) txtUsername.getScene().getWindow();
                s.close();
            }

            else{
                AlertDialog.show("", "Password Incorrect");
            }
        }

    }

    private void makeStage(int AccessLevel){
        try {

            Stage primaryStage = new Stage();

            primaryStage.setOnCloseRequest(event -> {
                event.consume(); // stop the close event from happening ~ Damsith
                if(ConfirmDialog.show("", "Are you sure?")){
                    Main.createLogin(new Stage());
                    primaryStage.close();
                }
            }); // code to be run on stage close ~ Damsith

            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.setMinHeight(750.0);
            primaryStage.setMinWidth(1380.0);

            mainContainer.loadScreen(MainScreens.HOME_SCREEN.getId(), MainScreens.HOME_SCREEN.getPath());
            mainContainer.setScreen(MainScreens.HOME_SCREEN.getId());
            Parent layout = mainContainer.getScreen(MainScreens.HOME_SCREEN.getId()).getParent();
            primaryStage.setScene(new Scene(layout));

//            if(AccessLevel == 1){
//
//                mainContainer.loadScreen(PatientScreens.PATIENT_SUMMARY_SCREEN.getId(), PatientScreens.PATIENT_SUMMARY_SCREEN.getPath());
//                mainContainer.setScreen(PatientScreens.PATIENT_SUMMARY_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(PatientScreens.PATIENT_SUMMARY_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));
//
//            }
//            else if(AccessLevel == 2){
//
//                mainContainer.loadScreen(SupplierScreens.DASHBOARD_SCREEN.getId(), SupplierScreens.DASHBOARD_SCREEN.getPath());
//                mainContainer.setScreen(SupplierScreens.DASHBOARD_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(SupplierScreens.DASHBOARD_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));
//
//            }
//
//            else if(AccessLevel == 3){
//
//                mainContainer.loadScreen(MyScreens.DASHBOARD_SCREEN.getId(), MyScreens.DASHBOARD_SCREEN.getPath());
//                mainContainer.setScreen(MyScreens.DASHBOARD_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(MyScreens.DASHBOARD_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));
//
//            }
//
//            else if(AccessLevel == 4){
//
//                mainContainer.loadScreen(SupplierScreens.PURCHASE_SCREEN.getId(), SupplierScreens.PURCHASE_SCREEN.getPath());
//                mainContainer.setScreen(SupplierScreens.PURCHASE_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(SupplierScreens.PURCHASE_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));
//            }
//
//            else if(AccessLevel == 5){
//
//                mainContainer.loadScreen(AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getId(), AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getPath());
//                mainContainer.setScreen(AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));
//            }
//
//            else if(AccessLevel == 6){
//
//                mainContainer.loadScreen(PatientScreens.DASHBOARD_SCREEN.getId(), PatientScreens.DASHBOARD_SCREEN.getPath());
//                mainContainer.setScreen(PatientScreens.DASHBOARD_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(PatientScreens.DASHBOARD_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));
//            }

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

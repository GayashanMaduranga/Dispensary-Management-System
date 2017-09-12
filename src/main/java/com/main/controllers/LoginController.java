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
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements ControlledScreen, Initializable{

    private Session session;
    public LoginModel loginModel = new LoginModel();
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
                makeStage(users.get(0).getAccessLevel());
                session.close();
                Stage s = (Stage) txtUsername.getScene().getWindow();
                s.close();
            }

            else{
                AlertDialog.show("", "Password Incorrect");
            }
        }
//        ResultSet rs = loginModel.getValidatedUser(txtUsername.getText(), txtPassword.getText());
//
//        try {
//            if(rs.next()){
//
//                makeStage(rs.getInt(3));
//                Stage s = (Stage) txtUsername.getScene().getWindow();
//                s.close();
//
//            }
//            else
//                AlertDialog.show("", "Wrong Credentials");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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
            primaryStage.setMinHeight(715.0);
            primaryStage.setMinWidth(1299.0);


            if(AccessLevel == 1){

                mainContainer.loadScreen(PatientScreens.DASHBOARD_SCREEN.getId(), PatientScreens.DASHBOARD_SCREEN.getPath());
                mainContainer.setScreen(PatientScreens.DASHBOARD_SCREEN.getId());
                Parent layout = mainContainer.getScreen(PatientScreens.DASHBOARD_SCREEN.getId()).getParent();
                primaryStage.setScene(new Scene(layout));

            }
            else if(AccessLevel == 2){

//                mainContainer.loadScreen(PatientScreens.REGISTER_PATIENTS_SCREEN.getId(), PatientScreens.REGISTER_PATIENTS_SCREEN.getPath());
//                mainContainer.setScreen(PatientScreens.REGISTER_PATIENTS_SCREEN.getId());
//                Parent layout = mainContainer.getScreen(PatientScreens.REGISTER_PATIENTS_SCREEN.getId()).getParent();
//                primaryStage.setScene(new Scene(layout));

            }

            else if(AccessLevel == 3){

                mainContainer.loadScreen(MyScreens.DASHBOARD_SCREEN.getId(), MyScreens.DASHBOARD_SCREEN.getPath());
                mainContainer.setScreen(MyScreens.DASHBOARD_SCREEN.getId());
                Parent layout = mainContainer.getScreen(MyScreens.DASHBOARD_SCREEN.getId()).getParent();
                primaryStage.setScene(new Scene(layout));

            }

            else if(AccessLevel == 4){

                mainContainer.loadScreen(SupplierScreens.PURCHASE_SCREEN.getId(), SupplierScreens.PURCHASE_SCREEN.getPath());
                mainContainer.setScreen(SupplierScreens.PURCHASE_SCREEN.getId());
                Parent layout = mainContainer.getScreen(SupplierScreens.PURCHASE_SCREEN.getId()).getParent();
                primaryStage.setScene(new Scene(layout));
            }

            else if(AccessLevel == 5){

                mainContainer.loadScreen(AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getId(), AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getPath());
                mainContainer.setScreen(AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getId());
                Parent layout = mainContainer.getScreen(AppointmentScreens.VIEW_APPOINTMENTS_SCREEN.getId()).getParent();
                primaryStage.setScene(new Scene(layout));
            }

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

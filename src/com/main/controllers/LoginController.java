package com.main.controllers;


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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements ControlledScreen{

    private LoginModel loginModel = new LoginModel();
    ScreenController mainContainer = new ScreenController();

    @Override
    public void setScreenParent(ScreenController screenParent) {
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

        ResultSet rs = loginModel.getValidatedUser(txtUsername.getText(), txtPassword.getText());

        try {
            if(rs.next()){

                makeStage(rs.getInt(3));
                Stage s = (Stage) txtUsername.getScene().getWindow();
                s.close();

            }
            else
                AlertDialog.show("", "Wrong Credentials");

        } catch (SQLException e) {
            e.printStackTrace();
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



            if(AccessLevel == 1){

                mainContainer.loadScreen(PatientScreens.VIEW_PATIENTS_SCREEN.getId(), PatientScreens.VIEW_PATIENTS_SCREEN.getPath());
                mainContainer.setScreen(PatientScreens.VIEW_PATIENTS_SCREEN.getId());
                Parent layout = mainContainer.getScreen(PatientScreens.VIEW_PATIENTS_SCREEN.getId()).getParent();
                primaryStage.setScene(new Scene(layout));

            }
            else if(AccessLevel == 2){

                Parent layout = FXMLLoader.load(getClass().getResource("/com/patientmanagement/views/Doctor.fxml"));
                primaryStage.setTitle("Title");
                primaryStage.setScene(new Scene(layout));

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

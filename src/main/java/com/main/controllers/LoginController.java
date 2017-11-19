package com.main.controllers;


import com.EntityClasses.User;
import com.appointmentscheduling.controllers.AppointmentScreens;
import com.common.*;
import com.employeemanagement.controllers.MyScreens;
import com.main.Main;
import com.main.models.LoginModel;
import com.patientmanagement.controllers.PatientScreens;
import com.suppliermanagement.controllers.SupplierScreens;
import db.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class LoginController implements ControlledScreen, Initializable,SessionListener{

    private Session session;

    ScreenController mainContainer;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        this.mainContainer = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



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


            session = UserSession.getSession();
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
                    makeStage();
                    Stage s = (Stage) txtUsername.getScene().getWindow();
                    s.close();
                }

                else{
                    AlertDialog.show("", "Password Incorrect");
                }
            }

    }

    private void makeStage(){
        try {

            mainContainer = new ScreenController();

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
            Parent layout = FXMLLoader.load(getClass().getResource(MainScreens.HOME_SCREEN.getPath()));
            primaryStage.setScene(new Scene(layout));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

    }


}

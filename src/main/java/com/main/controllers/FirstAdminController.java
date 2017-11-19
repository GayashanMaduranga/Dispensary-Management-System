package com.main.controllers;


import com.EntityClasses.User;
import com.common.*;
import com.main.Main;
import db.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstAdminController implements ControlledScreen, Initializable,SessionListener{

    private Session session;

    ScreenController mainContainer;

    @FXML
    private TextField txtAdminName;

    @FXML
    private PasswordField txtAdminPass;

    @FXML
    private PasswordField txtAdminPass2;

    @FXML
    private Button createBtn;

    @FXML
    private Button cancelBtn;


    @Override
    public void setScreenParent(ScreenController screenParent) {
        this.mainContainer = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    void logout(ActionEvent event) {

        if (ConfirmDialog.show("", "Are you sure you want to cancel?")) {
            Stage s = (Stage) cancelBtn.getScene().getWindow();
            s.close();
            System.exit(0);
        }
    }

    private boolean passwordsMatch(){

        if(txtAdminPass.getText().equals(txtAdminPass2.getText())){
            return true;
        }else{
            return false;
        }

    }

    @FXML
    void create(ActionEvent event){

        if (passwordsMatch()) {
            User u = new User();
            u.setAccessLevel(1);
            u.setUsername(txtAdminName.getText());
            u.setPassword(txtAdminPass.getText());

            session = UserSession.getSession();
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
            session.clear();

            Main.createLogin(new Stage());
            Stage s = (Stage) cancelBtn.getScene().getWindow();
            s.close();
        } else {
            AlertDialog.show("", "The passwords do not match");
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

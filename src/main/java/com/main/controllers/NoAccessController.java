package com.main.controllers;


import com.EntityClasses.User;
import com.common.*;
import com.main.Main;
import com.main.models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.List;
import java.util.ResourceBundle;

public class NoAccessController implements ControlledScreen, Initializable,SessionListener{

    private Session session;

    ScreenController mainContainer;

    @FXML
    private Button logoutBtn;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        this.mainContainer = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


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
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

    }


}

package com.main.controllers;


import com.main.AlertDialog;
import com.main.ConfirmDialog;
import com.main.Main;
import com.main.models.LoginModel;
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

public class LoginController {

    private LoginModel loginModel = new LoginModel();

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
                if(ConfirmDialog.show("", "Are you sure?")) //if confirm dialog returns true, close ~ Damsith
                    primaryStage.close();
            }); // code to be run on stage close ~ Damsith

            if(AccessLevel == 1){

                Parent layout = FXMLLoader.load(getClass().getResource("/com/patientmanagement/views/DoctorsAssistant.fxml"));
                primaryStage.setTitle("Title");
                primaryStage.setScene(new Scene(layout));

            }
            else if(AccessLevel == 2){

                Parent layout = FXMLLoader.load(getClass().getResource("/com/patientmanagement/views/Doctor.fxml"));
                primaryStage.setTitle("Title");
                primaryStage.setScene(new Scene(layout));

            }

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

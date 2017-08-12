package com.main.controllers;


import com.AlertDialog;
import com.ConfirmDialog;
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

public class LoginController {


    private Stage s;

    @FXML
    public Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void login(ActionEvent actionEvent){

        if (txtUsername.getText().equals("user") && txtPassword.getText().equals("pass")) {

            makeStage();
            s = (Stage)txtUsername.getScene().getWindow();
            s.close();

        } else {
            AlertDialog.show("", "wrong credentials");
        } // Password validation has to be refined ~ Damsith
    }

    private void makeStage(){
        try {

            Stage primaryStage = new Stage();

            primaryStage.setOnCloseRequest(event -> {
                event.consume(); // stop the close event from happening ~ Damsith
                if(ConfirmDialog.show("", "Are you sure?")) //if confirm dialog returns true, close ~ Damsith
                    primaryStage.close();
            }); // code to be run on stage close ~ Damsith

            Parent layout = FXMLLoader.load(getClass().getResource("/com/patientmanagement/views/DoctorsAssistant.fxml"));
            primaryStage.setTitle("Title");
            primaryStage.setScene(new Scene(layout));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

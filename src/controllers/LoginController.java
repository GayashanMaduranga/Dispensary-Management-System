package controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Stage s;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void login(){

        if (txtUsername.getText().equals("user") && txtPassword.getText().equals("pass")) {

            makeStage();
            s = (Stage)txtUsername.getScene().getWindow();
            s.close();

        } else {
            System.out.println("NOOOOO!");
        }
    }

    private void makeStage(){
        try {
            Stage primaryStage = new Stage();
//            primaryStage.setOnCloseRequest(event -> {
//                event.consume();
//                primaryStage.close();
//            });
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

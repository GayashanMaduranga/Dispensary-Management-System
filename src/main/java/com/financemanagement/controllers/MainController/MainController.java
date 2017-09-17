package com.financemanagement.controllers.MainController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kavindu on 9/16/2017.
 */
public class MainController implements Initializable {

    @FXML
    private Label lblMessage;

    public MainController(Label lblMessage) {
        this.lblMessage = lblMessage;
    }

    @FXML
    private TextField FinanceUNBtn;

    @FXML
    private PasswordField FinancePWBtn;

    @FXML
    private void BtnLoginAction(ActionEvent event){

        if(FinanceUNBtn.getText().equals("test") && FinancePWBtn.getText().equals("test")){
            lblMessage.setText("Welcome : " +FinanceUNBtn.getText());

        }else{

            lblMessage.setText("Username or Password is incorrect");
        }


        }






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

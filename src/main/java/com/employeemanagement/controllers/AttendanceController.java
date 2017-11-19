package com.employeemanagement.controllers;

import com.main.controllers.MainScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.hibernate.Session;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable, SessionListener {

    @FXML
    private JFXButton ariavalBtn;

    @FXML
    private JFXButton departureBtn;
    @FXML
    void setArival(ActionEvent event) {

        Stage s = (Stage) departureBtn.getScene().getWindow();

        ScannerController.isArival=true;
        if(!(Main.createFadedWindow(new Stage(), s,"/com/employeemanagement/Scanner.fxml"))){


        }else{

            System.out.println("canceled");
        }
    }

    @FXML
    void setDeparture(ActionEvent event) {

        Stage s = (Stage) departureBtn.getScene().getWindow();

        ScannerController.isArival=false;
        if(!(Main.createFadedWindow(new Stage(), s,"/com/employeemanagement/Scanner.fxml"))){



        }else{

            System.out.println("canceled");
        }

    }


    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

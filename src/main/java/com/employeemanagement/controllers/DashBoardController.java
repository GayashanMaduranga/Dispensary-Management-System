package com.employeemanagement.controllers;

import com.common.ConfirmDialog;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class DashBoardController implements Initializable,SessionListener {


    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setSession(Session session) {

    }

//    @FXML
//    void changeScene(MouseEvent event) {
//
//
//
//    }


}

package com.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.test.ControlledScreen;
import com.test.ScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class AddEmpoyeeController implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private VBox aside;
    @FXML
    private JFXButton dashBoardBtn;

    @Override
    public void setScreenParent(ScreenController screenParent) {
            controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void changeScene(MouseEvent event) {
        if(dashBoardBtn == (JFXButton)event.getSource()){
            controller.loadScreen(MyScreens.DASHBOARD_SCREEN,MyScreens.DASHBOARD_SCREEN_FXML );
            controller.setScreen(MyScreens.DASHBOARD_SCREEN);
            controller.unloadScreen(MyScreens.ADDEMPLOYEE_SCREEN);
        }
    }
}

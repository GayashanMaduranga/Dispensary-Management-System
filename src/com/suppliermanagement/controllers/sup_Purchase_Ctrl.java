package com.suppliermanagement.controllers;


import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class sup_Purchase_Ctrl implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private BorderPane rootpane;

    @FXML
    private JFXButton pur_btn;

    @FXML
    private JFXButton sup_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    @FXML
    void load_pur(ActionEvent event) {

    }

    @FXML
    void load_sup(ActionEvent event) throws IOException {

        BorderPane parent = FXMLLoader.load(getClass().getResource("supView_Suppliers.fxml"));
        rootpane.getChildren().setAll(parent);
        System.out.println("Workedddd");

    }


    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }
}

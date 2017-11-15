package com.Laboratory.controllers;


import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.controllers.MainScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import com.EntityClasses.MainTest;
import com.EntityClasses.TestField;
import db.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 9/17/2017.
 */
public class AddReferenceValueController implements Initializable,SessionListener {

    private Session session;
    private homeController MainScreenController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();

    }


    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {

        this.MainScreenController = (homeController) controller;


    }
}






















package com.employeemanagement.controllers;

import com.common.SessionListener;
import javafx.fxml.Initializable;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingScreenController implements Initializable, SessionListener {

    private MainScreenController mainScreenController;
    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController) controller;


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

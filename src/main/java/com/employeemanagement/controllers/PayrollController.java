package com.employeemanagement.controllers;

import com.common.SessionListener;
import javafx.fxml.Initializable;
import org.hibernate.Session;
import com.main.controllers.MainScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class PayrollController implements Initializable, SessionListener {


    private Session session;
    MainScreenController mainController;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {
        this.mainController = (MainScreenController) controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package com.suppliermanagement.controllers.Inventory_CTRL;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreenController;
import javafx.fxml.Initializable;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Inventory_View_CTRL implements SessionListener, Initializable {

    Session session;
    private MainScreenController mainScreenController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();
    }

    @Override
    public void setSession(Session session) {

        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;

    }
}
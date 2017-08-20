package com.appointmentscheduling.controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kalan on 8/12/2017.
 */
public class AppointmentController implements Initializable, ControlledScreen {

    private ScreenController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {

        this.controller = screenParent;
    }
}

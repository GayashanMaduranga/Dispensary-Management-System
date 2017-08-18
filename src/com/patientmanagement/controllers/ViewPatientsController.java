package com.patientmanagement.controllers;


import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damsith on 8/1/2017.
 */

public class ViewPatientsController implements Initializable,ControlledScreen {

    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

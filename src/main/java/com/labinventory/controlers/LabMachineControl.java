package com.labinventory.controlers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by chamara on 8/13/2017.
 */
public class LabMachineControl implements ControlledScreen, Initializable{


    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        this.controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }




}

package com.labinventory.controlers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by chamara on 8/13/2017.
 */
public class LabInventoryControl implements Initializable, ControlledScreen {

    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        this.controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void changeScreen(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){

            case "eqBtn":


                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_INVENTORY_SCREENS, LabInventoryScreens.LAB_EQUIPMENT_SCREEN);

                break;

            case "mtnBtn":


                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_INVENTORY_SCREENS, LabInventoryScreens.LAB_MACHINE_SCREEN);

                break;

            case "sidebarRegisterBtn1111":


                //ScreenController.changeScreen(controller, LabInventoryScreens.LAB_INVENTORY_SCREENS, LabInventoryScreens.LAB_INVENTORY_SCREENS);

                break;





        }
    }

}

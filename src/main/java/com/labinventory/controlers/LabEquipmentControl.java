package com.labinventory.controlers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by chamara on 8/13/2017.
 */
public class LabEquipmentControl implements Initializable,ControlledScreen{



    ScreenController controller;

    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton equipmentBtn;

    @FXML
    private JFXButton ManitainBtn;

    @FXML
    private JFXButton ReportBtn;

    @FXML
    void changeScene(MouseEvent event) {


        switch (((JFXButton) event.getSource()).getId()){
//            case "equipmentBtn":
//                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_INVENTORY_SCREENS, LabInventoryScreens.);
//                break;
            case "ManitainBtn":
                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_EQUIPMENT_SCREEN, LabInventoryScreens.LAB_MACHINE_SCREEN);
                break;
            case "ReportBtn":
                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_EQUIPMENT_SCREEN, LabInventoryScreens.LAB_INVENTORY_SCREENS);
                break;

        }

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

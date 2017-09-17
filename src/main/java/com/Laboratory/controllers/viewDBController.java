package com.Laboratory.controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 8/19/2017.
 */
public class viewDBController implements Initializable,ControlledScreen {

    ScreenController controller;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton addEmployeeBtn;



    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    @FXML
//    void changePanel(MouseEvent event) {
//        switch (((JFXButton) event.getSource()).getId()){
//            case "dashboardBT":
//                ScreenController.changeScreen(controller, screens.VIEWDB_SCREEN, screens.DASHBOARD_SCREEN);
//
//                break;
//            case "ordertestBT":
//                ScreenController.changeScreen(controller, screens.VIEWDB_SCREEN, screens.ORDERTEST_SCREEN);
//                break;
//            case "enter_resultBT":
//                ScreenController.changeScreen(controller, screens.VIEWDB_SCREEN, screens.ENTERRESULTS_SCREEN);
//                break;
////            case "viewDB_BT":
////                ScreenController.changeScreen(controller, screens.VIEWDB_SCREEN, screens.VIEWDB_SCREEN);
////                break;
//            case "extraBT":
//                ScreenController.changeScreen(controller, screens.DASHBOARD_SCREEN, screens.EXTRA_SCREEN);
//                break;
//
//        }
//    }
}

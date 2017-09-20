package com.PharmacyMgt.Controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Dilshanka on 15/09/2017.
 */
public class testBillController implements Initializable,ControlledScreen {


     ScreenController controller;

        @FXML
        private Label idLabel;

        @FXML
        private Label dateLabel;

        @FXML
        private Label totLabel;


    @Override
    public void setScreenParent(ScreenController screenParent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

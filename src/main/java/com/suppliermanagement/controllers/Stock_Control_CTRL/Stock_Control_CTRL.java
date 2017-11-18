package com.suppliermanagement.controllers.Stock_Control_CTRL;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreenController;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Stock_Control_CTRL implements SessionListener, Initializable {


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

    @FXML
    public void createPurchase(){

        ScreenController.changeScreen(SupplierScreens.STOCK_CONTROL_NEW,mainScreenController.getContent(),mainScreenController);

    }
}

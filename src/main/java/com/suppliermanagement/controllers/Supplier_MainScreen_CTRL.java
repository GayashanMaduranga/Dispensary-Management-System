package com.suppliermanagement.controllers;

import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-21.
 */
public class Supplier_MainScreen_CTRL  implements Initializable,SessionListener,ControlledScreen {



    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private StackPane content;

    private ScreenController controller;

    private Supplier_MainScreen_CTRL mainScreenController;

    public String test = "Hello";

    private Supplier supplier;

    public  Supplier getEmployee() {
        return supplier;
    }

    public void setEmployee(Supplier supplier) {
        this.supplier = supplier;
    }

    public StackPane getContent() {
        return content;
    }

    public void setContent(StackPane content) {
        this.content = content;
    }

    private JFXButton selectedBtn;

    @FXML
    void logout(ActionEvent event) {
        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) logoutBtn.getScene().getWindow();
            s.close();
        }
    }

    @FXML
    void showHome(ActionEvent event) {
        ScreenController.changeScreen(controller, SupplierScreens.SUPPLIER_MAIN_VIEW, SupplierScreens.DASHBOARD_SCREEN);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedBtn = dashBoardBtn;
        selectedBtn.setDisable(true);


        ScreenController.changeScreen(SupplierScreens.SUPPLIER_SCREEN_VIEW, content);

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {
        mainScreenController = this;

    }
}

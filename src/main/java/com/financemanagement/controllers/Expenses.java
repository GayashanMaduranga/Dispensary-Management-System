package com.financemanagement.controllers;

import com.EntityClasses.OtherExpenses;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kavindu on 9/12/2017.
 */
public class Expenses implements Initializable,ControlledScreen {

    ScreenController controller;


    @FXML
    private JFXButton resetbtn;

    @FXML
    private Label userLbl;

    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton logoutBtn;

    //Creating Variables

    private final SimpleIntegerProperty id ;

    private SimpleStringProperty name;

    private SimpleStringProperty description;

    private SimpleStringProperty remark;

    private SimpleDoubleProperty amount;

    public Expenses(ScreenController controller, Integer id, String name, String description, String remark, Double amount) {
        this.controller = controller;
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.remark = new SimpleStringProperty(remark);
        this.amount = new SimpleDoubleProperty(amount);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }



    @FXML
    void changeScene(ActionEvent event) {

        switch (((JFXButton) event.getSource()).getId()){
            case "FinanceMainBtn":
                ScreenController.changeScreen(controller, FinanceScreens.EXPENSES_SCREEN, FinanceScreens.FINANCE_MAIN_SCREEN);
                break;
            //case "":
              //  break;

        }


    }

    @FXML
    void logout(ActionEvent event) {

        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) logoutBtn.getScene().getWindow();
            s.close();
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

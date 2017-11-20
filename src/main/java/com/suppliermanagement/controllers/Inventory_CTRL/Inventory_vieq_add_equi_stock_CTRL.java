package com.suppliermanagement.controllers.Inventory_CTRL;

import com.EntityClasses.*;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.controllers.MainScreenController;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Inventory_vieq_add_equi_stock_CTRL implements Initializable,ControlledScreen{

    ScreenController controller;
    Session session;
    SessionListener listener;

    static Item eq;


    private MainScreenController mainScreenController;

    ObservableList<Supplier> pharmacySupplierList = FXCollections.observableArrayList();
    ObservableList<Supplier> equipmentSupplierList = FXCollections.observableArrayList();



    @FXML
    private TextField st_amt;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton Reset;

    @FXML
    private JFXButton cancel;

    @FXML
    private Label warn;


    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {





    }













    @FXML
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancel.getScene().getWindow();
        s.close();
    }

    @FXML
    void reset(ActionEvent event) {

        if (textfieldcheck()) {
            st_amt.setText("");
        }

    }



    @FXML
    void add_stock(ActionEvent event) {


        int f  = eq.getStock();
        f += Integer.parseInt(st_amt.getText());
        eq.setStock(f);


        Main.dialogCanceled = false;
        Stage s = (Stage) add.getScene().getWindow();
        s.close();

        System.out.println("Done stock");


    }

    private boolean textfieldcheck()
    {
        if(st_amt.getText().isEmpty())       {


            warn.setText("Please Complete all fields before adding.");

            return false;
        }

        return true;

    }





}

package com.labinventory.controlers;

import com.Laboratory.controllers.LabScreens;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.Screen;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;


/**
 * Created by chamara on 8/13/2017.
 */
public class LabEquipmentControl implements Initializable, ControlledScreen{

    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        this.controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private JFXButton mtnBtn;


    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton sidebarRegisterBtn111;

    @FXML
    private JFXButton sidebarRegisterBtn1111;

    @FXML
    private JFXButton eqBtn;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private GridPane topPane;

    @FXML
    private Label userLbl;



    @FXML
    void changeScreen(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){

            case "eqBtn":


               // ScreenController.changeScreen(controller, LabInventoryScreens.LAB_EQUIPMENT_SCREEN, LabInventoryScreens.LAB_EQUIPMENT_SCREEN);

            break;

            case "mtnBtn":


                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_EQUIPMENT_SCREEN, LabInventoryScreens.LAB_MACHINE_SCREEN);

                break;

            case "sidebarRegisterBtn1111":


                ScreenController.changeScreen(controller, LabInventoryScreens.LAB_EQUIPMENT_SCREEN, LabInventoryScreens.LAB_INVENTORY_SCREENS);

                break;



        }


    }

    @FXML
    private TreeTableView<Equipment>eqTable;

    @FXML
    private TreeTableColumn<Equipment,String> name;

    @FXML
    private TreeTableColumn<Equipment, Number> id;

    @FXML
    private TreeTableColumn<Equipment, Number> remaiquan;

    @FXML
    private TreeTableColumn<Equipment, Number> addquan;

    @FXML
    private TreeTableColumn<Equipment, Number> totalquan;

    @FXML
    private TreeTableColumn<Equipment, String> supplier;

    @FXML
    private TreeTableColumn<Equipment, Number> lifetime;

    @FXML
    private TreeTableColumn<Equipment, Number> today;


    @FXML
    private JFXTextField name1;

    @FXML
    private JFXTextField id1;

    @FXML
    private JFXTextField remaiquan1;

    @FXML
    private JFXTextField addquan1;

    @FXML
    private JFXTextField totalquan1;

    @FXML
    private JFXTextField supplier1;

    @FXML
    private JFXDatePicker lifetime1;

    @FXML
    private JFXDatePicker today1;

    private Session session;


    @FXML
    void insertBTN(MouseEvent event) {

        int sid = Integer.parseInt(id.getText());
        String sname = name.getText();

        Equipment equipment = new Equipment();
        equipment.setName("abc");

        session.beginTransaction();
        session.save(equipment);
        session.getTransaction().commit();



       // table.getRoot().getChildren().clear();
        //table.getRoot().getChildren().addAll(itemList);
    }

    @FXML
    void deleteFromTable(MouseEvent event){

        TreeItem<Equipment> equipmeny = eqTable.getSelectionModel().getSelectedItem();

        session.beginTransaction();
        session.delete(equipmeny.getValue());
        session.getTransaction().commit();

       // Equipment.remove(equipmeny)
        //table.getRoot().getChildren().clear();
       // table.getRoot().getChildren().addAll(itemList);
    }



    @FXML
    void updateTable(MouseEvent event) {

        TreeItem<Equipment> s = eqTable.getSelectionModel().getSelectedItem();
        s.getValue().setName(name.getText());

        session.beginTransaction();
        session.update(s.getValue());
        session.getTransaction().commit();


    }


    @FXML
    void setFields(MouseEvent event) {
        Equipment equipment = eqTable.getSelectionModel().getSelectedItem().getValue();
        name.setText(equipment.getName());
        id.setText(String.valueOf(equipment.getName()));
        remaiquan.setText(String.valueOf(equipment.getRquant()));
        addquan.setText(String.valueOf(equipment.getAddquantity()));
        totalquan.setText(String.valueOf(equipment.getTotquantity()));
        supplier.setText(equipment.getSupplier());
       // lifetime.setText(equipment.getLifetime());
        //today.setText(equipment.getToday());


    }










    }
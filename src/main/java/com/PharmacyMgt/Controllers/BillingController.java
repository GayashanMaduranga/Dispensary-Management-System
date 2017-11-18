package com.PharmacyMgt.Controllers;


import com.EntityClasses.*;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.controllers.MainScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vikt0r on 10/09/2017.
 */
public class BillingController implements Initializable,SessionListener {

    private MainScreenController mainScreenController;
    Session session;
    ObservableList<PharmacyBatch> pBatchList = FXCollections.observableArrayList();
    TreeItem<PharmacyBatch> root;

    @FXML
    private GridPane topPane;

    @FXML
    private Label userLbl;

    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private JFXButton billingNavBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton paymentNavBtn;

    @FXML
    private JFXButton logNavBtn;

    @FXML
    private JFXButton stockNavBtn;

    @FXML
    private JFXButton msgPortalNavBtn;

    @FXML
    private JFXButton backBtn;

    //****************************************************************************

    @FXML
    private JFXTreeTableView<PharmacyBatch> table1;


    // for medication table ************************************


    @FXML
    void logout(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<PharmacyBatch> Pbatches = new ArrayList<>();

        session = ScreenController.getSession();
        session.beginTransaction();
//        Query pharmacyBatchQuery = session.createQuery("from PharmacyBatch");
//        List<PharmacyBatch> Pbatches = pharmacyBatchQuery.list();

        Query drugQuery = session.createQuery("select e from Item e where ITEM_TYPE = 'Drug'");
        List<Item> drugs = drugQuery.list();

        session.getTransaction().commit();

//        for (Item p : drugs){
//            for (PharmacyBatch batch:((PharmacyItem)p).getBatches()){
//                Pbatches.add(batch);
//            }
//        }
//
//        for (PharmacyBatch pharmacyBatch : Pbatches){
//
//            pBatchList.add(pharmacyBatch);
//        }
//
//        JFXTreeTableColumn<PharmacyBatch, String> nameCol =  new JFXTreeTableColumn<>("Name");
//        nameCol.setCellValueFactory(param -> param.getValue().getValue().getPharmacyItem().itemNameProperty());
//
//        JFXTreeTableColumn<PharmacyBatch, Number> stockavCol =  new JFXTreeTableColumn<>("Stock");
//        stockavCol.setCellValueFactory(param -> param.getValue().getValue().getPharmacyItem().stockProperty());
//
//        JFXTreeTableColumn<PharmacyBatch, String> edCol =  new JFXTreeTableColumn<>("exp");
//        edCol.setCellValueFactory(param -> param.getValue().getValue().expiryDateProperty());
//
//        JFXTreeTableColumn<PharmacyBatch, Number> mrpCol =  new JFXTreeTableColumn<>("MRP");
//        mrpCol.setCellValueFactory(param -> param.getValue().getValue().purchasingPriceProperty());
//
//
//        root = new RecursiveTreeItem<PharmacyBatch>(pBatchList, RecursiveTreeObject::getChildren);
//
//        table1.getColumns().setAll(nameCol, stockavCol, edCol, mrpCol);
//        table1.setRoot(root);
//        table1.setShowRoot(false);

    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {
        this.mainScreenController = (MainScreenController) controller;
    }
}

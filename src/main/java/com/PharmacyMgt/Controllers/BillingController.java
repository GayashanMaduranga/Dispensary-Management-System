package com.PharmacyMgt.Controllers;


import com.EntityClasses.Measure;
import com.EntityClasses.Medication;
import com.EntityClasses.Patient;
import com.EntityClasses.PharmacyBatch;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vikt0r on 10/09/2017.
 */
public class BillingController implements Initializable,ControlledScreen {

    ScreenController controller;
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
    void changeScene(ActionEvent event) {

        switch (((JFXButton) event.getSource()).getId()) {
            case "titlebtn":

                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_BILLING_SCREEN, PharmacyScreens.DASHBOARD_SCREEN);
                break;

            case "msgNavBtn":
                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_BILLING_SCREEN,PharmacyScreens.PHARMACY_MESSAGE_SCREEN);
                break;
            case "paymentNavBtn":
                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_BILLING_SCREEN, PharmacyScreens.PHARMACY_PAYMENT_SCREEN);
                break;
            case "stockNavBtn":
                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_BILLING_SCREEN, PharmacyScreens.PHARMACY_STOCK_SCREEN);
                break;

            case "backBtn":
                ScreenController.changeScreen(controller, PharmacyScreens.PHARMACY_BILLING_SCREEN, PharmacyScreens.DASHBOARD_SCREEN);
                break;




        }
    }

    @FXML
    void logout(ActionEvent event) {

    }



    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query pharmacyBatchQuery = session.createQuery("from PharmacyBatch");
        List<PharmacyBatch> Pbatches = pharmacyBatchQuery.list();
        session.getTransaction().commit();

        for (PharmacyBatch pharmacyBatch : Pbatches){

            pBatchList.add(pharmacyBatch);
        }

        JFXTreeTableColumn<PharmacyBatch, String> nameCol =  new JFXTreeTableColumn<>("Name");
        nameCol.setCellValueFactory(param -> param.getValue().getValue().getPharmacyItem().itemNameProperty());

        JFXTreeTableColumn<PharmacyBatch, Number> stockavCol =  new JFXTreeTableColumn<>("Stock");
        stockavCol.setCellValueFactory(param -> param.getValue().getValue().getPharmacyItem().stockProperty());

        JFXTreeTableColumn<PharmacyBatch, String> edCol =  new JFXTreeTableColumn<>("exp");
        edCol.setCellValueFactory(param -> param.getValue().getValue().expiryDateProperty());

        JFXTreeTableColumn<PharmacyBatch, Number> mrpCol =  new JFXTreeTableColumn<>("MRP");
        mrpCol.setCellValueFactory(param -> param.getValue().getValue().purchasingPriceProperty());


        root = new RecursiveTreeItem<PharmacyBatch>(pBatchList, RecursiveTreeObject::getChildren);

        table1.getColumns().setAll(nameCol, stockavCol, edCol, mrpCol);
        table1.setRoot(root);
        table1.setShowRoot(false);

    }




}

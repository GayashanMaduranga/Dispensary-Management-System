package com.suppliermanagement.controllers.Stock_Control_CTRL;

import com.EntityClasses.SupplyOrder;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.main.controllers.MainScreenController;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Stock_Control_CTRL implements SessionListener, Initializable {


    Session session;
    private MainScreenController mainScreenController;
    ObservableList<SupplyOrder> sup_orderlist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        session = ScreenController.getSession();
//        session.beginTransaction();
//        Query supOrderlist = session.createQuery("select s from PharmacyItem  s");
//        List<SupplyOrder> product = supOrderlist.list();
//        session.getTransaction().commit();
//
//        session.clear();
//
//
//        for (SupplyOrder po : product) {
//
//              sup_orderlist.add(po);
//
//        }
//
//        //*************** Generating JFX Tree table for item Table **********
//        JFXTreeTableColumn<SupplyOrder, Number> ord_id = new JFXTreeTableColumn<>("ID");
//        ord_id.setCellValueFactory(param -> param.getValue().getValue().orderIDProperty());
//
//        JFXTreeTableColumn<SupplyOrder, String> pro_brand = new JFXTreeTableColumn<>("Seller");
//        pro_brand.setCellValueFactory(param -> param.getValue().getValue().getDate();
//
//
//
//
//        root = new RecursiveTreeItem<PharmacyItem>(productlist, RecursiveTreeObject::getChildren);
//
//        product_table.getColumns().setAll(pro_name, pro_brand, pro_mrp, pro_reorder, pro_stock);
//        product_table.setRoot(root);
//        product_table.setShowRoot(false);


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

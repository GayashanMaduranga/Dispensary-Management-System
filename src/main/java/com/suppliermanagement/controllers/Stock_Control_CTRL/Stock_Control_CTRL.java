package com.suppliermanagement.controllers.Stock_Control_CTRL;

import com.EntityClasses.SupplyOrder;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.controllers.MainScreenController;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static antlr.build.ANTLR.root;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Stock_Control_CTRL implements SessionListener, Initializable {


    Session session;
    private MainScreenController mainScreenController;
    ObservableList<SupplyOrder> sup_orderlist = FXCollections.observableArrayList();
    TreeItem<SupplyOrder> root;

    private SupplyOrder s;

    @FXML
    private JFXTreeTableView<SupplyOrder> purchased;

    @FXML
    private JFXButton delete;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        session = ScreenController.getSession();
        session.beginTransaction();
        Query supOrderlist = session.createQuery("select s from SupplyOrder  s");
        List<SupplyOrder> product = supOrderlist.list();
        session.getTransaction().commit();

        session.clear();


        for (SupplyOrder po : product) {

              sup_orderlist.add(po);

        }

        //*************** Generating JFX Tree table for item Table **********
        JFXTreeTableColumn<SupplyOrder, Number> ord_id = new JFXTreeTableColumn<>("ID");
        ord_id.setCellValueFactory(param -> param.getValue().getValue().orderIDProperty());

        JFXTreeTableColumn<SupplyOrder, String> pro_name = new JFXTreeTableColumn<>("Item Name");
        pro_name.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        JFXTreeTableColumn<SupplyOrder, String> pro_sup = new JFXTreeTableColumn<>("Supplier");
        pro_sup.setCellValueFactory(param -> param.getValue().getValue().supplynameProperty());

        JFXTreeTableColumn<SupplyOrder, Number> pro_qty = new JFXTreeTableColumn<>("Qty");
        pro_qty.setCellValueFactory(param -> param.getValue().getValue().qtyProperty());

        JFXTreeTableColumn<SupplyOrder, Number> pro_tot = new JFXTreeTableColumn<>("Total");
        pro_tot.setCellValueFactory(param -> param.getValue().getValue().totalProperty());




        root = new RecursiveTreeItem<SupplyOrder>(sup_orderlist, RecursiveTreeObject::getChildren);

        purchased.getColumns().setAll(ord_id,pro_name,pro_sup, pro_qty,pro_tot);
        purchased.setRoot(root);
        purchased.setShowRoot(false);


    }

    @FXML
    void setfield(MouseEvent event) {

        s = purchased.getSelectionModel().getSelectedItem().getValue();

    }


    @FXML
    void rm_p(ActionEvent event) {



            if(ConfirmDialog.show("", "Are you sure?")){

                session.beginTransaction();
                session.delete(s);
                session.getTransaction().commit();

                sup_orderlist.remove(s);

                purchased.refresh();

                s = null;
            }



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

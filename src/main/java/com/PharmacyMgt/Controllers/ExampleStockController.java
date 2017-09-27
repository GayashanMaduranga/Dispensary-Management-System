package com.PharmacyMgt.Controllers;


import com.EntityClasses.Item;
import com.EntityClasses.PharmacyItem;
import com.common.AlertDialog;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.controllers.MainScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vikt0r on 10/09/2017.
 */
@SuppressWarnings("ALL")
public class ExampleStockController implements Initializable,SessionListener {

    private MainScreenController mainScreenController;
    Session session;
    ObservableList<Item> pItemList = FXCollections.observableArrayList();
    RecursiveTreeItem root;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtDrugName;

    @FXML
    private TextField txtReorderLvl;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button addDrugBtn;

    //****************************************************************************

    @FXML
    private JFXTreeTableView<Item> drugTable;


    // for medication table ************************************


    @FXML
    void logout(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        session = ScreenController.getSession();
        session.beginTransaction();

        Query drugQuery = session.createQuery("select e from Item e where ITEM_TYPE = 'Drug'");
        List<Item> drugs = drugQuery.list();

        session.getTransaction().commit();

        for (Item p : drugs){

            pItemList.add(p);

        }

        JFXTreeTableColumn<Item, String> nameCol =  new JFXTreeTableColumn<>("Name");
        nameCol.setCellValueFactory(param -> ((PharmacyItem)param.getValue().getValue()).itemNameProperty());

        JFXTreeTableColumn<Item, Number> stockavCol =  new JFXTreeTableColumn<>("Stock");
        stockavCol.setCellValueFactory(param -> param.getValue().getValue().stockProperty());

        JFXTreeTableColumn<Item, Number> reorderCOl =  new JFXTreeTableColumn<>("reorder level");
        reorderCOl.setCellValueFactory(param -> ((PharmacyItem)param.getValue().getValue()).reorderLevelProperty());

        JFXTreeTableColumn<Item, Number> mrpCol =  new JFXTreeTableColumn<>("MRP");
        mrpCol.setCellValueFactory(param -> ((PharmacyItem)param.getValue().getValue()).MRPProperty());

        root = new RecursiveTreeItem<>(pItemList, RecursiveTreeObject::getChildren);

        drugTable.getColumns().setAll(nameCol, stockavCol, reorderCOl, mrpCol);
        drugTable.setRoot(root);
        drugTable.setShowRoot(false);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> drugTable.setPredicate(itemTreeItem -> {
            boolean flag = ((PharmacyItem)itemTreeItem.getValue()).itemNameProperty().getValue().contains(newValue.toLowerCase());
            return flag;
        }));

    }

    @FXML
    public void addDrug(){

        if (fieldsAreComplete()) {
            PharmacyItem item = new PharmacyItem();
            item.setItemName(txtDrugName.getText().toLowerCase());
            item.setMRP(Integer.parseInt(txtPrice.getText()));
            item.setReorderLevel(Integer.parseInt(txtReorderLvl.getText()));
            item.setStock(0);

            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();

            pItemList.add(item);
            drugTable.refresh();

            txtDrugName.clear();
            txtPrice.clear();
            txtReorderLvl.clear();
        }
    }

    private boolean fieldsAreComplete(){

        if(txtDrugName.getText().isEmpty() | txtPrice.getText().isEmpty() |
                txtReorderLvl.getText().isEmpty()){

            AlertDialog.show("Alert", "*Complete all fields");

            return false;
        }

        return true;
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

package com.suppliermanagement.controllers.Inventory_CTRL;

import com.EntityClasses.*;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.controllers.MainScreenController;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Inventory_View_CTRL implements SessionListener, Initializable {


    Session session;
    private MainScreenController mainScreenController;
    ScreenController controller;

    private PharmacyItem ph;
    private PharmacyBatch pi;

    private Equipment equipment;
    private Item item;
    private Machine machine;
    private Maintenance maintenance;
    private UtilityEquipment utilityEquipment;
    private SupplyOrder supplyOrder;




    @FXML
    private Button new_pro;

    @FXML
    private JFXTreeTableView<PharmacyItem> product_table;

    @FXML
    private JFXTreeTableView<Item> equi_table;

//    @FXML
//    private JFXTreeTableView<Equipment> equi_table;

    @FXML
    private Button edit;




    ObservableList<PharmacyItem> productlist = FXCollections.observableArrayList();
    ObservableList<Item> equiList = FXCollections.observableArrayList();
    ObservableList<PharmacyBatch> productlist_batch = FXCollections.observableArrayList();

    TreeItem<PharmacyItem> root;
    TreeItem<Item> root2;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


            session = ScreenController.getSession();
            session.beginTransaction();
            Query productNameQuery = session.createQuery("select s from PharmacyItem  s");
            List<PharmacyItem> product = productNameQuery.list();
            session.getTransaction().commit();

            session.clear();


            for (PharmacyItem s : product) {

                productlist.add(s);
            }

            //*************** Generating JFX Tree table for item Table **********
            JFXTreeTableColumn<PharmacyItem, String> pro_name = new JFXTreeTableColumn<>("Product Name");
            pro_name.setCellValueFactory(param -> param.getValue().getValue().itemNameProperty());

            JFXTreeTableColumn<PharmacyItem, String> pro_brand = new JFXTreeTableColumn<>("Brand");
            pro_brand.setCellValueFactory(param -> param.getValue().getValue().brandProperty());

            JFXTreeTableColumn<PharmacyItem, Number> pro_mrp = new JFXTreeTableColumn<>("Manufacure Rate");
            pro_mrp.setCellValueFactory(param -> param.getValue().getValue().MRPProperty());

            JFXTreeTableColumn<PharmacyItem, Number> pro_reorder = new JFXTreeTableColumn<>("ReOrder Level");
            pro_reorder.setCellValueFactory(param -> param.getValue().getValue().reorderLevelProperty());

            JFXTreeTableColumn<PharmacyItem, Number> pro_stock = new JFXTreeTableColumn<>("Stock Level");
            pro_stock.setCellValueFactory(param -> param.getValue().getValue().stockProperty());


            root = new RecursiveTreeItem<PharmacyItem>(productlist, RecursiveTreeObject::getChildren);

            product_table.getColumns().setAll(pro_name, pro_brand, pro_mrp, pro_reorder, pro_stock);
            product_table.setRoot(root);
            product_table.setShowRoot(false);

        session = ScreenController.getSession();
        session.beginTransaction();
        Query equipmentQuery = session.createQuery("select e from Item e where ITEM_TYPE = 'Equipment'");
        List<Item> equipments = equipmentQuery.list();
        session.getTransaction().commit();

        for (Item p : equipments){

            equiList.add(p);
        }

        //Create Table
        //Create column

        JFXTreeTableColumn<Item, String> nameCol =  new JFXTreeTableColumn<>("Name");
        nameCol.setCellValueFactory(param -> ((Equipment)param.getValue().getValue()).equipmentNameProperty());

        JFXTreeTableColumn<Item, Number> IDCol =  new JFXTreeTableColumn<>("ID");
        IDCol.setCellValueFactory(param -> param.getValue().getValue().itemIDProperty());

        JFXTreeTableColumn<Item, Number> stockCol =  new JFXTreeTableColumn<>("Stock");
        stockCol.setCellValueFactory(param -> param.getValue().getValue().stockProperty());


        root2 = new RecursiveTreeItem<>(equiList, RecursiveTreeObject::getChildren);

        equi_table.getColumns().setAll(IDCol, nameCol, stockCol);
        equi_table.setRoot(root2);
        equi_table.setShowRoot(false);



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
    void update(ActionEvent event) {

        Stage s = (Stage)edit.getScene().getWindow();





            Inventory_view_Edit_product_CTRL.pharmacyItem = product_table.getSelectionModel().getSelectedItem().getValue();


        if(!(Main.createFadedWindow(new Stage(), s,"/com/suppliermanagement/Inventory_View/Inventory_view_Edit_product.fxml"))){

            PharmacyItem pi = Inventory_view_Edit_product_CTRL.pharmacyItem;
//            PharmacyBatch pb = Inventory_view_New_product_CTRL.pharmacyBatch;

            session.beginTransaction();
            session.update(pi);
//            session.update(pb);

            session.getTransaction().commit();

            root = new RecursiveTreeItem<PharmacyItem>(productlist, RecursiveTreeObject::getChildren);

            //supplierList.add(su);

            product_table.refresh();
            product_table.setRoot(root);
            product_table.setShowRoot(false);


            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }




    }



    @FXML
    void setField_pro()  {

        ph =product_table.getSelectionModel().getSelectedItem().getValue();



    }

    @FXML
    void delete() {

        if(ConfirmDialog.show("", "Are you sure?")){

            session.beginTransaction();
            session.delete(ph);
            session.getTransaction().commit();

            productlist.remove(ph);

            product_table.refresh();

            ph = null;
        }


    }






    @FXML
    public void new_product(){

        System.out.println("FUCK 2222");
        Stage s = (Stage) new_pro.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/suppliermanagement/Inventory_View/Inventory_view_New_product.fxml"))){

            PharmacyItem pi = Inventory_view_New_product_CTRL.pharmacyItem;
            PharmacyBatch pb = Inventory_view_New_product_CTRL.pharmacyBatch;

//            Equipment eq = Inventory_view_New_product_CTRL.equipment;
//            Item im = Inventory_view_New_product_CTRL.item;
//            UtilityEquipment ue = Inventory_view_New_product_CTRL.utilityEquipment;
//            Maintenance ma = Inventory_view_New_product_CTRL.maintenance;
//            SupplyOrder sp = Inventory_view_New_product_CTRL.supplyOrder;


            session.beginTransaction();
            session.save(pi);
            session.save(pb);



            session.getTransaction().commit();

            System.out.println("YESS DAMNNNN");


            root = new RecursiveTreeItem<PharmacyItem>(productlist, RecursiveTreeObject::getChildren);

            productlist.add(pi);

            product_table.refresh();

            product_table.setRoot(root);
            product_table.setShowRoot(false);


            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }
    }
}



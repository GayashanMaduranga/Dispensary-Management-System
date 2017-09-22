package com.suppliermanagement.controllers.Suppliers_CTRL;

import com.EntityClasses.Supplier;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.models.LoginModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Supplier_view_CTRL implements Initializable,  ControlledScreen{

    Session session;

    ScreenController controller;

    private Supplier s;

    ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

    TreeItem<Supplier> root;

    @FXML
    private JFXTreeTableView<Supplier> supplierTable;

    @FXML
    private JFXButton add_new;

    @FXML
    private Label userLbl;





    //Adding supplier to database
    @FXML
    void new_sup(){

        Stage s = (Stage) add_new.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/suppliermanagement/Suppliers_View/Add_Supplier.fxml"))){

            Supplier su = Add_Supplier_CTRL.supplier;

            session.beginTransaction();
            session.save(su);
            session.getTransaction().commit();

            root = new RecursiveTreeItem<Supplier>(supplierList, RecursiveTreeObject::getChildren);

            supplierList.add(su);

           supplierTable.refresh();


            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }
    }








    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    // JFX SCENE LOAD
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(LoginModel.getUser());
//        session = Main.getSessionFactory().openSession();

        session.beginTransaction();
        Query supplierNameQuery = session.createQuery("select s from Supplier s");
        List<Supplier> suppliers = supplierNameQuery.list();
        session.getTransaction().commit();


        for (Supplier s  : suppliers){

            supplierList.add(s);
        }

        //*************** Generating JFX Tree table for Supplier Table **********
        JFXTreeTableColumn<Supplier, Number> sup_ID =  new JFXTreeTableColumn<>("Supplier ID");
        sup_ID.setCellValueFactory(param -> param.getValue().getValue().supIdProperty());

        JFXTreeTableColumn<Supplier, String> supname =  new JFXTreeTableColumn<>("Name");
        supname.setCellValueFactory(param -> param.getValue().getValue().supnameProperty());

        JFXTreeTableColumn<Supplier, String> sup_phone=  new JFXTreeTableColumn<>("Phone");
        sup_phone.setCellValueFactory(param -> param.getValue().getValue().contactNumberProperty());

        JFXTreeTableColumn<Supplier, String> sup_email =  new JFXTreeTableColumn<>("E-mail");
        sup_email.setCellValueFactory(param -> param.getValue().getValue().emailProperty());

        JFXTreeTableColumn<Supplier, String> sup_addr =  new JFXTreeTableColumn<>("Address");
        sup_addr.setCellValueFactory(param -> param.getValue().getValue().addressProperty());

        root = new RecursiveTreeItem<Supplier>(supplierList, RecursiveTreeObject::getChildren);

        supplierTable.getColumns().setAll(sup_ID, supname, sup_phone, sup_email,sup_addr);
        supplierTable.setRoot(root);
        supplierTable.setShowRoot(false);

        //**************************************************************************************


    }
}

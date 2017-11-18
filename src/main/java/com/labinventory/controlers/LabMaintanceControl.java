package com.labinventory.controlers;

import com.EntityClasses.Equipment;
import com.EntityClasses.Item;
import com.EntityClasses.Machine;
import com.EntityClasses.Maintenance;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.controllers.MainScreenController;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by chamara on 8/13/2017.
// */
public class LabMaintanceControl implements Initializable,SessionListener {

    ScreenController controller;
    private MainScreenController mainScreenController;
    private Session session;


    ObservableList<Maintenance> maintananceList =FXCollections.observableArrayList();

    TreeItem<Maintenance> root;


    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {

    }

    //ScreenController controller;
    //private MainScreenController mainScreenController;
    //private Session session;

   // ObservableList<Item> maintananceList = FXCollections.observableArrayList();

   // TreeItem<Item> root;


    @FXML
    private JFXTreeTableView<Maintenance> MaintanceTable;

    @FXML
    private TextField txtSearch;

    @FXML
    private OctIconView searchGlyph;

    @FXML
    private TextField txtMaintananceReason;

    @FXML
    private TextField txtMaintananceCost;

    @FXML
    private JFXButton btnAddDetails;

    @FXML
    private ComboBox<Machine> cmbMachine;


    @FXML
    void addMain() {


       Machine machine= cmbMachine.getSelectionModel().getSelectedItem();

        System.out.println(machine.machineNameProperty().toString());

        Maintenance m = new Maintenance();
        m.setReason(txtMaintananceReason.getText());
        m.setCost(Integer.parseInt(txtMaintananceCost.getText()));
        m.setDateLastServiced(java.sql.Date.valueOf(java.time.LocalDate.now()));

        machine.getMaintenaces().add(m);
//
        session.flush();
        session.clear();
        session.beginTransaction();
        session.update(machine);
        session.getTransaction().commit();
//
//
        maintananceList.add(m);
        MaintanceTable.refresh();


    }


    @FXML
    void maintananceTableSelection(MouseEvent event) {

        Maintenance itm = MaintanceTable.getSelectionModel().getSelectedItem().getValue();
        Machine machine= cmbMachine.getSelectionModel().getSelectedItem();
        txtMaintananceReason.setText(itm.getReason());
        txtMaintananceCost.setText(itm.toString());

    }


 //   @FXML
 //        void AddDetails(){
//        Machine mac = cmbMachine.getSelectionModel().getSelectedItem();
//        m.setReason(txtMaintananceReason.getText());
//        m.setCost(Double.parseDouble(txtMaintananceCost.getText()));
//        m.setDateLastServiced(java.sql.Date.valueOf(java.time.LocalDate.now()));
//        //m.setStock(0);
//
//
//        session.beginTransaction();
//        session.save(m);
//        session.getTransaction().commit();
//
//        maintananceList.add(m);
//        MaintanceTable.refresh();
 //   }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();
        session.beginTransaction();
        Query maintananceQuery = session.createQuery("select m from Item m where ITEM_TYPE = 'Machine'");
        List<Machine> machines = maintananceQuery.list();
        session.getTransaction().commit();

        ObservableList<Machine> m = FXCollections.observableArrayList();
        m.addAll(machines);

        cmbMachine.setItems(m);

        JFXTreeTableColumn<Maintenance, Number> costCol =  new JFXTreeTableColumn<>("Name");
        costCol.setCellValueFactory(param -> ((Maintenance)param.getValue().getValue()).costProperty());

        JFXTreeTableColumn<Maintenance, String> reasonCol =  new JFXTreeTableColumn<>("service Period");
        reasonCol.setCellValueFactory(param -> ((Maintenance)param.getValue().getValue()).reasonProperty());

        JFXTreeTableColumn<Maintenance, String> dateServicedCol =  new JFXTreeTableColumn<>("Date Last Serviced");
        dateServicedCol.setCellValueFactory(param -> ((Maintenance)param.getValue().getValue()).DateServicedProperty());

        root = new RecursiveTreeItem<>(maintananceList, RecursiveTreeObject::getChildren);

        MaintanceTable.getColumns().setAll( reasonCol, costCol, dateServicedCol);
        MaintanceTable.setRoot(root);
        MaintanceTable.setShowRoot(false);
    }


    @FXML
    void comboSelect(){

        List<Maintenance> maint = cmbMachine.getSelectionModel().getSelectedItem().getMaintenaces();

        maintananceList.clear();

        for (Maintenance p : maint){

            maintananceList.add(p);
        }

        MaintanceTable.refresh();
    }
}

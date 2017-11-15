package com.labinventory.controlers;

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


    //@FXML
   // void addDetails() {

//        Machine n = new Machine();
//        Machine m = (Machine)Maintenance();
//        m.setMachineName(txtMachineName.getText);
//        // m.setServicePeriod(spinnerServicePeriod.getValue());
//        m.setDateLastServiced(java.sql.Date.valueOf(java.time.LocalDate.now()));
//         //m.setStock(0);
//
//        session.beginTransaction();
//        session.save(m);
//        session.getTransaction().commit();
//
//        MachineList.add(m);
//        MachineTable.refresh();
  //  }


    @FXML
    void AddDetails(){
        Machine mac = cmbMachine.getSelectionModel().getSelectedItem();
        mac.get
        m.setReason(txtMaintananceReason.getText());
        m.setCost(Double.parseDouble(txtMaintananceCost.getText()));
        m.setDateLastServiced(java.sql.Date.valueOf(java.time.LocalDate.now()));
        //m.setStock(0);


        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();

        maintananceList.add(m);
        MaintanceTable.refresh();
    }





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

        //to grt values

      //  cmbMachine.getSelectionModel().getSelectedItem().getItemID()


        for (Machine b : machines){

         // maintananceList.add(b);
   }

      JFXTreeTableColumn<Maintenance, String> ReaCol =  new JFXTreeTableColumn<>("Reason");
          ReaCol.setCellValueFactory(param -> param.getValue().getValue().reasonProperty());

    //  JFXTreeTableColumn<Item, Number> IDCol =  new JFXTreeTableColumn<>("ID");
     //   IDCol.setCellValueFactory(param -> param.getValue().getValue().itemIDProperty());

        JFXTreeTableColumn<Maintenance,Number> CostCol = new JFXTreeTableColumn<>("Cost");
        CostCol.setCellValueFactory(param ->param.getValue().getValue().costProperty());

   //    JFXTreeTableColumn<Item, Number> servicePeriodCol =  new JFXTreeTableColumn<>("Maintanance Reason");
   //     servicePeriodCol.setCellValueFactory(param -> (param.getValue().getValue()).servicePeriodProperty());

      JFXTreeTableColumn<Maintenance, String> dateServicedCol =  new JFXTreeTableColumn<>("Date Serviced");
       dateServicedCol.setCellValueFactory(param -> (param.getValue().getValue()).DateServicedProperty());
//
//    JFXTreeTableColumn<Item, Boolean> actionCol = new JFXTreeTableColumn<>("Action");
//        actionCol.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
//        actionCol.setCellFactory(param -> new LabMaintanceControl.ActionCell(MachineTable));
//
    root = new RecursiveTreeItem<>(maintananceList, RecursiveTreeObject::getChildren);

           MaintanceTable.getColumns().setAll(ReaCol, CostCol,dateServicedCol);
           MaintanceTable.setRoot(root);
           MaintanceTable.setShowRoot(false);
    }

       private class ActionCell extends TreeTableCell<Item, Boolean> {

           // a button for adding a new person.
//        private Button removeBtn = new Button("Remove");
//        // a button for adding a new person.
//        private Button editButton = new Button(" Edit ");
//        // pads and centers the buttons in the cell.
//        final HBox paddedButton = new HBox(10);//to give space between two buttions
//
//        /**
//         * EditMedicationCell constructor
//         * @param fromTable the Table in which button resides.
//         */
//
//        ActionCell(final TreeTableView<Item> fromTable) {
//


           }

           @FXML
           void addMachine(ActionEvent event) {

           }


       }

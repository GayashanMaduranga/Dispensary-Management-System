package com.labinventory.controlers;

import com.EntityClasses.Item;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreenController;
import com.financemanagement.controllers.FinanceScreens;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.EntityClasses.Equipment;
import javafx.scene.layout.HBox;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;


/**
 * Created by chamara on 8/13/2017.
 */
public class LabEquipmentControl implements Initializable,SessionListener {

    ScreenController controller;
    private MainScreenController mainScreenController;
    private Session session;

    ObservableList<Item> equipmentList = FXCollections.observableArrayList();

    TreeItem<Item> root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();
        session.beginTransaction();
        Query equipmentQuery = session.createQuery("select e from Item e where ITEM_TYPE = 'Equipment'");
        List<Item> equipments = equipmentQuery.list();
        session.getTransaction().commit();

        for (Item p : equipments){

            equipmentList.add(p);
        }

        //Create Table
        //Create column

        JFXTreeTableColumn<Item, String> nameCol =  new JFXTreeTableColumn<>("Name");
        nameCol.setCellValueFactory(param -> ((Equipment)param.getValue().getValue()).equipmentNameProperty());

        JFXTreeTableColumn<Item, Number> IDCol =  new JFXTreeTableColumn<>("ID");
        IDCol.setCellValueFactory(param -> param.getValue().getValue().itemIDProperty());

        JFXTreeTableColumn<Item, Number> stockCol =  new JFXTreeTableColumn<>("Stock");
        stockCol.setCellValueFactory(param -> param.getValue().getValue().stockProperty());

        JFXTreeTableColumn<Item, Boolean> actionCol = new JFXTreeTableColumn<>("Action");
        actionCol.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
        actionCol.setCellFactory(param -> new LabEquipmentControl.ActionCell(equipmentTable));


        root = new RecursiveTreeItem<>(equipmentList, RecursiveTreeObject::getChildren);

        equipmentTable.getColumns().setAll(IDCol, nameCol, stockCol, actionCol);
        equipmentTable.setRoot(root);
        equipmentTable.setShowRoot(false);

        //.....Search code

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> equipmentTable.setPredicate(patientTreeItem -> {
            boolean flag = ((Equipment)patientTreeItem.getValue()).equipmentNameProperty().getValue().contains(newValue.toLowerCase());
            return flag;
        }));
    }

    @FXML
    private JFXTreeTableView<Item> equipmentTable;

    @FXML
    private TextField txtSearch;

    @FXML
    private OctIconView searchGlyph;

    @FXML
    private TextField txtAddEquipment;

    @FXML
    private JFXButton btnAddEquipment;

    @FXML
    void showHome(){
        ScreenController.changeScreen(controller, LabInventoryScreens.LAB_EQUIPMENT_SCREEN, FinanceScreens.MAIN_DASHBOARD_SCREEN);
    }

    @FXML
    void addEquipment(){

        Equipment e = new Equipment();
        e.setEquipmentName(txtAddEquipment.getText());
        e.setStock(0);

        if(txtAddEquipment.getText()==null || txtAddEquipment.getText().trim().isEmpty() ){
            JOptionPane.showMessageDialog(null,"---Please Enter Valid Equipment Name---");
//            System.out.println("OK");
        }else{

            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();

            equipmentList.add(e);
            equipmentTable.refresh();        }

    }


    @FXML
    void EquipmentTableSelection(MouseEvent event) {

        Equipment itm = (Equipment)equipmentTable.getSelectionModel().getSelectedItem().getValue();
        txtAddEquipment.setText(itm.getEquipmentName());


    }





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


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) { this.mainScreenController = (MainScreenController)controller; }

    private class ActionCell extends TreeTableCell<Item, Boolean> {

        // a button for adding a new person.
        private Button removeBtn = new Button("Remove");
        // a button for adding a new person.
        private Button editButton = new Button(" Edit ");
        // pads and centers the buttons in the cell.
        final HBox paddedButton = new HBox(10);//to give space between two buttions

        /**
         * EditMedicationCell constructor
         * @param fromTable the Table in which button resides.
         */

        ActionCell(final TreeTableView<Item> fromTable) {

            //to remove buttion Colour
            //to add buttion colour

            editButton.getStyleClass().clear();
            editButton.getStyleClass().add("button-blue");

            removeBtn.getStyleClass().clear();
            removeBtn.getStyleClass().add("button-red");

            paddedButton.setPadding(new Insets(3));
            paddedButton.setAlignment(Pos.CENTER);
            paddedButton.getChildren().addAll(removeBtn, editButton);

            removeBtn.setOnAction(actionEvent -> {

                fromTable.getSelectionModel().select(getTreeTableRow().getIndex());
                TreeItem<Item> m = fromTable.getSelectionModel().getSelectedItem();

                session.beginTransaction();
                session.delete(m.getValue());
                session.getTransaction().commit();

                String equipmentName = ((Equipment)m.getValue()).getEquipmentName();

                equipmentList.removeIf(equipmentTreeItem -> {
                    boolean flag = false;
                    if(((Equipment)equipmentTreeItem).getEquipmentName() == equipmentName)
                        flag = true;
                    return flag;
                });

                fromTable.refresh();

            });
            editButton.setOnAction(actionEvent ->{

//                fromTable.getSelectionModel().select(getTreeTableRow().getIndex());
//                TreeItem<Equipment> z = fromTable.getSelectionModel().getSelectedItem();
//
//               // z.getValue().setEquipmentName();
//                session.beginTransaction();
//                session.update(z.getValue());
//                session.getTransaction().commit();
//
//                String equipmentName = z.getValue().getEquipmentName();

                Equipment itm = (Equipment)equipmentTable.getSelectionModel().getSelectedItem().getValue();
                itm.setEquipmentName(txtAddEquipment.getText());
                equipmentTable.refresh();

                session.beginTransaction();
                session.update(itm);
                session.getTransaction().commit();


            });
        }

        /** places an add button in the row only if the row is not empty. */
        @Override protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
    
}











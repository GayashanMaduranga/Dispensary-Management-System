package com.labinventory.controlers;

import com.EntityClasses.Equipment;
import com.EntityClasses.Item;
import com.EntityClasses.Machine;
import com.common.ControlledScreen;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by chamara on 8/13/2017.
 */
public class LabMachineControl implements SessionListener, Initializable{


    ScreenController controller;
    private MainScreenController mainScreenController;
    private Session session;

    ObservableList<Item> machineList = FXCollections.observableArrayList();

    TreeItem<Item> root;

    @FXML
    private JFXTreeTableView<Item> MachineTable;

    @FXML
    private TextField txtSearch;

    @FXML
    private OctIconView searchGlyph;

    @FXML
    private TextField txtMachineName;

    @FXML
    private Spinner<Integer> spinnerServicePeriod;

    @FXML
    private JFXButton btnAddMachine;

    @FXML
    void addMachine(){

        Machine e = new Machine();
        e.setMachineName(txtMachineName.getText());
        e.setServicePeriod(spinnerServicePeriod.getValue());
        e.setDateLastServiced(java.sql.Date.valueOf(java.time.LocalDate.now()));
        e.setStock(0);

        if(txtMachineName.getText()==null || txtMachineName.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"--Enter Valid Machine Name--");
            System.out.println("ok");
        }else {

        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();

        machineList.add(e);
        MachineTable.refresh();   }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();
        session.beginTransaction();
        Query machineQuery = session.createQuery("select e from Item e where ITEM_TYPE = 'Machine'");
        List<Item> machines = machineQuery.list();
        session.getTransaction().commit();

        for (Item p : machines){

            machineList.add(p);
        }

        JFXTreeTableColumn<Item, String> nameCol =  new JFXTreeTableColumn<>("Name");
        nameCol.setCellValueFactory(param -> ((Machine)param.getValue().getValue()).machineNameProperty());

        JFXTreeTableColumn<Item, Number> IDCol =  new JFXTreeTableColumn<>("ID");
        IDCol.setCellValueFactory(param -> param.getValue().getValue().itemIDProperty());

        JFXTreeTableColumn<Item, Number> servicePeriodCol =  new JFXTreeTableColumn<>("service Period");
        servicePeriodCol.setCellValueFactory(param -> ((Machine)param.getValue().getValue()).servicePeriodProperty());

        JFXTreeTableColumn<Item, String> dateServicedCol =  new JFXTreeTableColumn<>("Date Last Serviced");
        dateServicedCol.setCellValueFactory(param -> ((Machine)param.getValue().getValue()).DateServicedProperty());

        JFXTreeTableColumn<Item, Boolean> actionCol = new JFXTreeTableColumn<>("Action");
        actionCol.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
        actionCol.setCellFactory(param -> new LabMachineControl.ActionCell(MachineTable));

        root = new RecursiveTreeItem<>(machineList, RecursiveTreeObject::getChildren);

        MachineTable.getColumns().setAll(IDCol, nameCol, servicePeriodCol, dateServicedCol, actionCol);
        MachineTable.setRoot(root);
        MachineTable.setShowRoot(false);

        //Search

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> MachineTable.setPredicate(patientTreeItem -> {
            boolean flag =((Machine)patientTreeItem.getValue()).machineNameProperty().getValue().contains(newValue.toLowerCase());
            return flag;
        }));
    }

    @FXML
    void machineTableSelection(MouseEvent event) {

        Machine machine = (Machine)MachineTable.getSelectionModel().getSelectedItem().getValue();
        txtMachineName.setText(machine.getMachineName());
        spinnerServicePeriod.getValueFactory().setValue(machine.getServicePeriod());

    }

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

                String machineName = ((Machine)m.getValue()).getMachineName();

                machineList.removeIf(equipmentTreeItem -> {
                    boolean flag = false;
                    if(((Machine)equipmentTreeItem).getMachineName() == machineName)
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

                Machine machine = (Machine)MachineTable.getSelectionModel().getSelectedItem().getValue();
                machine.setMachineName(txtMachineName.getText());
                machine.setServicePeriod(spinnerServicePeriod.getValue());
                MachineTable.refresh();

                session.beginTransaction();
                session.update(machine);
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

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) { this.mainScreenController = (MainScreenController)controller; }
}

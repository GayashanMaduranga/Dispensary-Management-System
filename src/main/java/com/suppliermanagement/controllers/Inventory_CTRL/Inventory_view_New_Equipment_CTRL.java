package com.suppliermanagement.controllers.Inventory_CTRL;

import com.EntityClasses.*;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.controllers.MainScreenController;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Inventory_view_New_Equipment_CTRL implements Initializable,ControlledScreen{

    ScreenController controller;
    Session session;
    SessionListener listener;

    static Equipment equipment;
    static Item item;
    static Machine machine;
    static Maintenance maintenance;
    static UtilityEquipment utilityEquipment;
    static SupplyOrder supplyOrder;

    private MainScreenController mainScreenController;

    ObservableList<Supplier> pharmacySupplierList = FXCollections.observableArrayList();
    ObservableList<Supplier> equipmentSupplierList = FXCollections.observableArrayList();



   @FXML
    private ComboBox<Supplier> supcomb;

    @FXML
    private ComboBox<String> pro_type;

    @FXML
    private OctIconView searchGlyph;

    @FXML
    private ComboBox<Supplier> selectSupplierCombo;

    @FXML
    private Button btnNewSupplier;

    @FXML
    private Button btnCreateOrder;

    @FXML
    private Button btnCancelOrder;



    @FXML
    private TextField pro_name;

    @FXML
    private TextField mrp_rate;

    @FXML
    private TextField qty;

    @FXML
    private DatePicker exp_date;

    @FXML
    private TextField sell_price;

    @FXML
    private DatePicker manu_date;

    @FXML
    private TextField pur_price;

    @FXML
    private TextField brand_name;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private TextField total;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton Reset;

    @FXML
    private JFXButton cancel;

    @FXML
    private TextField stock;

    @FXML
    private TextField re_order;





    @FXML
    public void populateSupplier(){

        if(pro_type.getSelectionModel().getSelectedItem().equals("Pharmacy")){


            Stage sf = (Stage) pro_type.getScene().getWindow();

            if(!(Main.createFadedWindow(new Stage(), sf,"/com/suppliermanagement/Inventory_View/Inventory_view_New_product.fxml"))){

            }else{

                System.out.println("canceled");
            }

//            System.out.println("Fuck ya!");
//
//            supcomb.getItems().clear();
//            session = ScreenController.getSession();
//            session.beginTransaction();
//            Query supplierPharmacyQuery = session.createQuery("select s from Supplier s where s.type = 'Pharmaceutical'");
//
//            List<Supplier> pharmaList = supplierPharmacyQuery.list();
//            session.getTransaction().commit();
//            pharmacySupplierList.addAll(pharmaList);
//            supcomb.setItems(pharmacySupplierList);

        }else if(pro_type.getSelectionModel().getSelectedItem().equals("Equipment")){

//            System.out.println("Damn it");
//
//            supcomb.getItems().clear();
//            session.beginTransaction();
//            Query supplierEquipmentQuery = session.createQuery("select s from Supplier s where s.type = 'Equipment'");
//            //noinspection unchecked
//            List<Supplier> equipList = supplierEquipmentQuery.list();
//            session.getTransaction().commit();
//            equipmentSupplierList.addAll(equipList);
//
//            supcomb.setItems(equipmentSupplierList);


        }

    }



    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

          equipment = new Equipment();
          item  = new Item();
          machine = new Machine();
          maintenance = new Maintenance();
          utilityEquipment = new UtilityEquipment();
          supplyOrder =  new SupplyOrder();

        pro_type.getItems().setAll("Pharmacy","Equipment");

//        pro_type.getSelectionModel().getSelectedItem().equals("Equipment");
//        session = ScreenController.getSession();
//
//
//        System.out.println("fuck  1");
//
//
//        System.out.println("Damn it");
//
//        supcomb.getItems().clear();
//        session.beginTransaction();
//        Query supplierEquipmentQuery = session.createQuery("select s from Supplier s where s.type = 'Equipment'");
//        //noinspection unchecked
//        List<Supplier> equipList = supplierEquipmentQuery.list();
//        session.getTransaction().commit();
//        equipmentSupplierList.addAll(equipList);
//
//        supcomb.setItems(equipmentSupplierList);


    }









    @FXML
    void pro_view(ActionEvent event) {
        populateSupplier();
        System.out.println("ok clicked");
    }




    @FXML
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancel.getScene().getWindow();
        s.close();
    }

    @FXML
    void reset(ActionEvent event) {


        pro_name.setText("");
        brand_name.setText("");
        mrp_rate.setText("");
        re_order.setText("");
        stock.setText("");

        manu_date.setValue(null);
        exp_date.setValue(null);
        pur_price.setText("");

    }


    @FXML
    void add_product(ActionEvent event) {


        equipment.setEquipmentName((pro_name.getText().toLowerCase()));
        utilityEquipment.setUsableState(brand_name.getText());
        supplyOrder.setDate(Date.valueOf(manu_date.getValue()));
        machine.setDateLastServiced(Date.valueOf(exp_date.getValue()));
        utilityEquipment.setUsableState(mrp_rate.getText());
        maintenance.setCost(Double.parseDouble(pur_price.getText()));
        item.setStock(Integer.parseInt(stock.getText()));
        machine.setServicePeriod(Integer.parseInt(re_order.getText()));






        Main.dialogCanceled = false;
        Stage s = (Stage) add.getScene().getWindow();
        s.close();

        System.out.println("ADDEDDD FUCKKKKKKKK");


    }





}

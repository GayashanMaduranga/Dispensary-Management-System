package com.suppliermanagement.controllers.Inventory_CTRL;

import com.EntityClasses.PharmacyBatch;
import com.EntityClasses.PharmacyItem;
import com.EntityClasses.Supplier;
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
import javafx.scene.control.*;
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
public class Inventory_view_Edit_product_CTRL implements Initializable,ControlledScreen{

    ScreenController controller;
    Session session;


    static PharmacyItem pharmacyItem;
    static PharmacyBatch pharmacyBatch;

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
    private JFXButton update;

    @FXML
    private Label warn;



    private final ObservableList<PharmacyBatch> pbatch = FXCollections.observableArrayList();



    @FXML
    public void populateSupplier(){

        if(pro_type.getSelectionModel().getSelectedItem().equals("Pharmacy")){

           System.out.println("Fuck ya!");
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

           System.out.println("Damn it");
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


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();
        pro_type.getItems().setAll("Pharmacy", "Equipment");

        System.out.println("fuck  1");

        System.out.println("ID ISSSSSSSSS"+pharmacyItem.getPhId());

        pro_name.setText(pharmacyItem.getItemName());
        System.out.println(pharmacyItem.getItemName());
        brand_name.setText(pharmacyItem.getBrand());
        mrp_rate.setText(Double.toString(pharmacyItem.getMRP()));
        re_order.setText(Integer.toString(pharmacyItem.getReorderLevel()));
        stock.setText(Integer.toString(pharmacyItem.getStock()));



        session.beginTransaction();
        Query phbatch = session.createQuery("select  p from PharmacyBatch p where p.pharmacyItem.phId = :itemId");
        //noinspection uncheckedi
        phbatch.setParameter("itemId",pharmacyItem.getPhId());
        List <PharmacyBatch> batch = phbatch.list();
        session.getTransaction().commit();
//        session.close();

        for (PharmacyBatch po : batch) {
            System.out.println(po.getManufacturingDate());
            System.out.println(po.getExpiryDate());
            System.out.println(po.getPurchasingPrice());
            exp_date.setValue(po.getExpiryDate().toLocalDate());
            manu_date.setValue(po.getManufacturingDate().toLocalDate());
            pur_price.setText(Double.toString(po.getPurchasingPrice()));
        }




    }









    @FXML
    void pro_view(ActionEvent event) {
        populateSupplier();
        System.out.println("ok clicked");
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
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancel.getScene().getWindow();
        s.close();
    }


    @FXML
    void update_product(ActionEvent event) {

            System.out.println("Done");
            session= ScreenController.getSession();
            session.beginTransaction();

        if (textfieldcheck()) {

            pharmacyItem.setItemName((pro_name.getText().toLowerCase()));
            pharmacyItem.setBrand(brand_name.getText());
            pharmacyItem.setMRP(Double.parseDouble(mrp_rate.getText()));
            pharmacyItem.setReorderLevel(Integer.parseInt(re_order.getText()));
            pharmacyItem.setStock(Integer.parseInt(stock.getText()));

            Query phbatch = session.createQuery("update PharmacyBatch p set p.manufacturingDate = :manudate , p.expiryDate = :expdate , p.purchasingPrice = :purprice " + " where p.pharmacyItem.phId= :itemId ");
            //noinspection uncheckedi

            phbatch.setParameter("itemId", pharmacyItem.getPhId());
            phbatch.setParameter("manudate", (Date.valueOf(manu_date.getValue())));
            phbatch.setParameter("expdate", (Date.valueOf(exp_date.getValue())));
            phbatch.setParameter("purprice", (Double.parseDouble(pur_price.getText())));
            phbatch.executeUpdate();


            System.out.println(0 + " ******");
            session.getTransaction().commit();


            Main.dialogCanceled = false;
            Stage s = (Stage) update.getScene().getWindow();
            s.close();

        }


    }

    private boolean textfieldcheck()
    {
        if(pro_name.getText().isEmpty() || brand_name.getText().isEmpty() || mrp_rate.getText().isEmpty()
                || re_order.getText().isEmpty() || stock.getText().isEmpty() || (pur_price.getText().isEmpty()) )      {

            warn.setText("Please Complete all fields before adding.");

            return false;
        }

        return true;

    }





}

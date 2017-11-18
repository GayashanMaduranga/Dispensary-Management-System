package com.suppliermanagement.controllers.Inventory_CTRL;

import com.EntityClasses.*;
import com.common.AlertDialog;
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
import javafx.scene.input.MouseEvent;
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
public class Inventory_view_New_product_CTRL implements Initializable,ControlledScreen{

    ScreenController controller;
    Session session;
    SessionListener listener;

    static PharmacyItem pharmacyItem;
    static PharmacyBatch pharmacyBatch;

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
    private Label equip_name;

    @FXML
    private Label use_state;

    @FXML
    private Label last_serv;

    @FXML
    private Label service_period;

    @FXML
    private Label cost;

    @FXML
    private Label pur_date;

    @FXML
    private Label life_time;


    @FXML
    private Label pr_name;

    @FXML
    private Label mrp;

    @FXML
    private Label exp;

    @FXML
    private Label reorder;

    @FXML
    private Label manu;

    @FXML
    private Label brand;

    @FXML
    private Label pur_p;

    @FXML
    private JFXButton titlebtn1;










    @FXML
    public void populateSupplier(){

        if(pro_type.getSelectionModel().getSelectedItem().equals("Pharmacy")){

            titlebtn.setVisible(true);
            titlebtn1.setVisible(false);

            equip_name.setVisible(false);
            use_state.setVisible(false);
            last_serv.setVisible(false);
            service_period.setVisible(false);
            cost.setVisible(false);
            pur_date.setVisible(false);
            life_time.setVisible(false);

            pr_name.setVisible(true);
            mrp.setVisible(true);
            exp.setVisible(true);
            reorder.setVisible(true);
            manu.setVisible(true);
            brand.setVisible(true);
            pur_p.setVisible(true);



        }else if(pro_type.getSelectionModel().getSelectedItem().equals("Equipment")) {

            titlebtn.setVisible(false);
            titlebtn1.setVisible(true);


            pr_name.setVisible(false);
            mrp.setVisible(false);
            exp.setVisible(false);
            reorder.setVisible(false);
            manu.setVisible(false);
            brand.setVisible(false);
            pur_p.setVisible(false);

            equip_name.setVisible(true);
            use_state.setVisible(true);
            last_serv.setVisible(true);
            service_period.setVisible(true);
            cost.setVisible(true);
            pur_date.setVisible(true);
            life_time.setVisible(true);








//            Stage sf = (Stage) pro_type.getScene().getWindow();
//
//            if(!(Main.createFadedWindow(new Stage(), sf,"/com/suppliermanagement/Inventory_View/Inventory_view_New_Equipment.fxml"))){
//
//            }else{
//
//                System.out.println("canceled");
//            }


//            Main.dialogCanceled = true;
//            Stage s = (Stage)pro_type.getScene().getWindow();
//            s.hide();





        }



    }



    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pharmacyItem = new PharmacyItem();
        pharmacyBatch = new PharmacyBatch();
        equipment = new Equipment();
        item  = new Item();
        machine = new Machine();
        maintenance = new Maintenance();
        utilityEquipment = new UtilityEquipment();
        supplyOrder =  new SupplyOrder();
        //session = ScreenController.getSession();

        titlebtn1.setVisible(false);
        titlebtn.setVisible(true);


        equip_name.setVisible(false);
        use_state.setVisible(false);
        last_serv.setVisible(false);
        service_period.setVisible(false);
        cost.setVisible(false);
        pur_date.setVisible(false);
        life_time.setVisible(false);




        pro_type.getItems().setAll("Pharmacy","Equipment");
         pro_type.promptTextProperty();

        System.out.println("fuck  1");

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


        if(pro_type.getSelectionModel().getSelectedItem().equals("Pharmacy")) {


            pharmacyItem.setItemName((pro_name.getText().toLowerCase()));
            pharmacyItem.setBrand(brand_name.getText());
            pharmacyItem.setMRP(Double.parseDouble(mrp_rate.getText()));
            pharmacyItem.setReorderLevel(Integer.parseInt(re_order.getText()));
            pharmacyItem.setStock(Integer.parseInt(stock.getText()));

            pharmacyBatch.setPharmacyItem(pharmacyItem);
            pharmacyBatch.setManufacturingDate(Date.valueOf(manu_date.getValue()));
            pharmacyBatch.setExpiryDate(Date.valueOf(exp_date.getValue()));
            pharmacyBatch.setPurchasingPrice(Double.parseDouble(pur_price.getText()));


            Main.dialogCanceled = false;
            Stage s = (Stage) add.getScene().getWindow();
            s.close();

        }
        else if(pro_type.getSelectionModel().getSelectedItem().equals("Equipment")) {
            System.out.println("ADDEDDD FUCKKKKKKKK");
//
////            equipment.setEquipmentName((pro_name.getText().toLowerCase()));
////            utilityEquipment.setUsableState(brand_name.getText());
////            supplyOrder.setDate(Date.valueOf(manu_date.getValue()));
////            machine.setDateLastServiced(Date.valueOf(exp_date.getValue()));
////            utilityEquipment.setUsableState(mrp_rate.getText());
////            maintenance.setCost(Double.parseDouble(pur_price.getText()));
////            item.setStock(Integer.parseInt(stock.getText()));
////            machine.setServicePeriod(Integer.parseInt(re_order.getText()));
//            session= ScreenController.getSession();
//            session.beginTransaction();
//
//
////            String hql = "INSERT into Equipment(:cost , :date ,:dat" +
////                    "e_last, :service_p ,:life , :usable ,:eq_name, :stock ,:itemd )";
//
//            String hql = "INSERT into Equipment(200 ,2017-11-20,2017-11-25, 52 ,6, yess ,:eq_name, :stock ,:itemd )";
//
//            Query phbatch = session.createQuery(hql);
////            phbatch.setParameter("cost",Double.parseDouble(pur_price.getText()));
////            phbatch.setParameter("date_last",(Date.valueOf(exp_date.getValue())));
////            phbatch.setParameter("service_p",(Date.valueOf(exp_date.getValue())));
////            phbatch.setParameter("life",(Date.valueOf(exp_date.getValue())));
//
//
//
//            Main.dialogCanceled = false;
//            Stage s = (Stage) add.getScene().getWindow();
//            s.close();


        }else{

            AlertDialog.show("", "select item type");
        }

    }





}

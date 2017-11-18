package com.suppliermanagement.controllers.Suppliers_CTRL;

import com.EntityClasses.Supplier;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.Main;
import com.main.controllers.MainScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
@SuppressWarnings("Duplicates")
public class Edit_Supplier_CTRL implements ControlledScreen, Initializable {


    Session session;

    static Supplier supplier;

    @FXML
    private MainScreenController mainScreenController;

    @FXML
    private TextField sup_name;

    @FXML
    private TextField su_id;

    @FXML
    private TextField sup_phone;

    @FXML
    private TextField sup_mail;

    @FXML
    private Button create_sup;

    @FXML
    private Button reset_sup;

    @FXML
    private Button cancel_sup;

    @FXML
    private JFXButton titlebtn;

    @FXML
    private TextArea sup_address;

    @FXML
    private RadioButton equip;

    @FXML
    private ToggleGroup type;

    @FXML
    private RadioButton pharm;

    @FXML
    private Label message_lbl;


    @FXML
    private Button update_sup;



    private final ObservableList<Supplier> supplierlist = FXCollections.observableArrayList();

//    Supplier su = Add_Supplier_CTRL.supplier;

    @Override
    public void setScreenParent(ScreenController screenParent) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sup_name.setText(supplier.getSupname());
        sup_mail.setText(supplier.getEmail());
        sup_phone.setText(supplier.getContactNumber());
        sup_address.setText(supplier.getAddress());

        if(supplier.getType().matches("Equipment")){
            equip.setSelected(true);
        }else{
            pharm.setSelected(true);
        }


        System.out.println("OK");


    }



    @FXML
    void update(ActionEvent event) {
        if (textfieldcheck())

        {
            supplier.setSupname((sup_name.getText().toLowerCase()));
            supplier.setContactNumber(sup_phone.getText());
            supplier.setEmail(sup_mail.getText().toLowerCase());
            supplier.setAddress(sup_address.getText());
            supplier.setType(((RadioButton) type.getSelectedToggle()).getText());
            Main.dialogCanceled = false;
            Stage s = (Stage) update_sup.getScene().getWindow();
            s.close();
        }



        }


    @FXML
    void cancel(ActionEvent event) {
        Main.dialogCanceled = true;
        Stage s = (Stage)cancel_sup.getScene().getWindow();
        s.close();

    }






    private boolean textfieldcheck()
    {
        if(sup_name.getText().isEmpty() || sup_phone.getText().isEmpty() || sup_mail.getText().isEmpty()
                || sup_address.getText().isEmpty() || ((RadioButton)type.getSelectedToggle()).getText().isEmpty() )       {

            message_lbl.setText("Please Complete all fields before adding.");

            return false;
        }

        return true;

    }
//    @Override
//    public void setSession(Session session) {
//
//        this.session = session;
//    }
//
//    @Override
//    public void setMainController(SessionListener controller) {
//
//        this.mainScreenController = (MainScreenController) controller;
//
//    }
}

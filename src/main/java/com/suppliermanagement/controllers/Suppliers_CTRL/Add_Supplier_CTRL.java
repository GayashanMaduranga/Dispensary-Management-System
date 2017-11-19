package com.suppliermanagement.controllers.Suppliers_CTRL;

import com.EntityClasses.Supplier;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
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
public class Add_Supplier_CTRL implements Initializable,ControlledScreen {

    ScreenController controller;

    Session session;

    static Supplier supplier;

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
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancel_sup.getScene().getWindow();
        s.close();
    }

    @FXML
    void register(){


        if (textfieldcheck()) {
            if (isValidPhoneNumber()) {
                if (isValidEmail()) {
                    supplier.setSupname((sup_name.getText().toLowerCase()));
                    supplier.setContactNumber(sup_phone.getText());
                    supplier.setEmail(sup_mail.getText().toLowerCase());
                    supplier.setAddress(sup_address.getText());
                    supplier.setType(((RadioButton) type.getSelectedToggle()).getText());
                    Main.dialogCanceled = false;
                    Stage s = (Stage) create_sup.getScene().getWindow();
                    s.close();
                }




            }
        }




   }




    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplier = new Supplier();
        //su_id.setText(String.valueOf(supplier.supIdProperty().getValue().intValue()));
        //System.out.println(supplier.supIdProperty().getValue().intValue());
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


    private boolean isValidPhoneNumber(){

        boolean flag = false;

        String regex = "^[0-9]{10}$";

        if(sup_phone.getText().matches(regex)){

            flag = true;
        }else{

            message_lbl.setText("*Please enter a 10 digit phone number");
        }
        return flag;
    }


    private boolean isValidEmail(){

        boolean flag = false;

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(sup_mail.getText());

        if(m.matches()){

            flag = true;

        }else{

            message_lbl.setText("*Please enter a valid email address");
        }
        return flag;
    }
















    }








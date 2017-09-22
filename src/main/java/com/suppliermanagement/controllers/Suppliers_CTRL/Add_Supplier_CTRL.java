package com.suppliermanagement.controllers.Suppliers_CTRL;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
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
    void cancel(){
        Main.dialogCanceled = true;
        Stage s = (Stage)cancel_sup.getScene().getWindow();
        s.close();
    }

    @FXML
    void register(){



        supplier.setSupname((sup_name.getText().toLowerCase()));
        supplier.setContactNumber(sup_phone.getText());
        supplier.setEmail(sup_mail.getText().toLowerCase());
        supplier.setAddress(sup_address.getText());

        Main.dialogCanceled = false;
        Stage s = (Stage)create_sup.getScene().getWindow();
        s.close();
    }




    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         supplier =  new Supplier();

        /*session.beginTransaction();
        Query query1 = session.createQuery("FROM Supplier ORDER BY supId DESC ");
        query1.setMaxResults(1);

        List<Supplier> oid = (List<Supplier>) query1.list();

        session.getTransaction().commit();

        for (Supplier f: oid) {
            su_id.setText(String.valueOf(f.getSupId()+1));
        }
        */


    }
}

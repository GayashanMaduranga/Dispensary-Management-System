package com.Laboratory.controllers;

import com.EntityClasses.Patient;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.controllers.MainScreenController;
import com.patientmanagement.controllers.RegisterPatientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 8/19/2017.
 */
public class orderTestController implements Initializable,SessionListener {

    private List<Patient> patients;
    static Patient labSummaryPatient;
    private List<String> values;



    private Session session;
    private MainScreenController mainScreenController;

    ScreenController controller;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private TextField txtSearchPatient;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXButton addEmployeeBtn;


    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private Button addPatientBtn;

    @FXML
    private Button getSearch;



//    @Override
//    public void setScreenParent(ScreenController screenParent) {
//        controller = screenParent;
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = ScreenController.getSession();

        patients = new ArrayList<>();

        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p.pname  from Patient p");
        values = patientNameQuery.list();
        session.getTransaction().commit();

        TextFields.bindAutoCompletion(txtSearchPatient, values);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;


    }

    @FXML
    void selectTest(ActionEvent event) {

        ScreenController.changeScreen(LabScreens.SELECT_TEST_SCREEN,mainScreenController.getContent(),mainScreenController);


    }

    @FXML
    void populateDetails(){

        String patientName = txtSearchPatient.getText();

        patients.clear();

        session.clear();
        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p from Patient p where p.pname = '"+patientName+"'");
        patients = patientNameQuery.list();
        session.getTransaction().commit();

        labSummaryPatient = patients.get(0);

        txtGender.setText(labSummaryPatient.getGender());
        txtAddress.setText(labSummaryPatient.getAddress());
        txtNIC.setText(labSummaryPatient.getNIC());


    }









    @FXML
    void addPatient(){

        Stage s = (Stage) addPatientBtn.getScene().getWindow();

        if(!(Main.createFadedWindow(new Stage(), s,"/com/patientmanagement/RegisterPatient.fxml"))){

            Patient p = RegisterPatientController.patient ;

            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();

            session.flush();
            session.clear();
            session.beginTransaction();
            Query patientNameQuery = session.createQuery("select p.pname  from Patient p");
            values = patientNameQuery.list();
            session.getTransaction().commit();

            TextFields.bindAutoCompletion(txtSearchPatient, values);


            Main.dialogCanceled = true;

        }else{

            System.out.println("canceled");
        }
    }









}


//DASHBOARD_SCREEN, LabScreens.EXTRA_SCREEN);
// current screen    |  next screen
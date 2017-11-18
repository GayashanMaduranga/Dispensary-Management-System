package com.Laboratory.controllers;

import com.EntityClasses.Patient;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 8/19/2017.
 */
public class orderTestController implements Initializable,SessionListener {

    private List<Patient> patients;
    static Patient labSummaryPatient;



    private Session session;
    private MainScreenController mainScreenController;

    ScreenController controller;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private TextField txtSearchPatient;

    @FXML
    private JFXButton addEmployeeBtn;



//    @Override
//    public void setScreenParent(ScreenController screenParent) {
//        controller = screenParent;
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = ScreenController.getSession();

        List<String> values;
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

        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p from Patient p where p.pname = '"+patientName+"'");
        patients = patientNameQuery.list();
        session.getTransaction().commit();

        labSummaryPatient = patients.get(0);

//        lblPid.setText(Integer.toString(labSummaryPatient.getpId()));
//        lblPoccupation.setText(labSummaryPatient.getOccupation());
//        lblPemail.setText(labSummaryPatient.getEmail());
//        lblPphone.setText(labSummaryPatient.getContactNumber());
//        lblPage.setText(labSummaryPatient.getDOB().toString());
//        lblPname.setText(labSummaryPatient.getPname());

    }


}


//DASHBOARD_SCREEN, LabScreens.EXTRA_SCREEN);
// current screen    |  yanna ona screen eka
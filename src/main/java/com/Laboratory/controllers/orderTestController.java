package com.Laboratory.controllers;

import com.EntityClasses.MainTest;
import com.EntityClasses.Patient;
import com.common.AlertDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.employeemanagement.controllers.MyScreens;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.Main;
import com.main.controllers.MainScreenController;
import com.patientmanagement.controllers.RegisterPatientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
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
    private JFXTreeTableView<MainTest> costTable;

    @FXML
    private JFXButton selectTestBtn;

    @FXML
    private Button printReciptBT;

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
    @FXML
    private TextField txtTotalPrice;


    private double totPrice;

    private TreeItem<MainTest> root3;
    private ObservableList<MainTest> costList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        disableButtons();

        session = ScreenController.getSession();

        patients = new ArrayList<>();

        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p.pname  from Patient p");
        values = patientNameQuery.list();
        session.getTransaction().commit();

        TextFields.bindAutoCompletion(txtSearchPatient, values);

        if(!(selectTestController.selectedTestList.isEmpty())){
            costList.addAll(selectTestController.selectedTestList);
        }

        JFXTreeTableColumn<MainTest, String> STnameCol =  new JFXTreeTableColumn<>("TestName");
        STnameCol.setCellValueFactory(param -> param.getValue().getValue().testNameProperty());

        JFXTreeTableColumn<MainTest, Number> STPriceCol =  new JFXTreeTableColumn<>("Price");
        STPriceCol.setCellValueFactory(param -> param.getValue().getValue().testPriceProperty());

        root3 = new RecursiveTreeItem<>(costList, RecursiveTreeObject::getChildren);

        //noinspection unchecked
        costTable.getColumns().setAll(STnameCol, STPriceCol);
        costTable.setRoot(root3);
        costTable.setShowRoot(false);
    }

    @FXML
    void printReceipt(){

        totPrice = 0.0;

        for (MainTest s : costList){
            totPrice += s.getTestPrice();
        }

        txtTotalPrice.setText(Double.toString(totPrice));

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

    private void disableButtons(){

        selectTestBtn.setDisable(true);
        System.out.println("");
    }

    private void enableButtons(){

        selectTestBtn.setDisable(false);
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

        if (!(patients.isEmpty())) {
            labSummaryPatient = patients.get(0);

            txtGender.setText(labSummaryPatient.getGender());
            txtAddress.setText(labSummaryPatient.getAddress());
            txtNIC.setText(labSummaryPatient.getNIC());
            enableButtons();
        } else {

            disableButtons();
            AlertDialog.show("Alert", "There is no such patient");
        }


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
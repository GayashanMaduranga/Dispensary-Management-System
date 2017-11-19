package com.Laboratory.controllers;

import com.EntityClasses.LabTestOrder;
import com.EntityClasses.MainTest;
import com.EntityClasses.Patient;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 8/19/2017.
 */
public class enterResultsController implements Initializable,SessionListener {




    private Session session;
    private MainScreenController mainScreenController;


    @FXML
    private JFXButton enterResSearchBT;

    @FXML
    private TreeTableView<LabTestOrder> patientTable;

    @FXML
    private TreeTableColumn<LabTestOrder, Number> patientIdCol;

    @FXML
    private TreeTableColumn<LabTestOrder,String> patientNameCol;

    @FXML
    private TreeTableColumn<LabTestOrder, Number> testIdCol;

    @FXML
    private TreeTableColumn<MainTest, String> testNameCol;

    @FXML
    private TreeTableColumn<?, ?> statusCol;

    @FXML
    private JFXButton enterResPrintBT;

    @FXML
    private JFXButton enterResDeleteBT;

    private List<TreeItem<LabTestOrder>> labTestOrderList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = ScreenController.getSession();


        labTestOrderList = new ArrayList<>();
        initTables();



        session.beginTransaction();
        Query patientNameQuery = session.createQuery("select p from LabTestOrder p");
        //noinspection unchecked
        List<LabTestOrder> testOrders = patientNameQuery.list();
        session.getTransaction().commit();

        labTestOrderList.clear();


        for(LabTestOrder l : testOrders){
            labTestOrderList.add(new TreeItem<>(l));
        }

        patientTable.getRoot().getChildren().clear();
        patientTable.getRoot().getChildren().addAll(labTestOrderList);




//        enterResSearchBT.textProperty().addListener((observable, oldValue, newValue) -> patientTable.setPredicate(patientTreeItem -> {
//            boolean flag = patientTreeItem.getValue().pnameProperty().getValue().contains(newValue.toLowerCase());
//            return flag;

    }


    void setSelectionDetails(MouseEvent event) {

//        Employee staff = staffTable.getSelectionModel().getSelectedItem().getValue();
//        fullName.setText(staff.getName());
//        nic.setText(staff.getNic());
//        contactNumber.setText(staff.getContactNumber());
//        email.setText(staff.getEmail());
//        jobRole.setText(staff.getJobRole());
//
//        mainController.setEmployee(staff);


    }

    private void initTables(){

        patientIdCol.setCellValueFactory(param -> param.getValue().getValue().getPatient().pIdProperty());
        patientNameCol.setCellValueFactory(param -> param.getValue().getValue().getPatient().pnameProperty());
        testIdCol.setCellValueFactory(param -> param.getValue().getValue().oIdProperty());
       // testNameCol.setCellValueFactory(param -> param.getValue().getValue().testNameProperty());




        LabTestOrder order= new LabTestOrder();
        Patient p = new Patient();


        MainTest m =new MainTest();



        p.setpId(1);
        p.setPname("Name");

//        m.setTestName("name");
//        TreeItem<LabTestOrder> root = new TreeItem<>();

        order.setoId(1);
        TreeItem<LabTestOrder> root1 = new TreeItem<>();




        patientTable.setRoot(root1);
        patientTable.setShowRoot(false);
//        patientTable.setRoo
//        priviousEmployementTable.setRoot(root1);
//        priviousEmployementTable.setShowRoot(false);




    }




    @FXML
    void insertResult(ActionEvent event) {

        ScreenController.changeScreen(LabScreens.INPUTRESULT_SCREEN,mainScreenController.getContent(),mainScreenController);

    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController)controller;
    }


}

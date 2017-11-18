package com.Laboratory.controllers;

/**
 * Created by AmilaWC on 9/15/2017.
 */

import com.EntityClasses.MainTest;
import com.EntityClasses.TestField;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreenController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.UserSession;
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


@SuppressWarnings("Duplicates")
public class addTestController implements Initializable, SessionListener{

    private Session session;
    private MainScreenController mainScreenController;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField testName;

    @FXML
    private JFXTextField testPrice;

    @FXML
    private JFXTextField fid;

    @FXML
    private JFXTextField fName;

    @FXML
    private JFXTextField Units;




    @FXML
    private TreeTableView<MainTest> mainTestTable;

    @FXML
    private TreeTableColumn<MainTest, Number> mainid;

    @FXML
    private TreeTableColumn<MainTest, String> mainName;

    @FXML
    private TreeTableColumn<MainTest, Number> mainPrice;






    @FXML
    private TreeTableView<TestField> testFieldTable;

    @FXML
    private TreeTableColumn<TestField, Number> TestFid;

    @FXML
    private TreeTableColumn<TestField, String> TestName;

    @FXML
    private TreeTableColumn<TestField, String > testUnits;





    @FXML
    private Button resetBT;

    @FXML
    private Button reset1;

    private List<TreeItem<MainTest>> mainTestList;

    private List<TreeItem<TestField>> fieldList;


    @FXML
    void addMainTest(ActionEvent event) {


        MainTest mainTest = new MainTest();
        //mainTest.settId(Integer.parseInt(id.getText()));
        mainTest.setTestName(testName.getText());
        mainTest.setTestPrice(Double.parseDouble(testPrice.getText()));

        session.beginTransaction();
        session.save(mainTest);
        session.getTransaction().commit();

        mainTestList.add(new TreeItem<MainTest>(mainTest));
        mainTestTable.getRoot().getChildren().clear();
        mainTestTable.getRoot().getChildren().addAll(mainTestList);


        System.out.println("Test if MainTest");
    }

    @FXML
    void addTestField(ActionEvent event) {

        TreeItem<MainTest> treeItem = mainTestTable.getSelectionModel().getSelectedItem();

        TestField field = new TestField();
        field.setFieldName(fName.getText());
        field.setUnits(testUnits.getText());
        treeItem.getValue().getTestFields().add(field);

        session.beginTransaction();
        session.update(treeItem.getValue());
        session.getTransaction().commit();

        fieldList.add(new TreeItem<TestField>(field));
        testFieldTable.getRoot().getChildren().clear();
        testFieldTable.getRoot().getChildren().addAll(fieldList);



    }

    @FXML
    void deleteMainTest(ActionEvent event) {

        TreeItem<MainTest> m = mainTestTable.getSelectionModel().getSelectedItem();

        session.beginTransaction();
        session.delete(m.getValue());
        session.getTransaction().commit();

        mainTestList.remove(m);
        mainTestTable.getRoot().getChildren().clear();
        mainTestTable.getRoot().getChildren().addAll(mainTestList);



    }

    @FXML
    void deleteTestField(ActionEvent event) {


        TreeItem<TestField> f = testFieldTable.getSelectionModel().getSelectedItem();

        session.beginTransaction();
        session.delete(f.getValue());
        session.getTransaction().commit();

        fieldList.remove(f);
        testFieldTable.getRoot().getChildren().clear();
        testFieldTable.getRoot().getChildren().addAll(fieldList);




    }




    @FXML
    void updateMainTest(ActionEvent event) {

        TreeItem<MainTest> m = mainTestTable.getSelectionModel().getSelectedItem();
        m.getValue().setTestName(testName.getText());
        m.getValue().setTestPrice(Integer.parseInt(testPrice.getText()));

        session.beginTransaction();
        session.update(m.getValue());
        session.getTransaction().commit();
    }



    @FXML
    void updateTestField(ActionEvent event) {

        TreeItem<TestField> m = testFieldTable.getSelectionModel().getSelectedItem();
        m.getValue().setFieldName(testName.getText());
        m.getValue().setUnits(testPrice.getText());

        session.beginTransaction();
        session.update(m.getValue());
        session.getTransaction().commit();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       mainid.setCellValueFactory(param -> param.getValue().getValue().tIdProperty());
       mainName.setCellValueFactory(param -> param.getValue().getValue().testNameProperty());
       mainPrice.setCellValueFactory(param -> param.getValue().getValue().testPriceProperty());




        session = ScreenController.getSession();
        mainTestList = new ArrayList<>();
        fieldList = new ArrayList<>();

        session.beginTransaction();
        Query query = session.createQuery("select s from MainTest s");
        List<MainTest> list = query.list();

        session.getTransaction().commit();

        for (MainTest s : list){
            mainTestList.add(new TreeItem<>(s));
        }


        MainTest mainTest = new MainTest();
        mainTest.settId(0);
        mainTest.setTestName("Test Name");
        mainTest.setTestPrice(0);

        TreeItem<MainTest> root = new TreeItem<>(mainTest);
        root.getChildren().addAll(mainTestList);
        mainTestTable.setRoot(root);
        mainTestTable.setShowRoot(false);


        TestFid.setCellValueFactory(param -> param.getValue().getValue().fIdProperty());

        TestName.setCellValueFactory(param -> param.getValue().getValue().fieldNameProperty());

        testUnits.setCellValueFactory(param -> param.getValue().getValue().unitsProperty());

        TestField testField = new TestField();
        testField.setfId(0);
        testField.setFieldName("Test Field Test");
        testField.setUnits("unit");

        TreeItem<TestField> root2 = new TreeItem<>(testField);

        testFieldTable.setRoot(root2);
        testFieldTable.setShowRoot(false);


    }


    @FXML
    void setTextFields(MouseEvent event) {

        TreeItem<MainTest> treeItem = mainTestTable.getSelectionModel().getSelectedItem();

        MainTest mainTest = treeItem.getValue();
        mainTest.getTestFields();
        fieldList.clear();

        for (TestField testField : mainTest.getTestFields()){
            fieldList.add(new TreeItem<>(testField));
        }

        testFieldTable.getRoot().getChildren().clear();
        testFieldTable.getRoot().getChildren().addAll(fieldList);

    }

    @FXML
    void reset(MouseEvent event) {

        testName.setText("");
        testPrice.setText("");

    }

    @FXML
    void reset2(MouseEvent event) {

        fid.setText("");
        fName.setText("");
        Units.setText("");

    }



    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController) controller;


    }
}

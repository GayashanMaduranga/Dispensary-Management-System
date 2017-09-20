package com.Laboratory.controllers;


import com.jfoenix.controls.JFXButton;
import com.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import com.EntityClasses.MainTest;
import com.EntityClasses.TestField;
import db.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 9/17/2017.
 */
public class AddReferenceValueController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}















//    @FXML
//    private JFXButton back_to_addTestBT;
//
//    @FXML
//    private TitledPane maintest;
//
//    @FXML
//    private TreeTableView<MainTest> mainTestTable;
//
//    @FXML
//    private TreeTableColumn<MainTest, Number> mainid;
//
//    @FXML
//    private TreeTableColumn<MainTest, String> mainName;
//
//    @FXML
//    private TreeTableColumn<MainTest, Number> mainPrice;
//
//    @FXML
//    private TitledPane testF;
//
//    @FXML
//    private TreeTableView<TestField> testFieldTable;
//
//    @FXML
//    private TreeTableColumn<TestField, Number> TestFid;
//
//    @FXML
//    private TreeTableColumn<TestField, String> TestName;
//
//    @FXML
//    private TreeTableColumn<TestField, String> testUnits;
//
//    private List<TreeItem<MainTest>> mainTestList;
//
//    private List<TreeItem<TestField>> fieldList;
//
//    private Session session;
//
//
//    public class addTestController implements Initializable{
//
//
//        @Override
//        public void initialize(URL location, ResourceBundle resources) {
//
//
//            session = UserSession.getSession();
//
//            mainTestList = new ArrayList<>();
//
//
//            session.beginTransaction();
//            Query query = session.createQuery("select s from MainTest s");
//            List<MainTest> students = query.list();
//
//            session.getTransaction().commit();
//
//            for (MainTest s : students){
//                mainTestList.add(new TreeItem<>(s));
//            }
//
//            mainid.setCellValueFactory(param -> param.getValue().getValue().tIdProperty());
//            mainName.setCellValueFactory(param -> param.getValue().getValue().testNameProperty());
//            mainPrice.setCellValueFactory(param -> param.getValue().getValue().testPriceProperty());
//
//
//
//
//            MainTest mainTest = new MainTest();
//            mainTest.settId(0);
//            mainTest.setTestName("Test Name");
//            mainTest.setTestPrice(0);
//
//            TreeItem<MainTest> root = new TreeItem<>(mainTest);
//
//            root.getChildren().addAll(mainTestList);
//            mainTestTable.setRoot(root);
//            mainTestTable.setShowRoot(false);
//
//            root.getChildren().addAll(mainTestList);
//            mainTestTable.setRoot(root);
//            mainTestTable.setShowRoot(false);
//
//            //****************************************************************
//            session = UserSession.getSession();
//
//            fieldList = new ArrayList<>();
//            session.beginTransaction();
//
//
//            Query query1 = session.createQuery("select s from TestField s");
//            List<TestField> field1 = (List<TestField>)query.list();
//
//            session.getTransaction().commit();
//
//            for (TestField e : field1){
//                fieldList.add(new TreeItem<>(e));
//            }
//
//
//
//            TestFid.setCellValueFactory(param -> param.getValue().getValue().fIdProperty());
//
//            TestName.setCellValueFactory(param -> param.getValue().getValue().fieldNameProperty());
//
//            testUnits.setCellValueFactory(param -> param.getValue().getValue().unitsProperty());
//
//            TestField testField = new TestField();
//            testField.setfId(0);
//            testField.setFieldName("Test Field Test");
//            testField.setUnits("unit");
//
//            TreeItem<TestField> root2 = new TreeItem<>();
//            root2.getChildren().addAll(fieldList);
//            testFieldTable.setRoot(root2);
//            testFieldTable.setShowRoot(false);
//
//
//
//
//        }
//    }
//
//
//
//
//
//





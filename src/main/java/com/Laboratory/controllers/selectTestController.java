package com.Laboratory.controllers;

import com.EntityClasses.MainTest;
import com.EntityClasses.TestField;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.main.controllers.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 8/21/2017.
 */
public class selectTestController  implements Initializable,SessionListener {



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





    private List<TreeItem<MainTest>> mainTestList;

    private List<TreeItem<TestField>> fieldList;



    @FXML
    private JFXButton backBT;

    @FXML
    void setTextFields(MouseEvent event) {

    }









    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = ScreenController.getSession();







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




        TestField testField = new TestField();
        testField.setfId(0);
        testField.setFieldName("Test Field Test");
        testField.setUnits("unit");

        TreeItem<TestField> root2 = new TreeItem<>(testField);




    }

    @Override
    public void setSession(Session session) {
        //this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController) controller;


    }

    @FXML
    void selectTest(ActionEvent event) {

      //  ScreenController.changeScreen(LabScreens.SELECT_TEST_SCREEN,homeController.getContent(),homeController);
    }




}

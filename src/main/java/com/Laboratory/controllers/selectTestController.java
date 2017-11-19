package com.Laboratory.controllers;

import com.EntityClasses.MainTest;
import com.EntityClasses.TestField;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.controllers.MainScreenController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Created by AmilaWC on 8/21/2017.
 */
public class selectTestController  implements Initializable,SessionListener {



    private Session session;
    private MainScreenController mainScreenController;
    private MainTest selectedMainTest= new MainTest();





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
    private TextField txtpId;

    @FXML
    private TextField txtpName;

    @FXML
    private Button searchBT;




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




    @FXML
    private JFXTreeTableView<TestField> subTestTable;

    @FXML
    private JFXTreeTableView<?> selectedTestTable;

    @FXML
    private JFXButton backBT;

    @FXML
    void setTextFields(MouseEvent event) {

    }

    ObservableList<TestField> fieldList = FXCollections.observableArrayList();

    TreeItem<TestField> root2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        txtpId.setText(Integer.toString(orderTestController.labSummaryPatient.getpId()));
//        txtpName.setText(orderTestController.labSummaryPatient.getPname());

        mainid.setCellValueFactory(param -> param.getValue().getValue().tIdProperty());
        mainName.setCellValueFactory(param -> param.getValue().getValue().testNameProperty());
        mainPrice.setCellValueFactory(param -> param.getValue().getValue().testPriceProperty());

        session = ScreenController.getSession();
        mainTestList = new ArrayList<>();

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


        JFXTreeTableColumn<TestField, String> fieldNameCol =  new JFXTreeTableColumn<>("Name");
        fieldNameCol.setCellValueFactory(param -> (param.getValue().getValue()).fieldNameProperty());

        JFXTreeTableColumn<TestField, String> fieldUnitCol =  new JFXTreeTableColumn<>("ID");
        fieldUnitCol.setCellValueFactory(param -> param.getValue().getValue().unitsProperty());

        root2 = new RecursiveTreeItem<>(fieldList, RecursiveTreeObject::getChildren);

        subTestTable.getColumns().setAll(fieldNameCol, fieldUnitCol);
        subTestTable.setRoot(root2);
        subTestTable.setShowRoot(false);

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
    void backToOrder(ActionEvent event) {

        ScreenController.changeScreen(LabScreens.ORDERTEST_SCREEN,mainScreenController.getContent(),mainScreenController);

    }



    @FXML
    void selectTest(ActionEvent event) {

      //  ScreenController.changeScreen(LabScreens.SELECT_TEST_SCREEN,homeController.getContent(),homeController);
    }

    @FXML
    void selectMainTest(){

        selectedMainTest = mainTestTable.getSelectionModel().getSelectedItem().getValue();

        fieldList.clear();
        fieldList.addAll(selectedMainTest.getTestFields());
        subTestTable.refresh();
    }

}

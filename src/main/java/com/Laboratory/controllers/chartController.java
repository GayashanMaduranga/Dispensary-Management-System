package com.Laboratory.controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AmilaWC on 8/20/2017.
 */
public class chartController implements Initializable,SessionListener {

    private Session session;
    private MainScreenController mainScreenController;






    @FXML
    private BarChart<?, ?> testChart;





    @FXML
    private AreaChart<?, ?> area;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;




    @FXML
    void loadChart(ActionEvent event) {

        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Lipid", 50));

        XYChart.Series set11 = new XYChart.Series<>();
        set11.getData().add(new XYChart.Data("Blood Glucose ", 66));

        XYChart.Series set12 = new XYChart.Series<>();
        set12.getData().add(new XYChart.Data("calcium ", 20));

        XYChart.Series set13 = new XYChart.Series<>();
        set13.getData().add(new XYChart.Data("Estrogens ", 36));

        XYChart.Series set14 = new XYChart.Series<>();
        set14.getData().add(new XYChart.Data("Oral Glucose Tolerance Test ", 96));




        testChart.getData().addAll(set1,set11,set12,set13,set14);


        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data("dddd", 50));

        XYChart.Series set21 = new XYChart.Series<>();
        set21.getData().add(new XYChart.Data("Blodlucose ", 66));

        XYChart.Series set22 = new XYChart.Series<>();
        set22.getData().add(new XYChart.Data("calcium ", 20));
        XYChart.Series set23 = new XYChart.Series<>();
        set23.getData().add(new XYChart.Data("Estrogens ", 36));
        XYChart.Series set24 = new XYChart.Series<>();
        set24.getData().add(new XYChart.Data("Oral Glucose Tolerance Test ", 96));


        XYChart.Series set3 = new XYChart.Series<>();
        set3.getData().add(new XYChart.Data("xxxxxx ", 10));
        set3.getData().add(new XYChart.Data("goo ", 446));

        area.getData().addAll(set2,set3,set21,set22,set23,set24);
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = ScreenController.getSession();





    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {

        this.mainScreenController = (MainScreenController) controller;


    }

//    @FXML
//    void changePanel(MouseEvent event) {
//        switch (((JFXButton) event.getSource()).getId()){
//            case "dashboardBT":
//
//                break;
//            case "ordertestBT":
//                ScreenController.changeScreen(controller, LabScreens.DASHBOARD_SCREEN, LabScreens.ORDERTEST_SCREEN);
//                break;
//            case "enter_resultBT":
//                ScreenController.changeScreen(controller, LabScreens.DASHBOARD_SCREEN, LabScreens.ENTERRESULTS_SCREEN);
//                break;
//            case "viewDB_BT":
//                ScreenController.changeScreen(controller, LabScreens.DASHBOARD_SCREEN, LabScreens.VIEWDB_SCREEN);
//                break;
//            case "extraBT":
//                ScreenController.changeScreen(controller, LabScreens.DASHBOARD_SCREEN, LabScreens.EXTRA_SCREEN);
//                break;
//
//        }
//    }
//    ScreenController controller;
//
//    @FXML
//    private JFXButton dashBoardBtn;
//
//    @FXML
//    private JFXButton addEmployeeBtn;
//
//
//
//    @Override
//    public void setScreenParent(ScreenController screenParent) {
//        controller = screenParent;
//
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
//
//    @FXML
//    void changePanel(MouseEvent event) {
//        switch (((JFXButton) event.getSource()).getId()){
//            case "dashboardBT":
//                ScreenController.changeScreen(controller, LabScreens.EXTRA_SCREEN, LabScreens.DASHBOARD_SCREEN);
//
//                break;
//            case "ordertestBT":
//                ScreenController.changeScreen(controller, LabScreens.EXTRA_SCREEN, LabScreens.ORDERTEST_SCREEN);
//                break;
//            case "enter_resultBT":
//                ScreenController.changeScreen(controller, LabScreens.EXTRA_SCREEN, LabScreens.ENTERRESULTS_SCREEN);
//                break;
//            case "viewDB_BT":
//                ScreenController.changeScreen(controller, LabScreens.EXTRA_SCREEN, LabScreens.VIEWDB_SCREEN);
//                break;
//            case "extraBT":
//                ScreenController.changeScreen(controller, LabScreens.DASHBOARD_SCREEN, LabScreens.EXTRA_SCREEN);
//                break;

//        }
//    }
}
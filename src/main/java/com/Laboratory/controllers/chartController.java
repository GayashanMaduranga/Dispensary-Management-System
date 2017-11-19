package com.Laboratory.controllers;

import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXButton;
import com.main.controllers.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import org.hibernate.Session;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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
    private CategoryAxis x;

    @FXML
    private NumberAxis y;




    @FXML
    void loadChart(ActionEvent event) {

        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Lipid", 50));
        set1.getData().add(new XYChart.Data("Blood Glucose ", 66));
        set1.getData().add(new XYChart.Data("calcium ", 20));
        set1.getData().add(new XYChart.Data("Estrogens ", 36));
        set1.getData().add(new XYChart.Data("Oral Glucose Tolerance Test ", 96));
        set1.getData().add(new XYChart.Data("Pregnancy Test ", 236));



        testChart.getData().addAll(set1);

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
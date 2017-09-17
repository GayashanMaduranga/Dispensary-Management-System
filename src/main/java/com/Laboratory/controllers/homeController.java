package com.Laboratory.controllers;


import com.common.ConfirmDialog;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import com.main.Main;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by AmilaWC on 9/16/2017.
 */
public class homeController implements Initializable{

    @FXML
    private GridPane topPane;

    @FXML
    private HBox must;

    @FXML
    private StackPane content;

    @FXML
    private GridPane aside;

    @FXML
    private JFXButton dashboardBT;

    @FXML
    private JFXButton enter_resultBT;

    @FXML
    private JFXButton add_new_testBT;

    @FXML
    private JFXButton labicon;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton viewDB_BT;

    @FXML
    private JFXButton extraBT;

    @FXML
    private JFXButton ordertestBT;



    @FXML
    private GridPane leftPane;

    @FXML
    private JFXButton selectedBtn;




    @FXML
    void changeScene(MouseEvent event) {

        selectedBtn.setDisable(false);
        //leftPane.requestFocus();
        selectedBtn = (JFXButton) event.getSource();
        selectedBtn.setDisable(true);

        switch (selectedBtn.getId()) {
            case "dashboardBT":
                ScreenController.changeScreen(screens.DASHBOARD_SCREEN, content);
                break;
            case "ordertestBT":
                ScreenController.changeScreen(screens.ORDERTEST_SCREEN, content);
                break;
            case "add_new_testBT":
                ScreenController.changeScreen(screens.ADDTEST_SCREEN, content);

                break;
            case "enter_resultBT":
                ScreenController.changeScreen(screens.ENTERRESULTS_SCREEN, content);

                break;
            case "viewDB_BT":
                ScreenController.changeScreen(screens.VIEWDB_SCREEN, content);

                break;
            case "extraBT":
                ScreenController.changeScreen(screens.EXTRA_SCREEN, content);

                break;

            case "add_referBT":
                ScreenController.changeScreen(screens.REFER_SCREEN, content);

                break;
        }
    }


    @FXML
    void logout(ActionEvent event) {
        if (ConfirmDialog.show("", "Are you sure you want to logout?")) {
            Main.createLogin(new Stage());
            Stage s = (Stage) logoutBtn.getScene().getWindow();
            s.close();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

            selectedBtn = dashboardBT;
            selectedBtn.setDisable(true);

          ScreenController.changeScreen(screens.DASHBOARD_SCREEN, content);
    }
}

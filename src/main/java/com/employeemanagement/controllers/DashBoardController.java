package com.employeemanagement.controllers;

import com.common.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class DashBoardController implements Initializable, SessionListener {


    @FXML
    private GridPane grid;

    @FXML
    private JFXButton addEmployeeBtn;

    @FXML
    private JFXButton employeeDetailsBtn;

    @FXML
    private JFXButton addDoctorBtn;

    @FXML
    private JFXButton doctorDetailsBtn;

    @FXML
    private JFXButton webCam;

    private MainScreenController mainScreenController;

    @FXML
    void changeScreen(ActionEvent event) {

        JFXButton selectedBtn = (JFXButton) event.getSource();
        switch (selectedBtn.getId()) {
            case "addEmployeeBtn":
                ScreenController.changeScreen(MyScreens.ADDEMPLOYEE_SCREEN, mainScreenController.getContent(), mainScreenController);

                break;
            case "employeeDetailsBtn":
                ScreenController.changeScreen(MyScreens.SEARCH_EMPLOYEE_SCREEN, mainScreenController.getContent(), mainScreenController);

                break;

            case "addDoctorBtn":
                ScreenController.changeScreen(MyScreens.ADD_DOCTOR_SCREEN, mainScreenController.getContent(), mainScreenController);


                break;
            case "doctorDetailsBtn":
                ScreenController.changeScreen(MyScreens.SEARCH_DOCTOR_SCREEN, mainScreenController.getContent(), mainScreenController);


                break;

            case "webCam":
                initWebCam();
                break;

        }

        if (mainScreenController == null)
            System.out.println("ON");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public void setMainController(SessionListener controller) {
        mainScreenController = (MainScreenController) controller;
    }

//    @FXML
//    void changeScene(MouseEvent event) {
//
//
//
//    }


    public void initWebCam() {

        System.out.println("OK");
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                try (QrCapture qr = new QrCapture()) {
                    showMessage("QR code text is:\n" + qr.getResult() + "");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


            }

            ;
        });
        thread.setDaemon(true);
        thread.start();
    }


    public void showInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void showMessage(String message) {

        showInformation("QR Information Dialog", message);

        System.out.println(message);

    }


}

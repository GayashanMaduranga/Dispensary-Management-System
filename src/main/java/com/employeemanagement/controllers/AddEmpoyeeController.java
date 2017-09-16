package com.employeemanagement.controllers;

import com.EntityClasses.PreviousEmployment;
import com.common.ConfirmDialog;
import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.shape.Circle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class AddEmpoyeeController implements Initializable{



    @FXML
    private Circle empImage;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXTextField nic;

    @FXML
    private JFXTextField contactNumber;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField unitNo;

    @FXML
    private JFXTextField streetAddress;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField zip;

    @FXML
    private TreeTableView<PreviousEmployment> priviousEmployementTable;

    @FXML
    private TreeTableColumn<PreviousEmployment, String> colcompany;

    @FXML
    private TreeTableColumn<PreviousEmployment, String> coljobTitle;

    @FXML
    private JFXTextField company;

    @FXML
    private JFXTextField jobTitle;

    @FXML
    private JFXDatePicker jobFrom;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField supervisor;

    @FXML
    private JFXTextField startingSalary;

    @FXML
    private JFXTextField endingSalary;

    @FXML
    private JFXDatePicker jobTo;

    @FXML
    private JFXTextField companyAddress;

    @FXML
    private TreeTableView<?> SchoolTable;

    @FXML
    private TreeTableColumn<?, ?> colSchoolName;

    @FXML
    private TreeTableColumn<?, ?> colSchoolAddress;

    @FXML
    private JFXTextField schoolName;

    @FXML
    private JFXRadioButton yes;

    @FXML
    private JFXRadioButton no;

    @FXML
    private JFXDatePicker schoolFrom;

    @FXML
    private JFXTextField schoolPhone;

    @FXML
    private JFXDatePicker schoolTo;

    @FXML
    private JFXTextField schoolAddress;



    private FileChooser fileChooser ;
    private Image empimage =null;
    private List<TreeItem<PreviousEmployment>> previouEmploymentList;


    @FXML
    void addEducationHistory(ActionEvent event) {



    }

    @FXML
    void addNewStaff(ActionEvent event) {

    }

    @FXML
    void addPreviousEmployment(ActionEvent event) {
        PreviousEmployment emplymemt = new PreviousEmployment();
        emplymemt.setCompany(company.getText());
        emplymemt.setAddress(companyAddress.getText());
        emplymemt.setJobTitle(jobTitle.getText());
        emplymemt.setPhone(phone.getText());
        emplymemt.setSupervisor(supervisor.getText());
        emplymemt.setFromDate(Date.valueOf(jobFrom.getValue()));
        emplymemt.setToDate(Date.valueOf(jobTo.getValue()));

        previouEmploymentList.add(new TreeItem<>(emplymemt));

        priviousEmployementTable.getRoot().getChildren().clear();
        priviousEmployementTable.getRoot().getChildren().addAll(previouEmploymentList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileChooser = new FileChooser();
        empImage.setFill(new ImagePattern(new Image("/com/Images/user1600.png")));
        previouEmploymentList = new ArrayList<>();

        colcompany.setCellValueFactory(param -> param.getValue().getValue().companyProperty());
        coljobTitle.setCellValueFactory(param -> param.getValue().getValue().jobTitleProperty());

        PreviousEmployment employment = new PreviousEmployment();
        employment.setCompany("company");
        employment.setJobTitle("Job Title");
        TreeItem<PreviousEmployment> root1 = new TreeItem<>();

        priviousEmployementTable.setRoot(root1);
        priviousEmployementTable.setShowRoot(false);





    }


    @FXML
    void uplodePhoto(ActionEvent event) {
        fileChooser.setTitle("Select Employee Image");




//        fileChooser.getExtensionFilters().addAll(
//
//                new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));



        File selectedFile = fileChooser.showOpenDialog(null);


        try {

            empimage = new Image(selectedFile.toURI().toString());
            empImage.setFill(new ImagePattern(empimage));

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}

package com.employeemanagement.controllers;

import com.common.ConfirmDialog;
import com.jfoenix.controls.JFXButton;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 8/13/2017.
 */
public class AddEmpoyeeController implements Initializable{

    @FXML
    private Circle empImage;



    FileChooser fileChooser ;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileChooser = new FileChooser();
        empImage.setFill(new ImagePattern(new Image("/com/Images/user1600.png")));


    }


    @FXML
    void uplodePhoto(ActionEvent event) {
        fileChooser.setTitle("Select Employee Image");




//        fileChooser.getExtensionFilters().addAll(
//
//                new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));



        File selectedFile = fileChooser.showOpenDialog(null);


        try {

            Image image = new Image(selectedFile.toURI().toString());
            empImage.setFill(new ImagePattern(image));

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}

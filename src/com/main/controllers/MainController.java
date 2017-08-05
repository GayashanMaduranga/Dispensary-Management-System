package com.main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class MainController {

    @FXML
    private Button rand_btn;

    @FXML
    private Label myMsg;

    public void generateRandom(ActionEvent actionEvent){

        Random rand = new Random();
        int myRandInt = rand.nextInt(50) + 1;
        myMsg.setText(Integer.toString(myRandInt));
        System.out.println(myRandInt);
    }
}

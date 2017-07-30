package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class MainController {

    @FXML
    private Label myMsg;

    public void generateRandom(){

        Random rand = new Random();
        int myRandInt = rand.nextInt(50) + 1;
        myMsg.setText(Integer.toString(myRandInt));
        System.out.println(myRandInt);
    }
}

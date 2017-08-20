package com.Laboratory.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by AmilaWC on 8/19/2017.
 */

    public class main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("orderTest.fxml"));

            //Parent root = FXMLLoader.load(getClass().getResource("dashBoard.fxml"));

            primaryStage.setTitle("MainWindow");
            primaryStage.setScene(new Scene(root, 1579, 715));
            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
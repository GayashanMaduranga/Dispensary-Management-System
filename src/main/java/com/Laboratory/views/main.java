package com.Laboratory.views;

import com.Laboratory.controllers.LabScreens;
import com.common.ScreenController;
import com.main.controllers.MainScreens;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by AmilaWC on 8/19/2017.
 */

    public class main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{



            ScreenController mainContainer = new ScreenController();

//            mainContainer.loadScreen(LabScreens.DASHBOARD_SCREEN.getId(), LabScreens.DASHBOARD_SCREEN.getPath());
//            mainContainer.setScreen(LabScreens.DASHBOARD_SCREEN.getId());
            mainContainer.loadScreen(MainScreens.HOME_SCREEN.getId(), MainScreens.HOME_SCREEN.getPath());
            mainContainer.setScreen(MainScreens.HOME_SCREEN.getId());
            Parent root = mainContainer.getScreen(MainScreens.HOME_SCREEN.getId()).getParent();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();





//            Parent root = FXMLLoader.load(getClass().getResource("orderTest.fxml"));

            //Parent root = FXMLLoader.load(getClass().getResource("dashBoard.fxml"));

//            primaryStage.setTitle("MainWindow");
//            primaryStage.setScene(new Scene(root, 1579, 715));
//            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
package com.Laboratory.views;

import com.Laboratory.controllers.screens;
import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by AmilaWC on 8/19/2017.
 */

    public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


//            ScreenController mainContainer = new ScreenController();
//
//            mainContainer.loadScreen(screens.DASHBOARD_SCREEN.getId(), screens.DASHBOARD_SCREEN.getPath());
//            mainContainer.setScreen(screens.DASHBOARD_SCREEN.getId());
//            Parent root = mainContainer.getScreen(screens.DASHBOARD_SCREEN.getId()).getParent();
//
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();

//
        try {

    Parent root = FXMLLoader.load(getClass().getResource(screens.HOME_SCREEN.getPath()));
    Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome Dispensary");
//            stage.getIcons().add(new Image("/com/Images/Hospital-management-system.png"));
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(715.0);
            primaryStage.setMinWidth(1299.0);
            primaryStage.show();
}

catch (IOException ex) {
        ex.printStackTrace();
        }
    }








//            Parent root = FXMLLoader.load(getClass().getResource("orderTest.fxml"));
//
//            Parent root = FXMLLoader.load(getClass().getResource("/com/Laboratory/addTest.fxml"));
//
//            primaryStage.setTitle("MainWindow");
//            primaryStage.setScene(new Scene(root, 1579, 715));
//            primaryStage.show();
//
//            primaryStage.setOnCloseRequest(event -> System.exit(0));
//       }


    public static void main(String[] args) {
        launch(args);
    }
}


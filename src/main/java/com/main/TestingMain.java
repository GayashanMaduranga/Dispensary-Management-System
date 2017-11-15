package com.main;

/**
 * Created by gayashan on 8/13/2017.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class TestingMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        ScreenController mainContainer = new ScreenController();
//
//
//        mainContainer.loadScreen(MyScreens.DASHBOARD_SCREEN.getId(), MyScreens.DASHBOARD_SCREEN.getPath());
//
//        mainContainer.setScreen(MyScreens.DASHBOARD_SCREEN.getId());
//        Parent root = mainContainer.getScreen(MyScreens.DASHBOARD_SCREEN.getId()).getParent();


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/labinventory/LabMaintance.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Welcome New Dispensary");
//            stage.getIcons().add(new Image("/com/Images/Hospital-management-system.png"));
            stage.setMaximized(false);
            stage.setMinHeight(715.0);
            stage.setMinWidth(1299.0);
            stage.setOnCloseRequest(event -> System.exit(0));
            stage.show();
        } catch (IOException ex) {
//            Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
package com.employeemanagement;

/**
 * Created by gayashan on 8/13/2017.
 */


import com.employeemanagement.controllers.MyScreens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class TestStage extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        try {

            Parent root = FXMLLoader.load(getClass().getResource(MyScreens.MAIN_SCREEN.getPath()));
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
            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

}
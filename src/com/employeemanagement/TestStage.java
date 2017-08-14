package com.employeemanagement;

/**
 * Created by gayashan on 8/13/2017.
 */


import com.employeemanagement.controllers.MyScreens;
import com.test.ScreenController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestStage extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(MyScreens.DASHBOARD_SCREEN, MyScreens.DASHBOARD_SCREEN_FXML);

        mainContainer.setScreen(MyScreens.DASHBOARD_SCREEN);

        Parent root = mainContainer.getScreen(MyScreens.DASHBOARD_SCREEN).getParent();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


//        Parent root = FXMLLoader.load(getClass().getResource("/com/employeemanagement/views/DashBoard.fxml"));
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
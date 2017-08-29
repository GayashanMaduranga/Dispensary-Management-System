package com.main;

import com.common.ScreenController;
import com.employeemanagement.controllers.MyScreens;
import com.main.controllers.MainScreens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        createLogin(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void createLogin(Stage primaryStage){

        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(MainScreens.LOGIN_SCREEN.getId(), MainScreens.LOGIN_SCREEN.getPath());
        mainContainer.setScreen(MainScreens.LOGIN_SCREEN.getId());
        Parent root = mainContainer.getScreen(MainScreens.LOGIN_SCREEN.getId()).getParent();

        primaryStage.setTitle("Login");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //code to center the stage on-screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }

}

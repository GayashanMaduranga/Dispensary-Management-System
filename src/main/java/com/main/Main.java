package com.main;

import com.common.ScreenController;
import com.main.controllers.MainScreens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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

    public static void createRegisterWindow(Stage primaryStage, Stage owner, String FXML_location) {

        Parent root = null;
        Object object = new Object();

        try {
            root = FXMLLoader.load(object.getClass().getResource(FXML_location));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setAlwaysOnTop(true);
//        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(255, 255, 255, 0.6));
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(491);
        primaryStage.setMinHeight(615);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
        owner.setFullScreen(true);
//        Runnable task = () -> {
//            try {
//                Thread.sleep(2000);
//                Platform.runLater(() -> owner.setFullScreen(true));
//                System.out.println("Thread done");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//        new Thread(task).start();
        primaryStage.toFront();
//        primaryStage.toFront();
        //code to center the stage on-screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}

package com.financemanagement.controllers.MainController;

/**
 * Created by Kavindu on 9/16/2017.
 */

import com.common.ScreenController;
import com.financemanagement.controllers.FinanceScreens;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ScreenController mainContainer = new ScreenController();


        mainContainer.loadScreen(FinanceScreens.LOGINFORM_SCREEN.getId(), FinanceScreens.FINANCE_MAIN_SCREEN.getPath());

        mainContainer.setScreen(FinanceScreens.FINANCE_MAIN_SCREEN.getId());
        Parent root = mainContainer.getScreen(FinanceScreens.FINANCE_MAIN_SCREEN.getId()).getParent();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


//        Parent root = FXMLLoader.load(getClass().getResource(""));
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
package com.suppliermanagement;

import com.common.ScreenController;


import com.employeemanagement.controllers.MyScreens;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * Created by Naveen Luke Fernando on 2017-08-14.
 */



public class Main_Naveen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(SupplierScreens.PURCHASE_SCREEN.getId(), SupplierScreens.PURCHASE_SCREEN.getPath());

        mainContainer.setScreen(SupplierScreens.PURCHASE_SCREEN.getId());

        Parent root = mainContainer.getScreen(SupplierScreens.PURCHASE_SCREEN.getId()).getParent();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();





    }






    public static void main(String[] args) {
        launch(args);
    }
}

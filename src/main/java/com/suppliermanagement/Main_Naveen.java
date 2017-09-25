package com.suppliermanagement;

import com.common.ScreenController;


import com.employeemanagement.controllers.MyScreens;
import com.suppliermanagement.controllers.SupplierScreens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Naveen Luke Fernando on 2017-08-14.
 */



public class Main_Naveen extends Application {

    @Override
    public void start(Stage stage) throws Exception{


        //ScreenController mainContainer = new ScreenController();
       // mainContainer.loadScreen(SupplierScreens.DASHBOARD_SCREEN.getId(), SupplierScreens.DASHBOARD_SCREEN.getPath());

        //mainContainer.setScreen(SupplierScreens.DASHBOARD_SCREEN.getId());

       // Parent root = mainContainer.getScreen(SupplierScreens.DASHBOARD_SCREEN.getId()).getParent();

        //Scene scene = new Scene(root);
        //primaryStage.setScene(scene);
        //primaryStage.show();



        try {

            Parent root = FXMLLoader.load(getClass().getResource(SupplierScreens.SUPPLIER_MAIN_VIEW.getPath()));
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

package com.common;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertDialog {

    public static void show(String title, String msg){

        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);
        stage.setMinWidth(400);
        stage.setMinHeight(150);

        Label lbl = new Label(msg);
        Button btnClose = new Button("Close");
        btnClose.setMinWidth(80);
        btnClose.setOnAction(e -> stage.close());

        VBox vBox =  new VBox(20);
        vBox.setPadding(new Insets(20,0,20,0));
        vBox.getChildren().addAll(lbl, btnClose);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("com/patientmanagement/views/styles.css");
        stage.setScene(scene);
        stage.showAndWait();

    }
}

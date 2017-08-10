package com;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertDialog {

    public static void show(String title, String msg){

        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);
        stage.setMinHeight(150);

        Label lbl = new Label(msg);
        Button btnClose = new Button("Close");
        btnClose.setOnAction(e -> stage.close());

        VBox vBox =  new VBox(10);
        vBox.getChildren().addAll(lbl, btnClose);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.showAndWait();

    }
}

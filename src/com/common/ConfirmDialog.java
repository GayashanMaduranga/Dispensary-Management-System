package com.common;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Damsith on 7/29/2017.
 */
public class ConfirmDialog {

    private static boolean answer = false;

    public static boolean show(String title, String msg) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);
        stage.setMinHeight(150);

        Label lbl = new Label(msg);
        Button btnYes = new Button("Yes");
        btnYes.setMinWidth(80);
        Button btnNo = new Button("No");
        btnNo.setMinWidth(80);

        btnYes.setOnAction(e -> {
            answer = true;
            stage.close();
        });

        btnNo.setOnAction(e -> {
            answer = false;
            stage.close();
        });

        VBox vBox = new VBox(20);
        HBox hBox = new HBox(30);
        hBox.getChildren().addAll(btnYes, btnNo);
        vBox.getChildren().addAll(lbl, hBox);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("com/patientmanagement/views/css2.css");
        stage.setScene(scene);
        stage.showAndWait();

        return answer;
    }
}

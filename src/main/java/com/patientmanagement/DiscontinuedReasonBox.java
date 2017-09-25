package com.patientmanagement;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Damsith on 7/29/2017.
 */
public class DiscontinuedReasonBox {

    private static String discontinuedAnswer = "";
    private static boolean answer = false;

    public static boolean show() {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMinWidth(600);
        stage.setMinHeight(300);

        Label lbl = new Label("Please choose the reason for discontinuing the medication");
        lbl.setStyle("-fx-font-size: 17px");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setMinWidth(200);
        choiceBox.getItems().add("Adverse Reaction");
        choiceBox.getItems().add("Allergic Reaction");
        choiceBox.getItems().add("Declined");
        choiceBox.getItems().add("Excluded");
        choiceBox.getItems().add("Not Required");
        choiceBox.getItems().add("Not Specified");
        choiceBox.setValue("Allergic Reaction");

        Button btnYes = new Button("Ok");
        btnYes.setMinWidth(90);
        Button btnNo = new Button("cancel");
        btnNo.setMinWidth(90);

        btnYes.setOnAction(e -> {
            answer = true;
            discontinuedAnswer = choiceBox.getValue();
            stage.close();

        });

        btnNo.setOnAction(e -> {
            answer = false;
            stage.close();
        });


        VBox vBox = new VBox(40);
        HBox hBox = new HBox(30);
        hBox.getChildren().addAll(btnYes, btnNo);
        vBox.getChildren().addAll(lbl, choiceBox, hBox);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("/com/patientmanagement/styles.css");
        stage.setScene(scene);
        stage.showAndWait();

        return answer;

    }

    public static String getDiscontinuedAnswer() {
        return discontinuedAnswer;
    }
}

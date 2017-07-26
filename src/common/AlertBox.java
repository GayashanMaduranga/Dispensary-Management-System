package common;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    public static void display(String title, String msg){

        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);

        Label lbl = new Label(msg);
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> stage.close());

        VBox vBox =  new VBox(10);
        vBox.getChildren().addAll(lbl, closeBtn);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.showAndWait();

    }
}

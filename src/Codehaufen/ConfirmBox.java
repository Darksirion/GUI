/*
 * Copyright (c) 17.5.2015
 * Team Turing
 */

package Codehaufen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(75);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("yes");
        Button noButton = new Button("no");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e  -> {
            answer = false;
            window.close();
        });

        BorderPane boarderpane = new BorderPane();


        HBox layout = new HBox();
        layout.getChildren().addAll(yesButton,noButton);
        layout.setAlignment(Pos.CENTER);    //pending einfügen
        boarderpane.setCenter(layout);



        Scene scene= new Scene(boarderpane);
        window.setScene(scene);
        window.showAndWait();

    return answer;
    }

}

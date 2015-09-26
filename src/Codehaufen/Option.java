package Codehaufen;///*
// * Copyright (c) 17.5.2015
// * Team Turing
// */
//
//package Controll;
//
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//public class Option {
//
//
//    public static void display(){
//        Stage window = new Stage();
//
//        window.initModality(Modality.APPLICATION_MODAL);
//        window.setTitle("Option");
//        window.setMinWidth(250);
//
//        Label label = new Label();
//        Button saveButton = new Button("Save");
//        saveButton.setOnAction(e -> window.close());
//
//        Button cancelutton = new Button("Cancel");
//
//        //VBox layout = new VBox(10);
//        //layout.getChildren().addAll(label, saveButton, cancelutton);
//        //layout.setAlignment(Pos.CENTER);
//
//       Parent option = FXMLLoader.load(getClass().getResource("option.fxml"));
//
//        Scene scene= new Scene(option, 400,300);
//        window.setScene(scene);
//        window.showAndWait();
//
//
//    }
//
//}

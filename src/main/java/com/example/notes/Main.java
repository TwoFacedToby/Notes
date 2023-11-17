package com.example.notes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox vbox = new VBox();
        vbox.setId("cannister"); // Set the ID for the VBox

        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setTitle("JavaFX CSS Example");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
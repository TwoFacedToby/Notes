package com.example.notes;

import com.example.notes.Navigation.NavigationEvents;
import com.example.notes.ViewElements.Window;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        Window window = new Window();
    }

    public static void main(String[] args) {
        launch();
    }
}
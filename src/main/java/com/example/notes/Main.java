package com.example.notes;

import com.example.notes.FileHandling.Directory;
import com.example.notes.FileHandling.FileHandler;
import com.example.notes.FileHandling.FileLoader;
import com.example.notes.Navigation.NavigationEvents;
import com.example.notes.ViewElements.Window;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //FileHandler.createDirectory("TestDirectory/Test2", "Test3");
        FileLoader.get();
        Window window = new Window();
        NavigationEvents.get().setCollections(FileLoader.get().getCollections());

        if(NavigationEvents.get().getCollections().size() > 0)
            window.getView().collection.setDirectory(NavigationEvents.get().getCollections().get(0));
    }

    public static void main(String[] args) {
        launch();
    }
}
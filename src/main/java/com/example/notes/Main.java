package com.example.notes;

import com.example.notes.FileHandling.FileLoader;
import com.example.notes.Navigation.NavigationEvents;
import com.example.notes.ViewElements.Window;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //FileHandler.createDirectory("TestDirectory/Test2", "Test3");
        FileLoader.get();
        Window window = new Window(null);
        if(FileLoader.get().getCollections().size() > 0){
            window.getView().getNavEvents().setCollection(FileLoader.get().getCollections().get(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
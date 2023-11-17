package com.example.notes.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window extends Stage {

    private final double width = 1600, height = 900;
    private final String title = "Notes";
    private Scene scene;

    public Window(){
        View view = new View();
        scene = new Scene(view, width, height);
        setCSS();
        setScene(scene);
        setTitle(title);
        setOnCloseRequest(this::onClose);
        show();
    }
    private void onClose(WindowEvent event){
        //event.consume();
    }

    private void setCSS(){
        scene.getStylesheets().add(getClass().getResource("/css/PageElements.css").toExternalForm());
    }

}

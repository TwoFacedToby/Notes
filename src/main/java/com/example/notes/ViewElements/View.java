package com.example.notes.ViewElements;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends BorderPane {
    private Stage stage;

    public View(Stage stage){
        this.stage = stage;
        setId("view");
        setTop(new TopBar(stage));
        setLeft(new Sidebar());
        setRight(new Sidebar());
        setCenter(new Page());
    }
}

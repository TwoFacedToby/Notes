package com.example.notes.ViewElements;

import com.example.notes.Enums.Direction;
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
        setLeft(new Sidebar(Direction.W, null));
        setRight(new Sidebar(Direction.E, null));
        setCenter(new Page());
    }
}

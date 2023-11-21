package com.example.notes.ViewElements;

import com.example.notes.Enums.Direction;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends BorderPane {
    private Stage stage;

    public CollectionView collection;
    public View(Stage stage){
        this.stage = stage;
        collection = new CollectionView(this);

        setId("view");
        setTop(new TopBar(this));
        setLeft(new Sidebar(Direction.W, collection));
        setRight(new Sidebar(Direction.E, null));
        setCenter(new Page());
    }
    public Stage getStage(){
        return stage;
    }
}

package com.example.notes.ViewElements;

import com.example.notes.Enums.Direction;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Sidebar extends BorderPane {
    private Direction direction;
    private double xOffset;

    public Sidebar(Direction direction, Node content){
        this.direction = direction;
        setId("side-bar");
        setCenter(content);
        init();
    }
    private void init(){
        VBox handle = new VBox();
        handle.setId("handle");
        if(direction == Direction.E){
            setLeft(handle);
            setCursor(Cursor.W_RESIZE);
        }
        else{
            setRight(handle);
            setCursor(Cursor.E_RESIZE);
        }

        setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
        });
        setOnMouseDragged((MouseEvent event) -> {
            double xDif = event.getSceneX() - xOffset;
            xOffset = event.getSceneX();
            if(direction == Direction.E) setPrefWidth(getPrefWidth()-xDif);
            else setPrefWidth(getPrefWidth()+xDif);

        });
    }
}

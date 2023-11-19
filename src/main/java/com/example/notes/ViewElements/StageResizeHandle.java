package com.example.notes.ViewElements;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageResizeHandle extends GridPane {
    private final double handleSize = 0.5;
    private final double handleExpandedSize = 10;
    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage;
    public StageResizeHandle(View view, Stage stage){
        this.stage = stage;
        GridPane.setHgrow(view, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setVgrow(view, javafx.scene.layout.Priority.ALWAYS);
        setGrid(view);
        setMouseTransparent(false);
        setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
    }
    private void setGrid(View view) {
        setStyle("-fx-background-color: rgba(52,52,52,0.02)");
        add(view, 0, 0);
        add(new Handle(Direction.E), 1, 0);
        add(new Handle(Direction.S), 0, 1);
        add(new Handle(Direction.SE), 1, 1);
    }
    private void setGiddyGrid(View view) {
        setStyle("-fx-background-color: transparent");
        add(new Handle(Direction.NW), 0, 0);
        add(new Handle(Direction.N), 1, 0);
        add(new Handle(Direction.NE), 2, 0);
        add(new Handle(Direction.W), 0, 1);
        add(view, 1, 1);
        add(new Handle(Direction.E), 2, 1);
        add(new Handle(Direction.SW), 0, 2);
        add(new Handle(Direction.S), 1, 2);
        add(new Handle(Direction.SE), 2, 2);
    }

    enum Direction{
        NW,
        N,
        NE,
        W,
        E,
        SW,
        S,
        SE
    }

    class Handle extends VBox {
        public Handle(Direction dir){
            setMouseTransparent(false);
            setMinSize(handleSize, handleSize);
            setLayoutX(-10);
            switch (dir){
                case E -> {
                    setCursor(Cursor.E_RESIZE);
                }
                case N ->{
                    setCursor(Cursor.N_RESIZE);
                }
                case W -> {
                    setCursor(Cursor.W_RESIZE);
                }
                case S -> {
                    setCursor(Cursor.S_RESIZE);
                }
                case NW -> {
                    setCursor(Cursor.NW_RESIZE);
                }
                case NE -> {
                    setCursor(Cursor.NE_RESIZE);
                }
                case SE -> {
                    setCursor(Cursor.SE_RESIZE);
                }
                case SW -> {
                    setCursor(Cursor.SW_RESIZE);
                }
            }
            switch (dir){
                case E:
                case NE:
                case SE:
                    setOnMouseEntered(e->{
                        setStyle("-fx-background-color: #737373");
                        setMinSize(handleSize+handleExpandedSize, handleSize);
                        stage.setWidth(stage.getWidth()+handleExpandedSize);
                    });
                    setOnMouseExited(e->{
                        setStyle("-fx-background-color: transparent");
                        setMinSize(handleSize, handleSize);
                        stage.setWidth(stage.getWidth()-handleExpandedSize);
                    });
                    break;
                case S:
                case SW:
                    setOnMouseEntered(e->{
                        setStyle("-fx-background-color: #737373");
                        setMinSize(handleSize, handleSize+handleExpandedSize);
                        stage.setHeight(stage.getHeight()+handleExpandedSize);
                    });
                    setOnMouseExited(e->{
                        setStyle("-fx-background-color: transparent");
                        setMinSize(handleSize, handleSize);
                        stage.setHeight(stage.getHeight()-handleExpandedSize);
                    });
                    break;
            }
            setStyle("-fx-background-color: transparent");

            setOnMouseDragged((MouseEvent event) -> {
                double xDif = event.getSceneX() - xOffset;
                double yDif = event.getSceneY() - yOffset;

                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
                scale(dir, xDif, yDif);
            });
        }
        private void scale(Direction dir, double xDif, double yDif){
            double newHeight;
            double newWidth;
            //Horizontal
            switch (dir){
                case E:
                case NE:
                case SE:

                    newWidth = stage.getWidth() + xDif;
                    stage.setWidth(newWidth);
                    break;
                case W:
                case NW:
                case SW:
                    newWidth = stage.getWidth() - xDif;
                    stage.setWidth(newWidth);
                    stage.setX(stage.getX() + xDif);
                    break;
            }
            //Vertical
            switch (dir){
                case N:
                case NE:
                case NW:
                    newHeight = stage.getHeight() - yDif;
                    stage.setHeight(newHeight);
                    stage.setY(stage.getY() + yDif);
                    break;
                case S:
                case SE:
                case SW:
                    newHeight = stage.getHeight() + yDif;
                    stage.setHeight(newHeight);
                    break;
            }
        }
    }
}

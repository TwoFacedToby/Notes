package com.example.notes.ViewElements;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BoolBox extends HBox {

    private Label label;
    private boolean value;
    private VBox box;
    private VBox checkmark;


    public BoolBox(){

        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #eeeeee");
        label = new Label();
        label.setStyle("-fx-text-fill: #eeeeee");
        setAlignment(Pos.CENTER);
        setSpacing(20);
        checkmark = new VBox();
        checkmark.setPrefSize(12, 12);
        checkmark.setStyle("-fx-background-color: #909090");
        box = new VBox();
        box.setPrefSize(16, 16);
        box.setStyle("-fx-background-color: transparent; -fx-border-color: #eeeeee; -fx-alignment: center; -fx-padding: 2");

        getChildren().addAll(label, box);

        box.setOnMouseClicked(e->setValue(!value));


    }

    public void setLabelText(String text){
        label.setText(text);
    }
    public void setValue(boolean value){
        this.value = value;

        box.getChildren().clear();
        if(value) box.getChildren().add(checkmark);

    }
    public boolean getValue(){
        return this.value;
    }


}

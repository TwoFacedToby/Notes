package com.example.notes.ViewElements;

import com.example.notes.FileHandling.Directory;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;

public class FoldingView extends VBox {

    private Directory directory;
    private FoldingView parent;

    private Button button;
    private Label buttonLabel;
    private Label arrow;
    private VBox children;
    private View view;


    public FoldingView(Directory directory, FoldingView parent, View view){
        this.view = view;
        this.directory = directory;
        this.parent = parent;
        setId("folding-view");
        init();
    }
    private void init(){
        button = new Button();
        children = new VBox();
        children.setId("folding-view-inner");
        getChildren().add(button);
        button.setOnAction(e->openClose());
        button.setId("folding-view-button");
        button.setMaxWidth(Double.MAX_VALUE);
        arrow = new Label("V");
        arrow.setRotate(90);
        arrow.setStyle("-fx-font-size: 12; -fx-text-fill: #aaaaaa; ");
        arrow.setScaleX(2);
        buttonLabel = new Label();
        buttonLabel.setId("folding-view-label");
        buttonLabel.setStyle(button.getStyle() + "; -fx-font-weight: bold");
        BorderPane graphic = new BorderPane();
        graphic.setLeft(buttonLabel);
        graphic.setRight(arrow);
        button.setGraphic(graphic);
        setDirectory(directory);

    }
    private void openClose(){
        if(getChildren().contains(children)) {
            getChildren().remove(children);
            arrow.setRotate(90);
        }
        else {
            getChildren().add(children);
            arrow.setRotate(0);
        }
    }
    public void setDirectory(Directory directory){
        this.directory = directory;
        updateName();
        updateChildrenViews();
    }
    public void updateName(){
        buttonLabel.setText(directory.getName());
    }
    public void updateChildrenViews(){

        children.getChildren().clear();

        ArrayList<File> childFiles = directory.getFiles();
        for(int i = 0; i < childFiles.size(); i++){
            children.getChildren().add(new FoldingFile(childFiles.get(i)));
        }
        ArrayList<Directory> childDirectories = directory.getChildren();
        for(int i = 0; i < childDirectories.size(); i++){
            children.getChildren().add(new FoldingView(childDirectories.get(i), this, view));
        }

    }

    class FoldingFile extends VBox {
        private File file;
        public FoldingFile(File file){
            this.file = file;
            setId("folding-view-file");
            Button button = new Button();
            button.setId("folding-view-label");
            button.setText(file.getName());
            getChildren().add(button);
        }



    }
}

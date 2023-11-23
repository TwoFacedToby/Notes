package com.example.notes.ViewElements;

import com.example.notes.Enums.Direction;
import com.example.notes.FileHandling.Directory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View extends BorderPane {
    private Stage stage;
    private Directory currentCollection;

    private CollectionView collection;
    public View(Stage stage, Directory directory){
        this.currentCollection = directory;
        this.stage = stage;
        collection = new CollectionView(this);

        setId("view");
        setTop(new TopBar(this));
        setLeft(new Sidebar(Direction.W, collection));
        setRight(new Sidebar(Direction.E, null));
        setCenter(new Page());
    }
    public Directory getCurrentCollection(){
        return currentCollection;
    }
    public void resetCollections(){
        setCurrentCollection(getCurrentCollection());
        collection = new CollectionView(this);
    }

    public void setCurrentCollection(Directory currentCollection) {
        this.currentCollection = currentCollection;
        collection.setDirectory(currentCollection);
        stage.setTitle(currentCollection.getName());
    }

    public Stage getStage(){
        return stage;
    }
}

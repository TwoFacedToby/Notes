package com.example.notes.ViewElements;

import com.example.notes.Enums.Direction;
import com.example.notes.FileHandling.Directory;
import com.example.notes.Navigation.NavigationEvents;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends BorderPane {
    private Window stage;
    private Directory currentCollection;
    private NavigationEvents navEvents;
    private CollectionView collection;
    public View(Window stage, Directory directory){
        navEvents = new NavigationEvents(this);
        this.currentCollection = directory;
        this.stage = stage;
        init();

    }
    private void init(){
        setId("view");
        setTop(new TopBar(this));
        collection = new CollectionView(this);
        if(currentCollection != null){
            collection.setDirectory(currentCollection);
            setLeft(new Sidebar(Direction.W, collection));
            setRight(new Sidebar(Direction.E, null));
            setCenter(new Page());
        }
        else {
            Label label = new Label("No open collection, Please Go to File->Open to open a collection");
            VBox holder = new VBox();
            holder.setAlignment(Pos.CENTER);
            holder.getChildren().add(label);
            setCenter(holder);
        }
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

    public NavigationEvents getNavEvents() {
        return navEvents;
    }
}

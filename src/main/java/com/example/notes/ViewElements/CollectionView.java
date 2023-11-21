package com.example.notes.ViewElements;

import com.example.notes.FileHandling.Directory;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CollectionView extends ScrollPane {
    private Directory collection;
    private View view;
    public CollectionView(View view){
        this.view = view;
        setId("collection");
    }
    public void setDirectory(Directory collection){
        this.collection = collection;
        FoldingView foldingView = new FoldingView(collection, null, view);
        VBox holder = new VBox();
        holder.setId("collection-flow");
        holder.getChildren().add(foldingView);
        setContent(holder);
    }

}

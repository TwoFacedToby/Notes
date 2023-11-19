package com.example.notes.ViewElements;

import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;

public class CollectionView extends FlowPane {
    private String name;
    public CollectionView(String name){
        this.name = name;
        setId("collection");
        setOrientation(Orientation.VERTICAL);
        if(name != null){
            init();
        }
    }
    private void init(){

    }

}

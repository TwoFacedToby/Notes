package com.example.notes.ViewElements;

import javafx.scene.layout.BorderPane;

public class View  extends BorderPane {

    
    public View(){
        setId("view");
        setTop(new TopBar());
        setLeft(new Sidebar());
        setRight(new Sidebar());
    }



}

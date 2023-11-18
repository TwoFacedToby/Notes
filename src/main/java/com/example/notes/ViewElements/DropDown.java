package com.example.notes.ViewElements;

import com.example.notes.Navigation.Link;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class DropDown extends ComboBox {
    private Link link;
    public DropDown(Link link){
        setId("drop-down");
        this.link = link;
    }

    private void init(){
        Label label = new Label(link.getName());
        label.setId("drop-down-label");
        setValue(label);
        getItems().add(label);
        for(int i = 0; i < link.getChildren().size(); i++){
            DropDown dd = new DropDown(link.getChildren().get(i));
            getItems().add(dd);
        }


    }
}

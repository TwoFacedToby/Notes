package com.example.notes.ViewElements;

import com.example.notes.Navigation.Link;
import com.example.notes.Navigation.NavigationEvents;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TopBar extends HBox {

    NavigationEvents navEvents;
    public TopBar(){
        init();
    }

    private void init(){
        setId("top-bar");
        navEvents = NavigationEvents.get();
        getChildren().addAll(initDropDowns(NavigationEvents.getNav()));


    }
    private ArrayList<DropDown> initDropDowns(ArrayList<Link> links) {
        ArrayList<DropDown> dropDowns = new ArrayList<>();
        for(int i = 0; i < links.size(); i++){
            dropDowns.add(new DropDown(links.get(i)));
        }
        return dropDowns;
    }
}

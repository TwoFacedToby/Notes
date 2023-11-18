package com.example.notes.ViewElements;

import com.example.notes.Navigation.Link;
import com.example.notes.Navigation.NavigationEvents;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TopBar extends HBox {

    NavigationEvents navEvents;
    ArrayList<Link> ddLinks;
    public TopBar(){
        init();
    }

    private void init(){
        setId("top-bar");
        navEvents = NavigationEvents.get();
        ddLinks = NavigationEvents.getNav();
        getChildren().addAll(initDropDowns());


    }
    private ArrayList<DropDown> initDropDowns() {
        ArrayList<DropDown> dd = new ArrayList<>();


        return dd;
    }
}

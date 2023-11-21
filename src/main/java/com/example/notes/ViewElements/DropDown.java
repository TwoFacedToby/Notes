package com.example.notes.ViewElements;

import com.example.notes.Navigation.Link;
import com.example.notes.Navigation.NavigationEvents;
import javafx.scene.control.*;

public class DropDown extends MenuButton {
    private Link link;
    private View view;
    public DropDown(Link link, View view){
        this.view = view;
        setId("drop-down");
        this.link = link;
        init();
    }

    private void init(){

        setText(link.getName());
        for(int i = 0; i < link.getChildren().size(); i++){
            MenuItem item = new MenuItem();
            item.setId("drop-down-item");
            item.setText(link.getChildren().get(i).getName());
            int final_index = i;
            item.setOnAction(e-> NavigationEvents.get().onNavClicked(link.getChildren().get(final_index), view));
            getItems().add(item);
        }


    }
}

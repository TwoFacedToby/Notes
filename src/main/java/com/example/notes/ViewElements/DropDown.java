package com.example.notes.ViewElements;

import com.example.notes.Navigation.Link;
import com.example.notes.Navigation.NavigationEvents;
import javafx.scene.control.*;

public class DropDown extends MenuButton {
    private Link link;
    public DropDown(Link link){
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
            item.setOnAction(e-> NavigationEvents.onNavClicked(link.getChildren().get(final_index)));
            getItems().add(item);
        }


    }
}

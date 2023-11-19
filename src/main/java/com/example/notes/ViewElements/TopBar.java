package com.example.notes.ViewElements;

import com.example.notes.Navigation.Link;
import com.example.notes.Navigation.NavigationEvents;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TopBar extends BorderPane {

    private double xOffset = 0;
    private double yOffset = 0;
    private View view;
    private Stage stage;
    public TopBar(View view){
        this.view = view;
        this.stage = view.getStage();
        init();
    }

    private void init(){
        setId("top-bar");
        setLeft(initDropDowns(NavigationEvents.getNav()));
        setRight(initButtons());
        initWindowDrag();

    }

    private HBox initButtons(){
        HBox buttons = new HBox();
        Button close = new Button("X");
        close.setId("top-bar-button");
        close.setOnAction(e->{stage.close();});
        String closeDefaultStyle = close.getStyle();;
        close.setOnMouseEntered(e->{close.setStyle(closeDefaultStyle + "; -fx-background-color: #be1717");});
        close.setOnMouseExited(e->{close.setStyle(closeDefaultStyle);});
        Button size = new Button("■");
        size.setId("top-bar-button");
        size.setOnAction(e->{stage.setMaximized(!stage.isMaximized());});
        Button minimize = new Button("-");
        minimize.setId("top-bar-button");
        minimize.setOnAction(e->{stage.setIconified(true);});
        buttons.setSpacing(2);
        buttons.getChildren().addAll(minimize, size, close);
        return buttons;
    }
    private void initWindowDrag(){
        setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        // Handle mouse drag event to move the stage
        setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
    private HBox initDropDowns(ArrayList<Link> links) {

        ArrayList<DropDown> dropdownArr = new ArrayList<>();
        for(int i = 0; i < links.size(); i++){
            if(links.get(i).getParent() == null) dropdownArr.add(new DropDown(links.get(i))); //Adds the top dropdowns
        }
        HBox dropdowns = new HBox();
        dropdowns.setSpacing(2);
        dropdowns.getChildren().addAll(dropdownArr);
        return dropdowns;
    }



}

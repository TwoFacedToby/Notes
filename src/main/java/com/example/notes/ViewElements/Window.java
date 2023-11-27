package com.example.notes.ViewElements;

import com.example.notes.FileHandling.Directory;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Window extends Stage {

    private final double screenWidth = 1600, screenHeight = 900;
    private final String title = "Notes";
    private Scene scene;
    private View view;

    public Window(Directory collection){
        view = new View(this, collection);
        StageResizeHandle resizeHandle = new StageResizeHandle(view, this);
        scene = new Scene(resizeHandle, screenWidth, screenHeight);
        scene.setFill(Color.TRANSPARENT);
        setCSS();
        setScene(scene);
        setTitle(title);
        setOnCloseRequest(this::onClose);
        initStyle(StageStyle.UNDECORATED);
        initStyle(StageStyle.TRANSPARENT);
        show();
    }
    private void onClose(WindowEvent event){

        //event.consume();
    }

    private void setCSS(){
        scene.getStylesheets().add(getClass().getResource("/css/PageElements.css").toExternalForm());
    }
    public View getView(){
        return view;
    }

}

package com.example.notes.ViewElements;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class Popup extends Stage {

    private Answer[] answer = new Answer[999];
    private int arraySize = 0;
    VBox content;
    Scene scene;

    public Popup(double width, double height){
        content = new VBox();
        scene = new Scene(content, width, height);
        setScene(scene);
        initStyle(StageStyle.UTILITY);
    }

    public void setClosable(boolean bool){
        if(!bool){
            setOnCloseRequest(Event::consume);
        }
        else{
            setOnCloseRequest(e->{});
        }
    }
    public void setCanClickOutside(boolean bool){
        if(bool){
            initModality(Modality.APPLICATION_MODAL);
        }
    }
    public Answer[] showAndWaitForInput(){
        if(arraySize > 1){
            answer = new Answer[arraySize];
        }
        showAndWait();
        return answer;
    }
    public void addCloseButton(String text){
        Button button = new Button(text);
        button.setId("folding-view-button");
        button.setOnAction(e->close());
        content.getChildren().add(button);
    }

    public void addTextInput(int expectedIndex, String prompt){
        arraySize++;
        TextField input = new TextField();
        input.setId("input-field");
        input.setPromptText(prompt);
        content.getChildren().add(input);
        input.setOnKeyTyped(e->{
            answer[expectedIndex] = new StringAnswer(input.getText());
        });



    }
    public void addFileTypeInput(int expectedIndex){
        arraySize++;
        ChoiceBox<String> answerBox = new ChoiceBox<>();
        answerBox.setId("drop-down");
        answerBox.getItems().addAll(FileType.FILE.name(), FileType.FOLDER.name(), FileType.COLLECTION.name());
        answerBox.setOnAction(e->{
            if(answerBox.getValue().equals(FileType.FILE.name())){
                answer[expectedIndex] = new FileTypeAnswer(FileType.FILE);
            }
            else if(answerBox.getValue().equals(FileType.FOLDER.name())){
                answer[expectedIndex] = new FileTypeAnswer(FileType.FOLDER);
            }
            else if(answerBox.getValue().equals(FileType.COLLECTION.name())){
                answer[expectedIndex] = new FileTypeAnswer(FileType.COLLECTION);
            }
        });
        answerBox.setValue(FileType.FILE.name());
        content.getChildren().add(answerBox);
    }



    public interface Answer {

    }
    public class StringAnswer implements Answer {
        private String string;
        public StringAnswer(String string){
            this.string = string;
        }
        public String getAnswer(){
            return string;
        }
    }
    public class IntAnswer implements Answer {
        private int intAnswer;

        public IntAnswer(int intAnswer){
            this.intAnswer = intAnswer;
        }
        public int getAnswer(){
            return intAnswer;
        }
    }
    public class FileTypeAnswer implements Answer {
        private FileType typeAnswer;

        public FileTypeAnswer(FileType typeAnswer){
            this.typeAnswer = typeAnswer;
        }

        public FileType getAnswer(){
            return typeAnswer;
        }

    }
    public enum FileType {
        FOLDER("Folder"),
        COLLECTION("Collection"),
        FILE("File"),
        ;

        FileType(String toDisplay) {

        }
    }
}

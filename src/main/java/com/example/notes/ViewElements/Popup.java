package com.example.notes.ViewElements;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup extends Stage {

    private Answer[] answers;
    private int arraySize = 0;
    VBox content;
    Scene scene;
    HBox choiceButtons;

    public Popup(double width, double height){
        content = new VBox();
        scene = new Scene(content, width, height);
        setScene(scene);
        initStyle(StageStyle.UTILITY);
        scene.getStylesheets().add(getClass().getResource("/css/PageElements.css").toExternalForm());
        content.setId("popup-view");

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
        showAndWait();
        return answers;
    }
    private void setDefaultAnswer(Answer answer, int index){
        if(answers == null) {
            answers = new Answer[1];
            answers[0] = answer;
            return;
        }
        if(answers.length <= index){
            Answer[] replace = new Answer[index + 1];
            for(int i = 0; i < answers.length; i++){
                replace[i] = answers[i];
            }
            answers = replace;
        }
        answers[index] = answer;
    }
    public void addCloseButton(String text){
        Button button = new Button(text);
        button.setId("popup-button");
        button.setOnAction(e->close());
        content.getChildren().add(button);
    }
    public void addMessage(String message){
        Label label = new Label(message);
        content.getChildren().add(label);
    }
    public void addChoiceButton(String name, String toReturn, int expectedIndex){
        if(choiceButtons == null) {
            setDefaultAnswer(new StringAnswer(""), arraySize++);
            choiceButtons = new HBox();
            content.getChildren().add(choiceButtons);
        }
        Button button = new Button(name);
        choiceButtons.getChildren().add(button);
        button.setOnAction(e -> {
            answers[expectedIndex] = new StringAnswer(toReturn);
            close();
        });
    }

    public void addTextInput(int expectedIndex, String prompt){
        setDefaultAnswer(new StringAnswer(""), arraySize++);
        TextField input = new TextField();
        input.setId("popup-input-field");
        input.setPromptText(prompt);
        content.getChildren().add(input);
        input.setOnKeyTyped(e->{
            answers[expectedIndex] = new StringAnswer(input.getText());
        });



    }
    public void addFileTypeInput(int expectedIndex){
        setDefaultAnswer(new FileTypeAnswer(FileType.FILE), arraySize++);
        ChoiceBox<String> answerBox = new ChoiceBox<>();
        answerBox.setId("drop-down");
        answerBox.getItems().addAll(FileType.FILE.name(), FileType.FOLDER.name(), FileType.COLLECTION.name());
        answerBox.setOnAction(e->{
            if(answerBox.getValue().equals(FileType.FILE.name())){
                answers[expectedIndex] = new FileTypeAnswer(FileType.FILE);
            }
            else if(answerBox.getValue().equals(FileType.FOLDER.name())){
                answers[expectedIndex] = new FileTypeAnswer(FileType.FOLDER);
            }
            else if(answerBox.getValue().equals(FileType.COLLECTION.name())){
                answers[expectedIndex] = new FileTypeAnswer(FileType.COLLECTION);
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

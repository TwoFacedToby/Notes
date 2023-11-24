package com.example.notes.ViewElements;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup extends Stage {

    private Answer[] answers;
    private int arraySize = 0;
    private boolean shouldReturnInfo = false;
    BorderPane topBar;
    VBox content;
    Scene scene;
    private HBox choiceButtons;
    private String title;
    private ChoiceBox<String> answerBox;

    public Popup(double width, double height, String title){
        this.title = title;
        content = new VBox();
        initTopBar();
        BorderPane window = new BorderPane();
        window.setTop(topBar);
        window.setCenter(content);
        scene = new Scene(window, width, height);
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        initStyle(StageStyle.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("/css/PopupElements.css").toExternalForm());
        content.setId("popup-view");

    }

    private void initTopBar(){
        topBar = new BorderPane();
        topBar.setId("top-bar");
        VBox centerer = new VBox();
        Label titleLabel = new Label(title); //Label
        titleLabel.setId("top-bar-label");
        centerer.getChildren().add(titleLabel);
        centerer.setAlignment(Pos.CENTER);
        //centerer.setPadding(new Insets(0, 0, 0, 20));
        topBar.setLeft(centerer);
        Button close = new Button("X"); //Close Button
        close.setId("top-bar-button");
        close.setOnAction(e->{close();});
        String closeDefaultStyle = close.getStyle();
        close.setOnMouseEntered(e->{close.setStyle(closeDefaultStyle + "; -fx-background-color: #be1717");});
        close.setOnMouseExited(e->{close.setStyle(closeDefaultStyle);});
        topBar.setRight(close);
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
        if(shouldReturnInfo) return answers;
        return null;
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
        button.setOnAction(e->{
            shouldReturnInfo = true;
            close();
        });
        content.getChildren().add(button);
    }
    public void addMessage(String message){
        Label label = new Label(message);
        label.setId("popup-label");
        content.getChildren().add(label);
    }
    public void addSpacer(double spacing){
        VBox spacer = new VBox();
        spacer.setMinHeight(spacing);
        spacer.setMaxHeight(spacing);
        content.getChildren().add(spacer);
    }
    public void addChoiceButton(String name, int expectedIndex){
        if(choiceButtons == null) {
            setDefaultAnswer(new StringAnswer(""), arraySize++);
            choiceButtons = new HBox();
            content.getChildren().add(choiceButtons);
        }
        Button button = new Button(name);
        choiceButtons.getChildren().add(button);
        button.setOnAction(e -> {
            answers[expectedIndex] = new StringAnswer(name);
            shouldReturnInfo = true;
            close();
        });
    }
    public void addDropDownChoice(String name, int expectedIndex){
        if(answerBox == null){
            setDefaultAnswer(new StringAnswer(name), arraySize++);
            answerBox = new ChoiceBox<>();
            answerBox.setValue(name);
            answerBox.setId("popup-drop-down");
            content.getChildren().add(answerBox);
            answerBox.setOnAction(e->{
                answers[expectedIndex] = new StringAnswer(name);
            });
        }
        answerBox.getItems().add(name);
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
}

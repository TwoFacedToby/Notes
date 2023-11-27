package com.example.notes.ViewElements;

import com.example.notes.FileHandling.Directory;
import com.example.notes.FileHandling.FileLoader;
import com.example.notes.Navigation.NavigationEvents;

import java.util.ArrayList;

public class StartWindow {

    Popup startPopup;
    Popup openPopup;
    Popup newPopup;
    public StartWindow(){
        startPopup = new Popup(300, 200, "Notes");
        startPopup.addMessage("No Collection Open\nOpen or Create a new One");
        startPopup.addChoiceButton("Open", 0);
        startPopup.addChoiceButton("Create New", 0);
        createOpen();
        createNew();

    }


    public void show(){
        Popup.Answer[] answers = startPopup.showAndWaitForInput();
        if(answers == null) return;
        String answer = "";
        try{
            answer = ((Popup.StringAnswer)answers[0]).getAnswer();
        }catch (Exception e){
            System.out.println("Parse Error: " + e.getMessage());
            return;
        }
        if(answer.equals("")) return;
        if(answer.equals("Open")) showOpen();
        else if(answer.equals("Create New")) showNew();
    }
    private void createNoCollectionError(){
        Popup popup = new Popup(300, 200, "No Saved Collections");
        popup.addMessage("No collection can be found on your device");
        popup.addChoiceButton("Create New Instead", 0);
        popup.addChoiceButton("Cancel", 0);

        Popup.Answer[] answers = popup.showAndWaitForInput();
        if(answers == null) return;
        String answer = "";
        try{
            answer = ((Popup.StringAnswer)answers[0]).getAnswer();
        }catch (Exception e){
            System.out.println("Parse Error: " + e.getMessage());
            return;
        }
        if(answer.equals("")) return;
        if(answer.equals("Create New Instead")) showNew();
    }
    private void createOpen(){
        ArrayList<Directory> collections = FileLoader.get().getCollections();
        if(collections.size() == 0) createNoCollectionError();
        openPopup = new Popup(300, 200, "Open");
        openPopup.addMessage("Choose Collection to Open");
        for(int i = 0; i < collections.size(); i++){
            openPopup.addDropDownChoice(collections.get(i).getName(),0);
        }
        openPopup.addCloseButton("Open");
    }
    private void showOpen(){
        ArrayList<Directory> collections = FileLoader.get().getCollections();
        Popup.Answer[] answers = openPopup.showAndWaitForInput();
        if(answers == null) return;
        Directory chosen = null;
        try{
            for(int i = 0; i < collections.size(); i++){
                if(((Popup.StringAnswer)answers[0]).getAnswer().equals(collections.get(i).getName())) chosen = collections.get(i);
            }
        }catch (Exception e){
            System.out.println("\nAnswer String Parsing Error: \n\n" + e.getMessage());
        }
        if(chosen == null) return;
        Window window = new Window(chosen);
    }
    private void createNew(){
        newPopup = new Popup(300, 200, "Create New Collection");
        newPopup.addMessage("Choose Name of New Collection");
        newPopup.addTextInput(0, "Name");
        newPopup.addCloseButton("Create");
    }
    private void showNew(){
        String answer = "";
        do{
            Popup.Answer[] answers = newPopup.showAndWaitForInput();
            if(answers == null) return;
            try{
                answer = ((Popup.StringAnswer)answers[0]).getAnswer();
            }catch (Exception e){
                System.out.println("Parse Error: " + e.getMessage());
            }
        }while(answer.equals(""));
        Directory directory = FileLoader.createCollection(answer);
        Window window = new Window(directory);

    }

}

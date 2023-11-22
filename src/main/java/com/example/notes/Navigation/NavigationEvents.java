package com.example.notes.Navigation;

import com.example.notes.FileHandling.Directory;
import com.example.notes.ViewElements.Popup;
import com.example.notes.ViewElements.View;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class NavigationEvents {
    private ArrayList<View> views = new ArrayList<>();
    private static NavigationEvents navEvents;
    private ArrayList<Directory> collections = new ArrayList<>();
    private ArrayList<Link> nav = new ArrayList<>();

    public static NavigationEvents get(){
        if(navEvents == null) {
            navEvents = new NavigationEvents();
        }
        return navEvents;
    }

    public ArrayList<Link> getNav(){
        if(nav.size() == 0) {
            initLinks();
            printNavToConsole();
        }
        return nav;

    }

    private void initLinks(){

        Link file = new Link("File \t \uD83D\uDD3D ", null);
            nav.add(file);
            new Link("New", file);
            new Link("Open", file);
            new Link("Save", file);
            new Link("Save As", file);
            new Link("Explorer View", file);
            new Link("Export", file);
            new Link("Settings", file);
            new Link("Close Collection", file);
        Link account = new Link("Account \t \uD83D\uDD3D", null);
            nav.add(account);
            new Link("Login", account);
            new Link("Change Account", account);
            new Link("Account Settings", account);
        Link help = new Link("Help \t \uD83D\uDD3D", null);
            nav.add(help);
            new Link("General Help Page", help);
            new Link("User Manual", help);
            new Link("Contact", help);
    }
    private void printNavToConsole(){
        for(int i = 0; i < nav.size(); i++){
            System.out.println(nav.get(i).getName() + ": " + nav.get(i).getLink());
        }
    }
    public void onNavClicked(Link link, View view){
        System.out.println(link.getName() + " Clicked");
    }
    public ArrayList<View> getViews(){
        return views;
    }

    public void openFile(File file){

    }
    public void createNew(Directory location){
        Popup popup = new Popup(200, 100);
        popup.setCanClickOutside(false);
        popup.addTextInput(0, "Name");
        popup.addFileTypeInput(1);
        popup.addCloseButton("Create");
        boolean gotValidAnswer = true;
        Popup.Answer[] answers;
        do{
            gotValidAnswer = true;
            answers = popup.showAndWaitForInput();
            for(int i = 0; i < answers.length; i++){
                if(answers[i] == null){
                    gotValidAnswer = false;
                }
            }
        }while(!gotValidAnswer);

        String name = ((Popup.StringAnswer)answers[0]).getAnswer();
        Popup.FileType fileType = ((Popup.FileTypeAnswer)answers[1]).getAnswer();

        switch (fileType){
            case FILE -> createFile(location, name);
            case FOLDER -> createDirectory(location, name);
            case COLLECTION -> createCollection(name);
        }
    }
    public void createFile(Directory location, String name){
        System.out.println(location.getName() + " / " + name);
    }
    public void createDirectory(Directory location, String name){
        System.out.println(location.getName() + " / " + name);

    }
    public void createCollection(String name){
        System.out.println("collections / " + name);

    }
    public ArrayList<Directory> getCollections(){
        return collections;
    }
    public void setCollections(ArrayList<Directory> collections){
        this.collections = collections;
    }

}

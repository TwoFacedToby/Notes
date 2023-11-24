package com.example.notes.Navigation;

import com.example.notes.FileHandling.Directory;
import com.example.notes.FileHandling.FileLoader;
import com.example.notes.ViewElements.*;

import java.io.File;
import java.util.ArrayList;

public class NavigationEvents {
    private View view;
    private static NavigationEvents navEvents;
    private Directory collection;
    private ArrayList<Link> nav = new ArrayList<>();

    public NavigationEvents(View view){
        this.view = view;
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
        switch (link.getName()){
            case "New" -> {
                createNew(null);
            }
        }
    }
    public View getView(){
        return view;
    }

    public void openFile(File file){

    }
    public void createNew(FoldingView foldView){
        Directory location;

        Popup popup = new Popup(200, 200, "Create New");
        popup.setCanClickOutside(false);
        popup.addMessage("Choose Name Of File:");
        popup.addTextInput(0, "Name");
        popup.addSpacer(10);
        if(foldView == null){
            location = view.getCurrentCollection();
            popup.addMessage("Choose Filetype:");
            if(location == null){
                popup.addDropDownChoice("Collection",1);
            }
            else{
                popup.addDropDownChoice("File",1);
                popup.addDropDownChoice("Folder",1);
                popup.addDropDownChoice("Collection",1);
            }
        }
        else{
            location = foldView.getDirectory();
            popup.addDropDownChoice("File",1);
            popup.addDropDownChoice("Folder",1);
        }
        popup.addSpacer(10);
        popup.addCloseButton("Create");


        boolean gotValidAnswer;
        Popup.Answer[] answers;
        do{
            gotValidAnswer = true;
            answers = popup.showAndWaitForInput();
            if(answers == null) return;
            try{
                if(((Popup.StringAnswer)answers[0]).getAnswer().equals("")) gotValidAnswer = false;
            }catch(Exception e){
                gotValidAnswer = false;
            }
            if(!gotValidAnswer){
                Popup retry = new Popup(200, 100, "Invalid Input");
                retry.addMessage("There was a problem with your input.");
                retry.addChoiceButton("Try Again",  0);
                retry.addChoiceButton("Cancel",  0);
                Popup.Answer[] retryAnswers = retry.showAndWaitForInput();
                if(retryAnswers == null) return;
                if(retryAnswers[0] == null) return;
                String strAnswer = ((Popup.StringAnswer)retryAnswers[0]).getAnswer();
                if(!strAnswer.equals("Retry")) return;
            }

        }while(!gotValidAnswer);

        String name = ((Popup.StringAnswer)answers[0]).getAnswer();
        String fileType = ((Popup.StringAnswer)answers[1]).getAnswer();

        switch (fileType){
            case "File" -> createFile(location, name, foldView, view);
            case "Folder" -> createDirectory(location, name, foldView, view);
            case "Collection" -> createCollection(name);
        }


    }
    public void createFile(Directory location, String name, FoldingView foldView, View view){
        FileLoader.createFile(location, name);
        if((foldView == null)) System.out.println("Foldview is null") ;
        else System.out.println("Foldview is not null") ;
        if(foldView != null) foldView.updateChildrenViews();
        else view.resetCollections();

    }
    public void createDirectory(Directory location, String name, FoldingView foldView, View view){
        FileLoader.createDirectory(location, name);
        System.out.println(location.getName());
        if((foldView == null)) System.out.println("Foldview is null") ;
        else System.out.println("Foldview is not null") ;
        if(foldView != null) foldView.updateChildrenViews();
        else view.resetCollections();

    }
    public void createCollection(String name){
        Directory collection = FileLoader.createCollection(name);
        Window window = new Window(collection);
        window.getView().setCurrentCollection(collection);
        window.getView().resetCollections();
    }
    public Directory getCollection(){
        return collection;
    }
    public void setCollection(Directory collection){
        this.collection = collection;
    }

}

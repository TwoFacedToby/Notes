package com.example.notes.Navigation;

import javafx.stage.Stage;

import java.util.ArrayList;

public class NavigationEvents {
    private static NavigationEvents navEvents;
    private static ArrayList<Link> topBar = new ArrayList<>();
    private static ArrayList<Link> nav = new ArrayList<>();

    public static NavigationEvents get(){
        if(navEvents == null) {
            navEvents = new NavigationEvents();
            init();
        }
        return navEvents;
    }

    private static void init(){
        initLinks();
        printNavToConsole();
    }
    public static ArrayList<Link> getNav(){
        if(nav.size() == 0) initLinks();
        return nav;
    }

    private static void initLinks(){
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
    private static void printNavToConsole(){
        for(int i = 0; i < nav.size(); i++){
            System.out.println(nav.get(i).getName() + ": " + nav.get(i).getLink());
        }
    }
    public static void onNavClicked(Link link){
        System.out.println(link.getName() + " Clicked");
    }



}

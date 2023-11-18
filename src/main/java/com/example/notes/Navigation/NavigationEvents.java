package com.example.notes.Navigation;

import java.util.ArrayList;

public class NavigationEvents {
    private static NavigationEvents navEvents;
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
        return nav;
    }

    private static void initLinks(){
        Link p; //Last Parent
        p = addToList(new Link("File", null));
            p = addToList(new Link("New", p));
                p = addToList(new Link("New Collection", p));
                p = addToList(new Link("New Notebook", p.getParent()));
                p = addToList(new Link("New Note", p.getParent()));
                p = addToList(new Link("New File", p.getParent()));
            p = addToList(new Link("Open", p.getParent().getParent()));
                p = addToList(new Link("Open Collection", p));
                p = addToList(new Link("Open Notebook", p.getParent()));
                p = addToList(new Link("Open Recent", p.getParent()));
            p = addToList(new Link("Export", p.getParent().getParent()));
                p = addToList(new Link("Export Note As PDF", p));
                p = addToList(new Link("Export Note As HTML", p.getParent()));
            p = addToList(new Link("Explorer View", p.getParent().getParent()));
            p = addToList(new Link("Settings", p.getParent()));
            p = addToList(new Link("Save", p.getParent()));
            p = addToList(new Link("Close Collection", p.getParent()));


    }
    private static Link addToList(Link link){
        nav.add(link);
        return link;
    }
    private static void printNavToConsole(){
        for(int i = 0; i < nav.size(); i++){
            System.out.println(nav.get(i).getName() + ": " + nav.get(i).getLink());
        }
    }



}

package com.example.notes.Navigation;

import java.util.ArrayList;

public class Link {
    private String link;
    private String name;
    private Link parent;
    private ArrayList<Link> children = new ArrayList<>();
    public Link(String name, Link parent){
        this.name = name;
        this.parent = parent;
        generateLink();
    }
    private void generateLink(){
        if(parent == null) link = "/" + name;
        else {
            link = parent.link + "/" + name;
            parent.getChildren().add(this);
        }

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getParent() {
        return parent;
    }

    public void setParent(Link parent) {
        this.parent = parent;
    }

    public ArrayList<Link> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Link> children) {
        this.children = children;
    }
}

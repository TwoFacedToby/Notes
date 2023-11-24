package com.example.notes.FileHandling;

import com.example.notes.Navigation.Link;

import java.io.File;
import java.util.ArrayList;

public class FileLoader {

    private static FileLoader loader;
    private static ArrayList<Directory> collections = new ArrayList<>();

    public static FileLoader get(){
        if(loader == null) {
            loader = new FileLoader();
            init();
        }
        return loader;
    }
    public ArrayList<Directory> getCollections(){
        return collections;
    }
    private static void init(){
        updateCollections();
        printCollections();
    }
    public static void updateCollections(){
        File mainDirectory = FileHandler.getTopFile();
        File[] collectionArr = mainDirectory.listFiles();
        for(int i = 0; i < collectionArr.length; i++){
            Directory dir = new Directory(collectionArr[i], null);
            collections.add(dir);
        }
    }
    private static void printCollections(){
        for(int i = 0; i < collections.size(); i++){
            printDirectory(collections.get(i), 0);
        }
    }
    private static void printDirectory(Directory dir, int indents){
        String indentString = "";
        for(int i = 0; i < indents; i++){
            indentString += "\t";
        }
        System.out.println(indentString + dir.getName());
        for(int i = 0; i < dir.getChildren().size(); i ++){
            printDirectory(dir.getChildren().get(i), indents+1);
        }
        for(int i = 0; i < dir.getFiles().size(); i++){
            System.out.println(indentString + "\t" + dir.getFiles().get(i).getName());
        }
    }
    public static Directory createCollection(String name){
        File file = FileHandler.createCollection(name);
        Directory dir;
        if(file != null) {
            dir = new Directory(file, null);
            collections.add(dir);
            return dir;
        }
        return null;
    }
    public static Directory createDirectory(Directory parent, String name){
        File file = FileHandler.createDirectory(parent.getPath(), name);

        if(file != null){
            System.out.println("Directory Created");
            Directory dir = new Directory(file, parent);
            parent.getChildren().add(dir);
            return dir;
        }
        System.out.println("Directory Not Created");

        return null;
    }
    public static File createFile(Directory parent, String name){


        return null;
    }


}

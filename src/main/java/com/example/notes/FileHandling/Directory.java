package com.example.notes.FileHandling;

import com.example.notes.Enums.Direction;

import java.io.File;
import java.util.ArrayList;

public class Directory {
    private String name;
    private String path;
    private File file;
    private Directory parent;
    private ArrayList<Directory> children = new ArrayList<>();
    private ArrayList<File> files = new ArrayList<>();

    public Directory(File file, Directory parent){
        this.file = file;
        this.parent = parent;
        this.name = file.getName();
        init();
    }
    private void init(){
        File[] fileArr = file.listFiles();
        for(int i = 0; i < fileArr.length; i++){
            if(fileArr[i].isDirectory()) children.add(new Directory(fileArr[i], this));
            else files.add(fileArr[i]);
        }
    }

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }
    public File getFile() {
        return file;
    }
    public Directory getParent() {
        return parent;
    }
    public ArrayList<Directory> getChildren() {
        return children;
    }
    public ArrayList<File> getFiles() {
        return files;
    }
    public void addFile(){

    }

}

package com.example.notes.FileHandling;

import java.io.File;
import java.util.ArrayList;

public class FileHandler {
    static final String globalPath = "src/main/Files";
    public static File getTopFile(){
        return getFileOrDirectory(globalPath);
    }
    public static File getFileOrDirectory(String path) {
        return new File(path);
    }
    public static File createCollection(String name) {
        File parentDirectory = getFileOrDirectory(globalPath + "/" + name);
        File newDirectory = new File(parentDirectory, name);

        if (newDirectory.mkdirs()) {
            System.out.println("Directory created successfully: " + newDirectory.getAbsolutePath());
            return newDirectory;
        } else {
            System.err.println("Failed to create directory: " + newDirectory.getAbsolutePath());
            return null;
        }
    }
    public static File createDirectory(String parentPath, String directoryName) {
        File parentDirectory = getFileOrDirectory(parentPath);
        File newDirectory = new File(parentDirectory, directoryName);

        if (newDirectory.mkdirs()) {
            System.out.println("Directory created successfully: " + newDirectory.getAbsolutePath());
            return newDirectory;
        } else {
            System.err.println("Failed to create directory: " + newDirectory.getAbsolutePath());
            return null;
        }
    }
}

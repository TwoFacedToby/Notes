package com.example.notes.FileHandling;

import java.io.File;

public class FileHandler {
    static final String globalPath = "src/main/Files";
    public static File getTopFile(){
        return getFileOrDirectory(globalPath);
    }
    public static File getFileOrDirectory(String path) {
        return new File(path);
    }
    public static File createCollection(String name) {
        File newCollection = getFileOrDirectory(globalPath + "/" + name);

        if (newCollection.mkdirs()) {
            System.out.println("Directory created successfully: " + newCollection.getAbsolutePath());
            return newCollection;
        } else {
            System.err.println("Failed to create directory: " + newCollection.getAbsolutePath());
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

package com.example.notes.Navigation;

public class Settings {

    private static Settings settings;

    public static Settings get(){
        if(settings == null) settings = new Settings();
        return settings;
    }

}

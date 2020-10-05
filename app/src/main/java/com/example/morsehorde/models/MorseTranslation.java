package com.example.morsehorde.models;

public class MorseTranslation {

    private String key;
    private String translation;


    public MorseTranslation(String key, String translation) {
        this.key = key;
        this.translation = translation;
    }


    public String getKey() {
        return key;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return key + ": " + translation;
    }


}

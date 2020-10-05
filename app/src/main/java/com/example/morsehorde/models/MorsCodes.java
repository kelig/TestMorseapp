package com.example.morsehorde.models;

public class MorsCodes {
    private int id;
    private String Letter;
    private String Morsecode;


    public MorsCodes(int id, String letter, String morsecode) {
        this.id = id;
        Letter = letter;
        Morsecode = morsecode;
    }

    public int getId() {
        return id;
    }

    public String getLetter() {
        return Letter;
    }

    public void setLetter(String letter) {
        Letter = letter;
    }

    public String getMorsecode() {
        return Morsecode;
    }

    public void setMorsecode(String morsecode) {
        Morsecode = morsecode;
    }

    @Override
    public String toString() {
        return Letter + '/'  + Morsecode ;
    }
}

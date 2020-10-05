package com.example.morsehorde.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "morsenotes")
public class morseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;

    @Ignore
    public morseEntity(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public morseEntity(String text) {
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}

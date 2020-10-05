package com.example.morsehorde.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {morseEntity.class}, version = 1,exportSchema = false)

public abstract class morseNotesDatabase extends RoomDatabase {


    public static final String DATABASE_NAME = "morse_database";
    private static morseNotesDatabase INSTANCE;

    public abstract MorseDao morseDao();

    public static morseNotesDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (morseNotesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            morseNotesDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;


    }
}

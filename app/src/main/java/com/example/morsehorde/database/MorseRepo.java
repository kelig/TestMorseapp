package com.example.morsehorde.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MorseRepo {
    private static MorseRepo ourInstance;
    public LiveData<List<morseEntity>> Morse;
    private morseNotesDatabase DB;
    private Executor executor = Executors.newSingleThreadExecutor();



    private MorseRepo(Context context){
        DB = morseNotesDatabase.getInstance(context);
        Morse = getAllMorse();
    }

    public static MorseRepo getInstance(Context context){
        if(ourInstance == null){
            ourInstance = new MorseRepo(context);
        };
        return ourInstance;
    }



    public void addData(final morseEntity word){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                DB.morseDao().insertMorse(word);
            }
        });
    }


    public LiveData<List<morseEntity>> getAllMorse() {
        return DB.morseDao().getAll();
    }




}

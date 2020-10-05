package com.example.morsehorde.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MorseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMorse(morseEntity morse);


    @Query("SELECT * FROM morsenotes")
    LiveData<List<morseEntity>> getAll();


}

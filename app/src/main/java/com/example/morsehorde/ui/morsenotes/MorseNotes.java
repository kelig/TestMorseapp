package com.example.morsehorde.ui.morsenotes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.morsehorde.R;
import com.example.morsehorde.database.MorseDao;
import com.example.morsehorde.database.MorseRepo;
import com.example.morsehorde.database.morseEntity;
import com.example.morsehorde.database.morseNotesDatabase;
import com.example.morsehorde.models.MorseTranslation;
import com.example.morsehorde.ui.AdapterRememberMorse;
import com.example.morsehorde.ui.AdapterWordList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MorseNotes extends Fragment {

    private Button Add;
    private EditText AddMorsenote;

    private RecyclerView recyclerView;
    private ArrayList<morseEntity> morseEntities;
    private Executor executor = Executors.newSingleThreadExecutor();
    private MorseRepo MorseRepo;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_morse_notes, container, false);


        MorseRepo = MorseRepo.getInstance(getActivity());

        recyclerView = (RecyclerView) root.findViewById(R.id.morsenotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        GetMorselist(recyclerView);



        Add = root.findViewById(R.id.AddbuttonLocal);
        AddMorsenote = (EditText) root.findViewById(R.id.editMorseList);

        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                String value = AddMorsenote.getText().toString();

                SaveMorseRemember(value);


            }
        });



        return root;
    }


    private void GetMorselist(final RecyclerView view) {

        Observer<List<morseEntity>> morseObserver = new Observer<List<morseEntity>>() {
            @Override
            public void onChanged(List<morseEntity> morseEntitieschange) {
                morseEntities.clear();
                morseEntities.addAll(morseEntitieschange);
                if(morseEntities != null){
                    ArrayList e = (ArrayList) morseEntitieschange;
                    AdapterRememberMorse adap = new AdapterRememberMorse(getActivity(), e);
                    view.setAdapter(adap);
                }
                else{
                    recyclerView.notify();
                }
            }
        };
        };


    private void SaveMorseRemember(String word){

        morseEntity ms = new morseEntity(word);
        MorseRepo.addData(ms);

        GetMorselist(this.recyclerView);
    }


};









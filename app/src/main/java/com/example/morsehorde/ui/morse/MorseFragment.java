package com.example.morsehorde.ui.morse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.morsehorde.ui.AdapterLetters;
import com.example.morsehorde.ui.QueueSingleton;
import com.example.morsehorde.R;
import com.example.morsehorde.models.MorsCodes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MorseFragment extends Fragment {


    Button beep;
    Button beep2;
    Button notify;
    Button camera;

    String URL = "http://192.168.0.219:3000/morsecodes";
    RecyclerView recyclerView;
    ArrayList<MorsCodes> morsCodes = new ArrayList<MorsCodes>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_morse, container, false);

        final MediaPlayer mpLong = MediaPlayer.create(getActivity(), R.raw.morselong);
        final MediaPlayer mpSos = MediaPlayer.create(getActivity(), R.raw.sosmorsecode);

        beep2 = root.findViewById(R.id.Beep2);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.morsehorde.sharedpref", getActivity().MODE_PRIVATE);
        Boolean isVisible = sharedPreferences.getBoolean("buttonShow",false);

        if(!isVisible){
            beep2.setVisibility(View.GONE);
        }





        beep2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                        mpSos.start();
                    }

                    break;
                    case MotionEvent.ACTION_UP: {
                        mpSos.pause();
                    }
                    break;
                }
                return true;
            }
        });



        //create beep when button is pressed
        beep = root.findViewById(R.id.Beep);

        beep.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                        mpLong.start();
                    }

                    break;
                    case MotionEvent.ACTION_UP: {
                        mpLong.pause();
                    }
                    break;
                }
                return true;
            }
        });


        //camera button to call camera
        camera = root.findViewById(R.id.Camera);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        // this is the snackbar button for info
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "In this app you will learn a little morsecode", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recyclerView = (RecyclerView) root.findViewById(R.id.letterlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GetMorsCodes(recyclerView);


        return root;
    }//end


    public void GetMorsCodes(final RecyclerView view) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                //Log.i("ddddd", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //get values from json
                        int idValueInt = Integer.parseInt(response.getJSONObject(i).getString("Id") );
                        String MorseLetter = response.getJSONObject(i).getString("MorseLetter");
                        String Morsecode = response.getJSONObject(i).getString("Morsecode");
                        //create object and put in arraylist
                        MorsCodes mc = new MorsCodes(idValueInt, MorseLetter, Morsecode);
                        morsCodes.add(mc);
                    } catch (JSONException e) {
                        Log.e("JsonarrayError", e.getMessage());
                    }
                }
                //add to recyclerview on page
                view.setAdapter(new AdapterLetters(getActivity(), morsCodes) {
                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    }
                });
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", error.getMessage());
                        MorsCodes mc = new MorsCodes(99999, "No", "•⁃⁃⁃");
                        morsCodes.add(mc);
                        //add to recyclerview on page
                        view.setAdapter(new AdapterLetters(getActivity(), morsCodes) {
                            @Override
                            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            }
                        });
                    }

                });
        QueueSingleton.getInstance(getActivity()).addToRequestQueue(jsonArrayRequest);

    }


}
package com.example.morsehorde.ui.words;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.morsehorde.ui.AdapterWordList;
import com.example.morsehorde.ui.QueueSingleton;
import com.example.morsehorde.models.MorseTranslation;
import com.example.morsehorde.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordsFragment extends Fragment {

    Button Add;
    EditText Addtext;

    String URL = "http://192.168.0.219:3000/words";
    RecyclerView recyclerView;
    ArrayList<MorseTranslation> morseTranslation = new ArrayList<MorseTranslation>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_words, container, false);


        recyclerView = (RecyclerView) root.findViewById(R.id.wordlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GetMorselist(recyclerView);


        Add = root.findViewById(R.id.Addbutton);
        Addtext = (EditText) root.findViewById(R.id.editList);

        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                String value = Addtext.getText().toString();
                if(value!=null){
                    Postmorseword(value,recyclerView);
                }

            }
        });


        return root;
    }


    public void GetMorselist(final RecyclerView view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray keys = object.names();
                    //get items from jsonobject and put in arraylist
                    for (int i = 0; i < keys.length(); ++i) {
                        //key value pairs
                        String key = keys.getString(i);
                        String value = object.getString(key);
                        //create morsetranslation object and add to array
                        MorseTranslation mt = new MorseTranslation(key, value);
                        morseTranslation.add(mt);
                    }
                } catch (JSONException err) {
                    Log.e("Error", err.toString());
                }
                //add to recyclerview on page
                view.setAdapter(new AdapterWordList(getActivity(), morseTranslation) {
                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    }
                });
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MORSE_ERROR", error.getMessage());
                        MorseTranslation mt = new MorseTranslation("A", "Something went wrong");
                        morseTranslation.add(mt);
                        //add to recyclerview on page on error
                        view.setAdapter(new AdapterWordList(getActivity(), morseTranslation) {
                            @Override
                            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            }
                        });

                    }
                });

        QueueSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }


    public void Postmorseword(final String word,final RecyclerView view){

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        //Log.d("Response", response);
                        morseTranslation.removeAll(morseTranslation);
                        GetMorselist(view);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        // error
                        Log.d("Error.Response", e.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("word", word);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        QueueSingleton.getInstance(getActivity()).addToRequestQueue(postRequest);




    }



    public void PostMorselist(){

    }


}
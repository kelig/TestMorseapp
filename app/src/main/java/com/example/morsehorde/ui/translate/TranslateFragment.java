package com.example.morsehorde.ui.translate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.morsehorde.R;
import com.example.morsehorde.models.Translator;

public class TranslateFragment extends Fragment {

    private TextView txtinput;
    private TextView result;
    private Button toMorseBtn;
    private Button toAlphaBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_translate, container, false);


        txtinput =  root.findViewById(R.id.txt);
        result = root.findViewById(R.id.result);
        toMorseBtn = root.findViewById(R.id.toMorseBtn);
        toAlphaBtn = root.findViewById(R.id.toAlphaBtn);


        toMorseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String convert = txtinput.getText().toString();
                String converted = Translator.alphaToMorse(convert);
                result.setText(converted);
            }
        });

        toAlphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String convert = txtinput.getText().toString();
                String converted = Translator.morseToAlpha(convert);
                result.setText(converted);
            }
        });


       
        return root;
    }
}
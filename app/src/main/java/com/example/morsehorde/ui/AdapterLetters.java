package com.example.morsehorde.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morsehorde.R;
import com.example.morsehorde.models.MorsCodes;
import com.example.morsehorde.models.MorseTranslation;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterLetters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<MorsCodes> morsCodes = new ArrayList<MorsCodes>();


    public AdapterLetters(Context context, ArrayList<MorsCodes> morsCodes) {
        this.context = context;
        this.morsCodes = morsCodes;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row= inflater.inflate(R.layout.layout_morse,parent,false);
        Item item = new Item(row);

        return item;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        ((Item)holder).Letter.setText(morsCodes.get(position).getLetter());
        ((Item)holder).Morse.setText(morsCodes.get(position).getMorsecode());
    }


    @Override
    public int getItemCount() {
        return morsCodes.size();
    }


    public class Item extends RecyclerView.ViewHolder{
        TextView Letter;
        TextView Morse;


        public Item(View itemView){
            super(itemView);
            Letter = itemView.findViewById(R.id.Letter);
            Morse = itemView.findViewById(R.id.Morse);

        }
    }

}

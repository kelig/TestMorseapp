package com.example.morsehorde.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morsehorde.R;
import com.example.morsehorde.models.MorseTranslation;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterWordList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<MorseTranslation> morseTranslation = new ArrayList<MorseTranslation>();


    public AdapterWordList(Context context, ArrayList<MorseTranslation> morseTranslation) {
        this.context = context;
        this.morseTranslation = morseTranslation;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row= inflater.inflate(R.layout.layout_list,parent,false);
        Item item = new Item(row);

        return item;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        ((Item)holder).textView.setText(morseTranslation.get(position).toString());

    }


    @Override
    public int getItemCount() {
        return morseTranslation.size();
    }


    public class Item extends RecyclerView.ViewHolder{
        TextView textView;
        public Item(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }
    }

}

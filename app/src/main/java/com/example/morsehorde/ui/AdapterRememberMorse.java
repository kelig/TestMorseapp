package com.example.morsehorde.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morsehorde.R;
import com.example.morsehorde.database.morseEntity;

import java.util.ArrayList;
import java.util.List;


public class AdapterRememberMorse  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<morseEntity> morseEntities;



    public AdapterRememberMorse(Context context, List<morseEntity> morseEntities) {
        this.context = context;
        this.morseEntities = morseEntities;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).textView.setText(morseEntities.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return morseEntities.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textView;
        public Item(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.itemnote);
        }
    }
}

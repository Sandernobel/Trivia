package com.example.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {

    // Initialize arraylist
    private ArrayList<Highscore> highscores;

    // Constructor
    public HighscoreAdapter(Context context, int resource, ArrayList<Highscore> objects) {
        super(context, resource, objects);
        highscores = objects;
    }

    // Apply adapter to relevant layout
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_row, parent, false);
        }
        TextView name = convertView.findViewById(R.id.nameResult);
        name.setText(highscores.get(position).getName());

        TextView price = convertView.findViewById(R.id.scoreResult);
        price.setText(String.valueOf(highscores.get(position).getScore()));

        return convertView;
    }
}
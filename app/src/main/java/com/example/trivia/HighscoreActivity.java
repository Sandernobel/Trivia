package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreActivity extends AppCompatActivity implements GetHighscoreRequest.Callback {

    // Initialize Listview
    ListView highscore_view;
    float score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Get intent
        Intent intent = getIntent();
        float score = intent.getFloatExtra("score", 0);

        // Show score
        TextView your_score = findViewById(R.id.your_score);
        your_score.setText("Your score: " + String.valueOf(score));

        // Get highscores
        GetHighscoreRequest x = new GetHighscoreRequest(this);
        x.getHighscores(this);
    }

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {
        // Apply sorter to highscores
        Sorter sorter = new Sorter();
        Collections.sort(highscores, sorter);

        // Apply adapter to listview
        highscore_view = findViewById(R.id.highscore_list);
        HighscoreAdapter adapter = new HighscoreAdapter(this, android.R.layout.simple_list_item_1, highscores);
        highscore_view.setAdapter(adapter);
    }

    @Override
    public void gotHighscoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

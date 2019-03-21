package com.example.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetHighscoreRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    public interface Callback {
        // Initialize callback methods
        void gotHighscores(ArrayList<Highscore> highscores);
        void gotHighscoresError(String message);
    }

    // Initialize variables
    public Callback highscores;
    public Context context;

    // Constructor
    public GetHighscoreRequest(Context context) {
        this.context = context;
    }

    // Get highscores from local server
    public void getHighscores(Callback activity){
        highscores = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-sandernobel.legacy.cs50.io:8080/highscore";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        highscores.gotHighscoresError(message);
    }

    // Loop over all highscores and add them to arraylist
    public void onResponse(JSONArray response) {
        ArrayList<Highscore> highscoreList = new ArrayList<>();
        try {
            for (int position = 0; position < response.length(); position++) {
                JSONObject highscore = response.getJSONObject(position);
                String name = highscore.getString("Name");
                String score = highscore.getString("Score");
                highscoreList.add(new Highscore(name, Float.parseFloat(score)));
            }
        }
        catch(JSONException error) {
            error.printStackTrace();
        }
        highscores.gotHighscores(highscoreList);
    }
}

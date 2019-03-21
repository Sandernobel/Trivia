package com.example.trivia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PostHighscoreRequest extends StringRequest {

    // Initialize variables
    String high_name, high_score;

    // Constructor
    public PostHighscoreRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener error_listener) {
        super(method, url, listener, error_listener);
    }

    // Method to supply parameters to the request
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("Name", high_name);
        params.put("Score", high_score);
        return params;
    }

    public void postHighscore(String name, String score) {
        high_name = name;
        high_score = score;
    }
}

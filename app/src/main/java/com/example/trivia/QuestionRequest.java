package com.example.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        // Initialize callback methods
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    public Callback questions;
    public Context context;

    public QuestionRequest(Context context) {
        this.context = context;
    }

    public void getQuestions(Callback activity, String difficulty){
        questions = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://opentdb.com/api.php?amount=10&category=9&difficulty="+difficulty+"&type=boolean";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        questions.gotQuestionsError(message);
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            JSONArray questions = response.getJSONArray("results");
            for (int position = 0; position < questions.length(); position++) {
                JSONObject question = (JSONObject) questions.get(position);
                String q = question.getString("question");
                String corr_ans = question.getString("correct_answer");
                JSONArray incorr_ans = question.getJSONArray("incorrect_answers");

                Question question1 = new Question(q, corr_ans, incorr_ans);
                questionList.add(question1);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        questions.gotQuestions(questionList);
    }
}

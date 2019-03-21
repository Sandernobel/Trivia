package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class GameplayActivity extends AppCompatActivity implements QuestionRequest.Callback, Response.Listener, Response.ErrorListener{

    // Initialize variables
    private int question_index;
    private double multiplier;
    private float score;
    private TextView question_view, score_view;
    private ArrayList<Question> questions;
    private String correct_answer, name, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        // Get intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // Get selected difficulty and apply multiplier
        String difficulty = bundle.getString("difficulty");
        switch (difficulty) {
            case "easy":
                multiplier = 0.7;
                break;
            case "medium":
                multiplier = 1;
                break;
            case "hard":
                multiplier = 1.3;
                break;
        }

        // Get name
        name = bundle.getString("name");

        // Get questions
        QuestionRequest x = new QuestionRequest(this);
        x.getQuestions(this, difficulty);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        // Get question list and start keeping track of index and score
        this.questions = questions;
        question_index = 0;
        score = 0;
        nextQuestion(question_index);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(Object response) {
        Log.d("response", response.toString());
    }

    public void checkCorrect(View v) {
        // See if user pressed true or false
        Button answer_button = (Button) v;
        String answer = answer_button.getText().toString();

        // If user pressed right answer, give 10 points times multiplier
        if (answer.equals(correct_answer)) {
            Toast.makeText(this, "That's right!", Toast.LENGTH_SHORT).show();
            score += (10 * multiplier);
        }
        else {
            Toast.makeText(this, "That's wrong!", Toast.LENGTH_SHORT).show();
        }
        // Go to next question index
        question_index ++;

        // If all questions are answered
        if (question_index == 10) {
            Toast.makeText(this, "That's it!", Toast.LENGTH_SHORT).show();
            // Pass intent to next activity
            Intent intent = new Intent(this, HighscoreActivity.class);
            intent.putExtra("score", score);

            // Post score to local server
            url = "https://ide50-sandernobel.legacy.cs50.io:8080/highscore";
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            PostHighscoreRequest request = new PostHighscoreRequest(Request.Method.POST, url, this, this);
            request.postHighscore(name, String.valueOf(score));
            queue.add(request);

            // Start next activity and finish this one
            startActivity(intent);
            finish();
        }
        else {
            // Go to next question
            nextQuestion(question_index);
        }
    }

    public void nextQuestion(int question_index) {
        // Get current question
        Question question = questions.get(question_index);

        // Show question
        question_view = findViewById(R.id.question);
        question_view.setText(question.getQuestion());

        // Show score
        score_view = findViewById(R.id.score);
        score_view.setText("Score: " + score);

        // Get correct answer
        correct_answer = question.getCorrect_answer();
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

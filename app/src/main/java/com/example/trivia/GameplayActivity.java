package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameplayActivity extends AppCompatActivity implements QuestionRequest.Callback{

    private int question_index, score;
    private TextView question_view, score_view;
    private ArrayList<Question> questions;
    private String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String difficulty = bundle.getString("difficulty");
        String name = bundle.getString("name");

        QuestionRequest x = new QuestionRequest(this);
        x.getQuestions(this, difficulty);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {

        this.questions = questions;
        question_index = 0;
        score = 0;
        nextQuestion(question_index);
    }

    public void checkCorrect(View v) {
        Button answer_button = (Button) v;
        String answer = answer_button.getText().toString();

        if (answer.equals(correct_answer)) {
            Toast.makeText(this, "That's right!", Toast.LENGTH_LONG).show();
            score += 10;
        }
        else {
            Toast.makeText(this, "That's wrong!", Toast.LENGTH_LONG).show();
        }
        question_index ++;

        if (question_index == 10) {
            Toast.makeText(this, "That's it!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HighscoreActivity.class);

        }
        Log.d("question", String.valueOf(question_index));
        nextQuestion(question_index);
    }

    public void nextQuestion(int question_index) {
        Question question = questions.get(question_index);

        question_view = findViewById(R.id.question);
        question_view.setText(question.getQuestion());

        score_view = findViewById(R.id.score);
        score_view.setText("Score: " + score);

        correct_answer = question.getCorrect_answer();
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

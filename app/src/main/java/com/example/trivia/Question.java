package com.example.trivia;

import org.json.JSONArray;

public class Question {

    // Initialize variables
    private String question, correct_answer;

    // Constructor
    public Question (String question, String correct_answer) {
        this.question = question;
        this.correct_answer = correct_answer;
    }

    // Getter methods
    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }
}

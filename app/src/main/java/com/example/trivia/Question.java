package com.example.trivia;

import org.json.JSONArray;

public class Question {

    private String question, correct_answer;
    private JSONArray incorrect_answer;

    public Question (String question, String correct_answer, JSONArray incorrect_answer) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answer = incorrect_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public JSONArray getIncorrect_answer() {
        return incorrect_answer;
    }

    public void setIncorrect_answer(JSONArray incorrect_answer) {
        this.incorrect_answer = incorrect_answer;
    }
}

package com.example.trivia;

public class Highscore {

    private String name;
    private float score;

    public Highscore(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public float getScore() {
        return score;
    }
}

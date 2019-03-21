package com.example.trivia;

import java.util.Comparator;

// Compares two scores with each other
public class Sorter implements Comparator<Highscore> {
    @Override
    public int compare(Highscore score1, Highscore score2) {
        return Float.compare(score2.getScore(), score1.getScore());
    }
}
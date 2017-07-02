package com.example.lion.ch031_flagsquizapp.Model;

/**
 * Created by Lion on 6/30/2017.
 */

public class Ranking {
    private int ID;
    private int Score;

    public Ranking(int ID, int score) {
        this.ID = ID;
        Score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}

package com.example.namankhanna.basketballscoreboard;

public class Player {
    String name, id;
    int score, tNum;

    public Player() {

    }

    public Player(String name, String id, int score, int tNum) {
        this.name = name;
        this.id = id;
        this.score = score;
        this.tNum = tNum;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int gettNum() {
        return tNum;
    }
}

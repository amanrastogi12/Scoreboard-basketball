package com.example.namankhanna.basketballscoreboard;

public class Teams {

    String team1, team2, key;

    public Teams() {

    }

    public Teams(String team1, String team2, String key) {
        this.team1 = team1;
        this.team2 = team2;
        this.key = key;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getKey() {
        return key;
    }
}

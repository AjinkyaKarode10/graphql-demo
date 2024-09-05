package com.example.graphqldemo.model;

import org.springframework.data.annotation.Id;

public class Player {

    @Id
    String id;
    String playerId;
    String name;
    Team team;

    public Player(String playerId,String name,Team team) {
        this.playerId = playerId;
        this.name = name;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

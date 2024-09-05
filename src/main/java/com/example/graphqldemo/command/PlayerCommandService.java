package com.example.graphqldemo.command;

import com.example.graphqldemo.model.Player;
import com.example.graphqldemo.model.Team;

public interface PlayerCommandService {
    public Player createPlayer(String playerId, String name, Team team);
    public Player updatePlayer(String playerId, String name, Team team);
}

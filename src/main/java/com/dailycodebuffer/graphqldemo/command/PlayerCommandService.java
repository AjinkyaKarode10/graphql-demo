package com.dailycodebuffer.graphqldemo.command;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;

public interface PlayerCommandService {
    public Player createPlayer(String playerId, String name, Team team);
    public Player updatePlayer(String playerId, String name, Team team);
}

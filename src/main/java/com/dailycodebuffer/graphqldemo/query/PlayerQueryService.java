package com.dailycodebuffer.graphqldemo.query;

import com.dailycodebuffer.graphqldemo.model.Player;

import java.util.List;

public interface PlayerQueryService {

    public List<Player> findAllPLayers();
    public Player findByPlayerId(String playerId);
}

package com.example.graphqldemo.query;

import com.example.graphqldemo.model.Player;

import java.util.List;

public interface PlayerQueryService {

    public List<Player> findAllPLayers();
    public Player findByPlayerId(String playerId);
}

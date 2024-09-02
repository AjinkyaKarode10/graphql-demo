package com.dailycodebuffer.graphqldemo.query;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.resolver.PlayerQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerQueryServiceImpl implements PlayerQueryService {

    private final PlayerQueryResolver playerQueryResolver;

    public PlayerQueryServiceImpl(PlayerQueryResolver playerQueryResolver) {
        this.playerQueryResolver = playerQueryResolver;
    }

    public List<Player> findAllPLayers() {
        return playerQueryResolver.findAll();
    }

    public Player findByPlayerId(String playerId) {
        return playerQueryResolver.findOne(playerId);
    }
}

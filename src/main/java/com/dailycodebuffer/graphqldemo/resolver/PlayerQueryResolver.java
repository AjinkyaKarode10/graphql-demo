package com.dailycodebuffer.graphqldemo.resolver;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlayerQueryResolver {

    PlayerRepository playerRepository;
    public PlayerQueryResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findOne(String pId) {
        Optional<Player> playerOp = playerRepository.findByPlayerId(pId);
        return playerOp.orElse(null);
    }
}

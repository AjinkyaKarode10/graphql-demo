package com.dailycodebuffer.graphqldemo.resolver;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerMutationResolver {

    PlayerRepository playerRepository;
    public PlayerMutationResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player create(Player player) {
        playerRepository.save(player);
        return player;
    }

    public Player update(Player player) {
        Player updatedPlayerRecord;
        Optional<Player> optional = playerRepository.findByPlayerId(player.getPlayerId());

        if (optional.isPresent()) {
            Player existingPlayer = optional.get();
            existingPlayer.setName(player.getName());
            existingPlayer.setTeam(player.getTeam());
            updatedPlayerRecord = playerRepository.save(player);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayerRecord;
    }
}

package com.dailycodebuffer.graphqldemo.command;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.resolver.PlayerMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class PlayerCommandServiceImpl implements PlayerCommandService {

    private final PlayerMutationResolver playerMutationResolver;

    public PlayerCommandServiceImpl(PlayerMutationResolver playerMutationResolver) {
        this.playerMutationResolver = playerMutationResolver;
    }

    public Player createPlayer(String playerId, String name, Team team) {
        Player player = new Player(playerId, name, team);
        return playerMutationResolver.create(player);
    }

    public Player updatePlayer(String playerId, String name, Team team) {
        Player player = new Player(playerId, name, team);
        return playerMutationResolver.update(player);
    }
}

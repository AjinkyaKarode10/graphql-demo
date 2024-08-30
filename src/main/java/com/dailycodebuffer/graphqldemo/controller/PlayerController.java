package com.dailycodebuffer.graphqldemo.controller;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.PlayerRecord;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Player findOne(@Argument String playerId) {
        return playerService.findOne(playerId);
    }

    @MutationMapping
    public Player create(@Argument String playerId, @Argument String name, @Argument Team team) {
        return playerService.create(playerId,name,team);
    }

    @MutationMapping
    public Player update(@Argument String playerId, @Argument String name, @Argument Team team) {
        return playerService.update(playerId,name,team);
    }

//    @MutationMapping
//    public Player delete(@Argument Integer id) {
//        return playerService.delete(id);
//    }
}

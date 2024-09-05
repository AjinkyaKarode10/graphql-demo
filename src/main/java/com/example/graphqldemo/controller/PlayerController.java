package com.example.graphqldemo.controller;

import com.example.graphqldemo.command.PlayerCommandServiceImpl;
import com.example.graphqldemo.query.PlayerQueryServiceImpl;
import com.example.graphqldemo.model.Player;
import com.example.graphqldemo.model.Team;
import com.example.graphqldemo.resolver.PlayerSubscriptionResolver;
import jakarta.annotation.PostConstruct;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;

@Controller
public class PlayerController {

//    private final PlayerService playerService;
//
//    public PlayerController(PlayerService playerService) {
//        this.playerService = playerService;
//    }
    @Autowired
    PlayerSubscriptionResolver playerSubscriptionResolver;
    private FluxSink<Player> playerStream;
    private ConnectableFlux<Player> playerPublisher;

    @PostConstruct
    public void init() {

        Flux<Player> publisher = Flux.create(emitter -> {
            playerStream = emitter;
        });
        playerPublisher = publisher.publish();
        playerPublisher.connect();
    }

    private final PlayerCommandServiceImpl playerCommandServiceImpl;
    private final PlayerQueryServiceImpl playerQueryServiceImpl;

    public PlayerController(PlayerCommandServiceImpl playerCommandServiceImpl, PlayerQueryServiceImpl playerQueryServiceImpl) {
        this.playerCommandServiceImpl = playerCommandServiceImpl;
        this.playerQueryServiceImpl = playerQueryServiceImpl;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerQueryServiceImpl.findAllPLayers();
    }

    @QueryMapping
    public Player findOne(@Argument String playerId) {
        return playerQueryServiceImpl.findByPlayerId(playerId);
    }

    @MutationMapping
    public Player create(@Argument String playerId, @Argument String name, @Argument Team team) {
        Player player = playerCommandServiceImpl.createPlayer(playerId,name,team);
        playerStream.next(player);
        return player;
    }

    @MutationMapping
    public Player update(@Argument String playerId, @Argument String name, @Argument Team team) {
        Player updatedPlayer = playerCommandServiceImpl.updatePlayer(playerId,name,team);
        playerStream.next(updatedPlayer);
        return updatedPlayer;
    }

//    @SubscriptionMapping
//    public Publisher<Player> playerCreated() {
//        return playerPublisher;
//    }

    @SubscriptionMapping
    public Flux<Player> playerCreated() {
        return playerSubscriptionResolver.playerCreated();
    }

    @SubscriptionMapping
    public Publisher<Player> playerUpdated() {
        return playerPublisher;
    }
}

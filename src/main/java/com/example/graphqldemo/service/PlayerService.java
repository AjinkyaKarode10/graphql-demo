package com.example.graphqldemo.service;

import com.example.graphqldemo.model.Player;
import com.example.graphqldemo.model.Team;
import com.example.graphqldemo.repository.PlayerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;


    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findOne(String pId) {
        Optional<Player> pLayerOp = playerRepository.findByPlayerId(pId);
        return pLayerOp.orElse(null);
    }

    public Player create(String playerId, String name, Team team) {
        Player player = new Player(playerId, name, team);
        playerRepository.save(player);
        return player;
    }

//    public Player delete(Integer id) {
//        Player player = players.stream().filter(c -> c.Id() == id)
//                .findFirst().orElseThrow(() -> new IllegalArgumentException());
//        players.remove(player);
//        return player;
//    }

    public Player update(String playerId, String name, Team team) {
        Player updatedPlayerRecord;
        Optional<Player> optional = playerRepository.findByPlayerId(playerId);

        if (optional.isPresent()) {
            Player player = optional.get();
            player.setName(name);
            player.setTeam(team);
            updatedPlayerRecord = playerRepository.save(player);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayerRecord;
    }

    @PostConstruct
    private void init() {
        if (playerRepository.count() == 0) {
            playerRepository.save((new Player("100", "MS Dhoni", Team.CSK)));
            playerRepository.save((new Player("101", "Rohit Sharma", Team.MI)));
            playerRepository.save((new Player("102", "Jasprit Bumrah", Team.MI)));
            playerRepository.save((new Player("103", "Rishabh pant", Team.DC)));
            playerRepository.save((new Player("104", "Suresh Raina", Team.CSK)));
        }

    }
}

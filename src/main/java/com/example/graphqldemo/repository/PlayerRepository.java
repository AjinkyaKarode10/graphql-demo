package com.example.graphqldemo.repository;

import com.example.graphqldemo.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByPlayerId(String pId);
}

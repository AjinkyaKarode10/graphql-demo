package com.dailycodebuffer.graphqldemo.repository;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.PlayerRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByPlayerId(String pId);
}

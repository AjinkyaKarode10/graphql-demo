package com.dailycodebuffer.graphqldemo.resolver;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.repository.PlayerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class PlayerSubscriptionResolver {
    PlayerMutationResolver playerMutationResolver;
    public PlayerSubscriptionResolver(PlayerMutationResolver playerMutationResolver) {
        this.playerMutationResolver = playerMutationResolver;
    }

    public Flux<Player> playerCreated() {
        return playerMutationResolver.playerCreated();
    }

}

package com.dailycodebuffer.graphqldemo.controller;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.PlayerRecord;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

@Import(PlayerService.class)
@GraphQlTest(PlayerController.class)
class PlayerRecordControllerTest {

    @Autowired
    GraphQlTester tester;

    @Autowired
    PlayerService playerService;

    @Test
    void testFindAllPlayerShouldReturnAllPlayers() {
        String document = """
                query MyQuery {
                  findAll {
                    id
                    name
                    team
                  }
                }
                """;

        tester.document(document)
                .execute()
                .path("findAll")
                .entityList(PlayerRecord.class)
                .hasSizeGreaterThan(3);
    }

    @Test
    void testValidIdShouldReturnPlayer() {
        String document = """
                query findOne($id: ID) {
                  findOne(id: $id) {
                    id
                    name
                    team
                  }
                }
                """;

        tester.document(document)
                .variable("id",1)
                .execute()
                .path("findOne")
                .entity(PlayerRecord.class)
                .satisfies(playerRecord -> {
                    Assertions.assertEquals("MS Dhoni", playerRecord.name());
                    Assertions.assertEquals(Team.CSK, playerRecord.team());
                });
    }

    @Test
    void testInvalidIdShouldReturnNull() {
        String document = """
                query findOne($id: ID) {
                  findOne(id: $id) {
                    id
                    name
                    team
                  }
                }
                """;

        tester.document(document)
                .variable("id", 100)
                .execute()
                .path("findOne")
                .valueIsNull();
    }

    @Test
    void testShouldCreateNewPlayer() {
        int currentCount = playerService.findAll().size();
        String document = """
                mutation create($name: String, $team: Team) {
                  create(name: $name, team: $team) {
                    id
                    name
                    team
                  }
                }
                """;

        tester.document(document)
                .variable("name","Virat Kohli")
                .variable("team", Team.RCB)
                .execute()
                .path("create")
                .entity(PlayerRecord.class)
                .satisfies(playerRecord -> {
                    Assertions.assertEquals("Virat Kohli", playerRecord.name());
                    Assertions.assertEquals(Team.RCB, playerRecord.team());
                });

        Assertions.assertEquals(currentCount + 1 , playerService.findAll().size());
    }

    @Test
    void testShouldUpdateExistingPlayer() {
        String document = """
                mutation update($id: ID, $name: String, $team: Team) {
                  update(id: $id, name: $name, team: $team) {
                    id
                    name
                    team
                  }
                }
                """;
        tester.document(document)
                .variable("id",3)
                .variable("name","Updated Jasprit Bumrah")
                .variable("team", Team.CSK)
                .execute()
                .path("update")
                .entity(PlayerRecord.class);

        Player updatePlayerRecord = playerService.findOne("3");
        Assertions.assertEquals("Updated Jasprit Bumrah", updatePlayerRecord.getName());
        Assertions.assertEquals(Team.CSK, updatePlayerRecord.getTeam());
    }

    @Test
    void testShouldRemovePlayerWithValidId() {
        int currentCount = playerService.findAll().size();

        String document = """
                mutation delete($id: ID) {
                  delete(id: $id) {
                    id
                    name
                    team
                  }
                }
                """;

        tester.document(document)
                .variable("id", 3)
                .executeAndVerify();

        Assertions.assertEquals(currentCount -1, playerService.findAll().size());
    }
}
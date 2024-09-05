## Spring Boot GraphQL Query,Mutation,Subscription API

### Technologies
1. Java 17

### Build
mvn clean install

Open http://localhost:8080/graphiql for GraphQL introspection.

GraphQL Query Request
```graphql
query MyQuery {
    findOne(playerId: "100") {
        id
        playerId
        name
        team
    }
}
```

Response
```graphql
{
"data": {
"findOne": {
"id": "66d6cd3a8143e41165d53fa3",
"playerId": "100",
"name": "MS Dhoni",
"team": "CSK"
}
}
}
```

GraphQL Mutation Request
```graphql
mutation MyMutation {
    create(playerId:"111",team: RCB, name: "Henrich Klaseen") {
        id
    }
    # update(playerId:"105",team: RCB, name:"Virat Kohli"){
    #   id
    #   playerId
    #   name
    #   team
    # }
}
```

Response
```graphql
{
"data": {
"create": {
"id": "66d94c93196c582588eef47b"
}
}
}
```

GraphQL Subscription Request
```graphql
subscription Sub{
    playerCreated{
        playerId
        name
    }
    # playerUpdated{
    #   playerId
    #   name
    #   }
} 
```
Subscription Request opens a Websocket connection and whenever there is mutation operations like creation/updation we can see the output.

Response
```graphql
{
"data": {
"playerCreated": {
"playerId": "111",
"name": "Henrich Klaseen"
}
}
}
```
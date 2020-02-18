package com.codeoftheweb.salvo;
//package Game.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
    @Autowired SalvoRepository salvoRepository;

    @RequestMapping("/games")
    public List<Map> getAll() {
        List<Game> games = gameRepository.findAll();
        List<Map> gamesList = new ArrayList<>();

        games.forEach(game -> {
            System.out.println(game.getPlayers());
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id", game.getId());
            gameMap.put("created", game.getDate());
            gameMap.put("gamePlayers", game.getPlayers());
        gamesList.add(gameMap);
        });
        return gamesList;
    };

    @RequestMapping("/games/{id}")
    public List<Map> getAll(@PathVariable long id) {
        List<Game> games = gameRepository.findAllById(Collections.singleton(id));
        List<Map> gamesList = new ArrayList<>();

        games.forEach(game -> {
            System.out.println(game.getPlayers());
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id", game.getId());
            gameMap.put("created", game.getDate());
            gameMap.put("gamePlayers", game.getPlayers());
            gamesList.add(gameMap);
        });
        return gamesList;
    };

    @RequestMapping("/gamePlayers")
    public List<Map> getGamePlayerId(){
        //System.out.println(id);
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAll();
        List<Map> gamesList = new ArrayList<>();


        for (GamePlayer gamePlayer: gamePlayers) {
            //System.out.println(game.getPlayers());
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id_gamePlayer", gamePlayer.getId());;
            gameMap.put("created", gamePlayer.getGame().getDate());
            gameMap.put("players", gamePlayer.getPlayer());
            gameMap.put("ships", gamePlayer.getShips());
            gameMap.put("salvos", gamePlayer.getSalvos());
            gamesList.add(gameMap);
        };
        return gamesList;
    }

    @RequestMapping("/gamePlayers/{id}")
    public List<Map> getGamePlayerId(@PathVariable long id){
        //System.out.println(id);
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAllById(Collections.singleton(id));
        List<Map> gamesList = new ArrayList<>();

        for (GamePlayer gamePlayer: gamePlayers) {
            //System.out.println(game.getPlayers());
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id_gamePlayer", gamePlayer.getId());;
            gameMap.put("created", gamePlayer.getGame().getDate());
            gameMap.put("players", gamePlayer.getPlayer());
            gameMap.put("ships", gamePlayer.getShips());
            gameMap.put("salvos", gamePlayer.getSalvos());
            gamesList.add(gameMap);
        };
        return gamesList;
    }
}
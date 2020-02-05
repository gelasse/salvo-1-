package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
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
    private GamePlayerRepository gamePlayerRepository;

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
    }
}


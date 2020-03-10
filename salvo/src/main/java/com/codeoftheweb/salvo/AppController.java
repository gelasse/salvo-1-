package com.codeoftheweb.salvo;
//package Game.java;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private SalvoRepository salvoRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @RequestMapping("/games")
    public List<Map> getAll(Authentication authentication) {
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

    public boolean getLogin(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    @RequestMapping("/games/{id}")
    public List<Map> getAll(@PathVariable long id, Authentication authentication) {
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
    }

    @RequestMapping("/gamePlayers")
    public List<Map> getGamePlayerId() {
        //System.out.println(id);
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAll();
        List<Map> gamesList = new ArrayList<>();

        for (GamePlayer gamePlayer : gamePlayers) {
            //System.out.println(game.getPlayers());
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id_gamePlayer", gamePlayer.getId());
            gameMap.put("created", gamePlayer.getGame().getDate());
            gameMap.put("players", gamePlayer.getPlayer());
            gameMap.put("ships", gamePlayer.getShips());
            gameMap.put("salvos", gamePlayer.getSalvos());
            gameMap.put("score", gamePlayer.getPlayer().getScores());
            gamesList.add(gameMap);
        }
        return gamesList;
    }

    @RequestMapping("/leaderboard")
    public Set<Map<String, Object>> getLeaderBoardInfo() {
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAll();
        Set<Map<String, Object>> leaderboard = new LinkedHashSet<>();

        for (GamePlayer gamePlayer : gamePlayers) {
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("name", gamePlayer.getPlayer().getLastName());
            System.out.println(gamePlayer.getPlayer().getLastName());
            gameMap.put("totalScore", gamePlayer.getPlayer().getScores().stream().mapToDouble(score -> score.getScore()).sum());
            gameMap.put("totalWin", gamePlayer.getPlayer().getScores().stream().filter(score -> score.getScore() == 1).count());
            gameMap.put("totalLoss", gamePlayer.getPlayer().getScores().stream().filter(score -> score.getScore() == 0).count());
            gameMap.put("totalTies", gamePlayer.getPlayer().getScores().stream().filter(score -> score.getScore() == 0.5).count());
            leaderboard.add(gameMap);
        }
        return leaderboard;
    }
/*
    @RequestMapping("/gamePlayers/{id}")
    public List<Map> getGamePlayerId(@PathVariable long id, Authentication authentication) {
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAllById(Collections.singleton(id));
        List<Map> gamesList = new ArrayList<>();

        for (GamePlayer gamePlayer : gamePlayers) {
            Map<String, Object> gameMap = new LinkedHashMap<>();
            gameMap.put("id_gamePlayer", gamePlayer.getId());
            gameMap.put("created", gamePlayer.getGame().getDate());
            gameMap.put("players", gamePlayer.getPlayer());
            gameMap.put("ships", gamePlayer.getShips());
            gameMap.put("salvos", gamePlayer.getSalvos());
            gameMap.put("score", gamePlayer.getPlayer().getScores());
            gamesList.add(gameMap);
        }
        return gamesList;
    }*/

    @RequestMapping("/gamePlayers/{id}")
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable long id, Authentication authentication) {
        Map<String, Object> gameViewInfo = new LinkedHashMap<>();
        if (!getLogin(authentication)) {
            Player player = playerRepository.findByUserName(authentication.getName());
            GamePlayer currentGamePlayer = gamePlayerRepository.getOne(id);
            System.out.println(currentGamePlayer);
            System.out.println(player.getId());
            System.out.println(currentGamePlayer.getPlayer().getId());
            if (player.getId() == currentGamePlayer.getPlayer().getId()) {
                System.out.println(player.getId());
                System.out.println(currentGamePlayer.getPlayer().getId());
                gameViewInfo.put("id_gamePlayer", currentGamePlayer.getId());
                gameViewInfo.put("created", currentGamePlayer.getGame().getDate());
                gameViewInfo.put("players", currentGamePlayer.getPlayer());
                gameViewInfo.put("ships", currentGamePlayer.getShips());
                gameViewInfo.put("salvos", currentGamePlayer.getSalvos());
                gameViewInfo.put("score", currentGamePlayer.getPlayer().getScores());

                return new ResponseEntity<>(gameViewInfo, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("error", "Not allowed to view opponents game"), HttpStatus.FORBIDDEN);
            }
            } else {
            return new ResponseEntity<>(makeMap("error", "You are not logged in"), HttpStatus.UNAUTHORIZED);
        }
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
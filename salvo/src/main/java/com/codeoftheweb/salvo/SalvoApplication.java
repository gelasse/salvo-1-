package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);}

		@Bean
		public CommandLineRunner initData (PlayerRepository repository, GameRepository
		gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository){
			return (args) -> {
				// save a couple of customers
				Player player_1 = new Player("Jake", "Brauer", "jacke_brauer@gmail.com");
				Player player_2 = new Player("Allo", "hello", "allo_hello@gmail.com");
				Player player_3 = new Player("anna", "Masculi", "anna_Masculi@gmail.com");

				Date newDate_1 = new Date();
				Date newDate_1_1 = Date.from(newDate_1.toInstant().plusSeconds(3600));
				Date newDate_1_2 = Date.from(newDate_1.toInstant().plusSeconds(7200));
//			SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
//			String formattedDate = formatter.format(newDate_1_1);
//			System.out.println(formattedDate);
				Game game_1 = new Game(newDate_1);
				Game game_2 = new Game(newDate_1_1);
				Game game_3 = new Game(newDate_1_2);

				GamePlayer gamePlayer_1 = new GamePlayer(game_1, player_1);
				GamePlayer gamePlayer_2 = new GamePlayer(game_2, player_2);
				GamePlayer gamePlayer_3 = new GamePlayer(game_3, player_3);

				List<String> location1 = new ArrayList<>();
				location1.add("A1");
				location1.add("A2");
				location1.add("A3");
				location1.add("A4");
				location1.add("A5");
				List<String> location2 = new ArrayList<>();
				location2.add("B1");
				location2.add("B2");
				location2.add("B3");
				location2.add("B4");
				List<String> location3 = new ArrayList<>();
				location3.add("C1");
				location3.add("C2");
				location3.add("C3");
				Ship cruiser = new Ship("cruiser", location1, gamePlayer_1);
				//gamePlayer_1.addShip(cruiser);
				Ship destroyer = new Ship("destroyer", location2, gamePlayer_1);
				Ship battleship = new Ship("battleship", location3, gamePlayer_1);
				Ship cruiser2 = new Ship("cruiser", location1, gamePlayer_2);
				repository.save(player_1);
				repository.save(player_2);
				repository.save(player_3);
				gameRepository.save(game_1);
				gameRepository.save(game_2);
				gameRepository.save(game_3);
				gamePlayerRepository.save(gamePlayer_1);
				gamePlayerRepository.save(gamePlayer_2);
				gamePlayerRepository.save(gamePlayer_3);
				shipRepository.save(cruiser);
				shipRepository.save(destroyer);
				shipRepository.save(battleship);
				shipRepository.save(cruiser2);
			};
		}
	}


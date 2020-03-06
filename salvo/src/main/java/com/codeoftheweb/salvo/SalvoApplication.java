package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
		@Bean
		public CommandLineRunner initData (PlayerRepository repository, GameRepository
		gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository ,SalvoRepository salvoRepository, ScoreRepository scoreRepository){
			return (args) -> {
				// save a couple of customers
				Player player_1 = new Player("Jake", "Brauer", "jacke_brauer@gmail.com", passwordEncoder().encode("akolo"));
				Player player_2 = new Player("Allo", "hello", "allo_hello@gmail.com", passwordEncoder().encode("akoli"));
				Player player_3 = new Player("anna", "Masculi", "anna_Masculi@gmail.com", passwordEncoder().encode("akola"));

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
				GamePlayer gamePlayer_2 = new GamePlayer(game_1, player_2);    //gamePlayer_1.addShip(cruiser);
				GamePlayer gamePlayer_3 = new GamePlayer(game_3, player_3);

				List<String> location1 = new ArrayList<>();
				location1.add("A1");
				location1.add("A2");
				location1.add("A3");
				location1.add("J4");
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
				Ship destroyer = new Ship("destroyer", location2, gamePlayer_1);
				Ship battleship = new Ship("battleship", location3, gamePlayer_1);
				Ship cruiser2 = new Ship("cruiser", location1, gamePlayer_2);
				Ship cruiser = new Ship("cruiser", location1, gamePlayer_1);
				List<String> salvoLocation1 = new ArrayList<>();
				salvoLocation1.add("CC1");
				salvoLocation1.add("CC2");
				salvoLocation1.add("CC3");
				List<String> salvoLocation2 = new ArrayList<>();
				salvoLocation2.add("BB1");
				salvoLocation2.add("BB2");
				salvoLocation2.add("BB3");
				List<String> salvoLocation3 = new ArrayList<>();
				salvoLocation3.add("AA1");
				salvoLocation3.add("AA2");
				salvoLocation3.add("AA3");
				salvoLocation3.add("AA4");
				salvoLocation3.add("AA5");
				Salvo salvo1_1 = new Salvo(1, gamePlayer_1, salvoLocation1);
				Salvo salvo1_2 = new Salvo(2, gamePlayer_1, salvoLocation2);
				Salvo salvo2_1 = new Salvo(1, gamePlayer_2, salvoLocation2);
				Salvo salvo2_2 = new Salvo(2, gamePlayer_2, salvoLocation3);
				Salvo salvo3 = new Salvo(3, gamePlayer_3, salvoLocation3);
				Score score_1 = new Score(game_1, player_1, 0);
				Score score_2 = new Score(game_2, player_1, 0.5);
				Score score_3 = new Score(game_3, player_2, 1);
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
				salvoRepository.save(salvo1_1);
				salvoRepository.save(salvo1_2);
				salvoRepository.save(salvo2_2);
				salvoRepository.save(salvo2_1);
				salvoRepository.save(salvo3);
				scoreRepository.save(score_1);
				scoreRepository.save(score_2);
				scoreRepository.save(score_3);
			};
			}
	}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName-> {
			System.out.println(inputName);
			Player player = playerRepository.findByUserName(inputName);
			if (player != null) {
                System.out.println("logging in");
				return new User(player.getUserName(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private WebApplicationContext applicationContext;
	private WebSecurityConfiguration webSecurityConfiguration;    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/rest/players*").permitAll()
				.antMatchers("web/game.js").permitAll()
				.antMatchers("/manager.html*").permitAll()
				.antMatchers("/manager.js*").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/web/home*").permitAll()
				.antMatchers("/web/players*").permitAll()
				.antMatchers("/api/leaderboard/*").permitAll()
				.antMatchers("/api/games/**").permitAll()
				.antMatchers("/api/login*").permitAll()
				.antMatchers("/web/game*").permitAll()
				.antMatchers("/api/gamePlayers/**").permitAll()
				.antMatchers("/web/login*").permitAll()
				.antMatchers("/web/game_view*").permitAll()
				.antMatchers("/**").hasAuthority("USER").anyRequest().authenticated()
				.and().formLogin()
				.usernameParameter("userName")
				.passwordParameter("password")
				.loginPage("/api/login")
				.and().logout()
				.logoutUrl("/api/logout");        // if user is not authenticated, just send an authentication failure response
		http.exceptionHandling()
				.authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));        // if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));        // if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));        // if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}    private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}}
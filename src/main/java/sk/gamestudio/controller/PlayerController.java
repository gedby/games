package sk.gamestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.gamestudio.entity.Player;
import sk.gamestudio.service.PlayerService;
import sk.gamestudio.service.PlayerServiceJDBC;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	
	private Player loggedPlayer;

	private String message;

	public String getMessage() {
		return message;
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/player")
	public String player() {
		message = "Please login or register!";
		return "login";
	}
	//doplnam register 2 a highscores
	@RequestMapping("/register2")
	public String register2() {
		message = "Please register!";
		return "register";
	}
	
	@RequestMapping("/highscores2")
	public String highscores2() {
		return "highscores2";
	}
	
	@RequestMapping("/index2")
	public String index2() {
		return "index";
	}

	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		return "index";
	}

	@RequestMapping("/login")
	public String login(@RequestParam(required = true) String name, @RequestParam(required = true) String password) {
		loggedPlayer = playerService.login(name, password);
		if (loggedPlayer != null) {
			return "index";
		}
		message = "Wrong login or password";
		return "login";
	}

	@RequestMapping("/register")
	public String register(String name, String password, String password2) {
		if (password != null && !password.equals(password2)) {
			message = "Passwords do not match!";
			return "login";
		}

		Player player = new Player(name, password);
		playerService.register(player);
		loggedPlayer = player;
		if (loggedPlayer != null) {
			return "index";
		}
		return "login";
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}

	public Player getLoggedPlayer() {
		return loggedPlayer;
	}
}

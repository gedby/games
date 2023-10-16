package sk.gamestudio.service;

import sk.gamestudio.entity.Player;

public class PlayerServiceNaive implements PlayerService {

	@Override
	public Player login(String name, String password) {
		if("admin".equals(name) && "admin".equals(password))
			return new Player(name, password);
		return null;
	}

	@Override
	public void register(Player player) {
		
	}
}

package sk.gamestudio.service;

import sk.gamestudio.entity.Player;

public interface PlayerService {
	Player login(String name, String password);
	
	void register(Player player);
}

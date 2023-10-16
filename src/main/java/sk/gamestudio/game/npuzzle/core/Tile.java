package sk.gamestudio.game.npuzzle.core;

import java.io.Serializable;

public class Tile implements Serializable {
	private final int value;
	
	public Tile(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

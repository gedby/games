package sk.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	@GeneratedValue
	private int ident;
	
	private String player;
	
	private String game;
	
	private int value;
	
	public Score() {
	}

	public Score(String player, String game, int value) {
		this.player = player;
		this.game = game;
		this.value = value;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}
	
	@Override
	public String toString() {
		return "Score [player=" + player + ", game=" + game + ", value=" + value + "]";
	}
}

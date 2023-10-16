package sk.gamestudio.game.npuzzle;

import sk.gamestudio.game.npuzzle.consoleui.ConsoleUI;
import sk.gamestudio.game.npuzzle.core.Field;

public class Main {

	public static void main(String[] args) {
		Field field = Field.load();
		if (field == null)
			field = new Field(4, 4);
		else
			System.out.println("Field loaded!");
		
		ConsoleUI ui = new ConsoleUI(field);
		ui.play();
	}
}

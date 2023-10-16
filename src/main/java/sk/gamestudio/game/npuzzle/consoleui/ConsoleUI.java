package sk.gamestudio.game.npuzzle.consoleui;

import java.io.IOException;
import java.util.Scanner;

import sk.gamestudio.game.npuzzle.core.Field;
import sk.gamestudio.game.npuzzle.core.Tile;

public class ConsoleUI {
	private Field field;

	public ConsoleUI(Field field) {
		super();
		this.field = field;
	}
	
	public void play() {
		do {
			printField();
			processInput();
		} while(!field.isSolved());
		
		printField();
		System.out.println("You won!");
	}

	private void printField() {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				if (tile == null)
					System.out.print("   ");
				else
					System.out.printf("%3d", tile.getValue());
			}
			System.out.println();
		}
	}

	
	private void processInput() {
		System.out.print("Enter tile number to move (X to exit): ");
		String line = new Scanner(System.in).nextLine().trim().toUpperCase();
		if("X".equals(line)) {
			try {
				field.save();
				System.out.println("Game saved!");
			} catch (IOException e) {
				System.out.println("Game not saved!");
				e.printStackTrace();
			}
			System.exit(0);
		}
		try {
			int tileNumber = Integer.parseInt(line);
			if(!field.move(tileNumber))
				System.out.println("Invalid move!");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input!");
		}
	}
}

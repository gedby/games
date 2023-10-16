package sk.gamestudio.game.rps;

import java.util.Scanner;
import java.util.Random;
import sk.gamestudio.game.rps.consoleui.*;
import sk.gamestudio.game.rps.core.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("zadaj velkost rowCount: ");
		String rowCount = sc.nextLine();
		int result1 = Integer.parseInt(rowCount);
		System.out.println("zadaj velkost columnCount: ");
		String columnCount = sc.nextLine();
		int result2 = Integer.parseInt(columnCount);
		Field field = new Field(result1, result2);
		field.generate();
		ConsoleUI ui = new ConsoleUI(field);
		ui.run();
	
			
			
	}
}

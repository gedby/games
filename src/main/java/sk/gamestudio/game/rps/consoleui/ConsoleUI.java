package sk.gamestudio.game.rps.consoleui;
import sk.gamestudio.game.rps.core.*;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ConsoleUI {
//zadaj velkost pola a pocet RSP
//zadaj meno pre player1 a vyber si kde chces mat vlajku a pascu a ukaze mu na mape kde ma ake zbrane
//zadaj meno pre player2 a vyber si kde chces mat vlajku a pascu a ukaze mu na mape kde ma ake zbrane
//random boolean z dvoch moznosti cerveny alebo modry
//(vytvorti sa pole)
//na rade je cerveny(zadaj poziciu) RED - 1(ROCK) - 1 a sipky

	public Field field;
	private boolean rightMove = true;
	private boolean done;
	private int a;
	private int b;
	
	public ConsoleUI(Field field) {
		this.field = field;
	}
	
	public boolean getMove() {
		return rightMove;
	}
	
	public void run() {
	setBot();
	setGame();
	field.printField();	
	do {
		System.out.println("");
		System.out.println("Na tahu je Red");
		System.out.println("");
		try
		{
			Thread.sleep(3000);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
		botInput2("Red");
		//userInput2("Blue");
	} while (field.getState() == GameState.PLAYING);
	}
	
	public void setBot() {
		//field.flagAndTrap(pozicia1, pozicia2, vlajka, "Red");
		//field.flagAndTrap(pozicia3, pozicia4, pasca, "Red");
		field.flagAndTrapBot("Flag");
		field.flagAndTrapBot("Trap");
		field.setRSPRed();
		field.shuffleRed();
		field.setNumberRed();
		field.printRed();
	}
	
	
	public void setGame() {
		
		Scanner sc = new Scanner(System.in);
//		System.out.println("zadaj velkost rowCount: ");
//		String rowCount = sc.nextLine();
//		int result1 = Integer.parseInt(rowCount);
//		System.out.println("zadaj velkost columnCount: ");
//		String columnCount = sc.nextLine();
//		int result2 = Integer.parseInt(columnCount);
//		Field field = new Field(result1, result2);
//		System.out.println("zadaj meno pre playera1: ");
//		String player1 = sc.nextLine();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		field.setNumberBlue();
		field.printBlue();
		System.out.println("zadaj poziciu pre vlajku(riadok): ");
		String pozicia5 = sc.nextLine();
		System.out.println("zadaj poziciu pre vlajku(stlpec): ");
		String pozicia6 = sc.nextLine();
		String vlajka2 = "Flag";
		field.flagAndTrap(pozicia5, pozicia6, vlajka2, "Blue");
		System.out.println("zadaj poziciu pre pascu(riadok1): ");
		String pozicia7 = sc.nextLine();
		System.out.println("zadaj poziciu pre pascu(stlpec1): ");
		String pozicia8 = sc.nextLine();
		String pasca2 = "Trap";
		field.flagAndTrap(pozicia7, pozicia8, pasca2, "Blue");	
		field.setRSPBlue();
		field.shuffleBlue();
		field.setNumberBlue();
		field.printBlue();
		System.out.println("takto vyzeraju tvoji hraci, zapis si cisla a zbrane ");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	
	public void userInput2(String player, int e, int f, int g, int h) {
		Scanner sc = new Scanner(System.in);
		String pozicia1;
		String pozicia2;
		String pozicia3;
		String pozicia4;
		int a = e;
		int b = f;
		int c = g;
		int d = h;
		pozicia1 = Integer.valueOf(a).toString();
		pozicia2 = Integer.valueOf(b).toString();
		pozicia3 = Integer.valueOf(c).toString();
		pozicia4 = Integer.valueOf(d).toString();
		//System.out.println("Na tahu je "+player);
		//do {
		//System.out.println("vyber si s ktorym vojakom sa pohnes(zadaj jeho poziciu(riadok)): ");
		//pozicia1 = sc.nextLine();
		//a = Integer.parseInt(pozicia1);
		//System.out.println("vyber si s ktorym vojakom sa pohnes(zadaj jeho poziciu(stlpec)): ");
		//pozicia2 = sc.nextLine();
		//b = Integer.parseInt(pozicia2);
		//} while (field.checkPlayer(pozicia1, pozicia2, player) != true); //viem urobit field.tiles[a][a].getColour(); ?
		
		//System.out.println("vyber si kam sa pohnes(zadaj poziciu(riadok)");
		//pozicia3 = sc.nextLine();
		//c = Integer.parseInt(pozicia3);
		//System.out.println("vyber si kam sa pohnes(zadaj poziciu(stlpec)");
		//pozicia4 = sc.nextLine();
		//d = Integer.parseInt(pozicia4);
		if(c == a+1 && d == b || c == a-1 && d == b || c == a && d == b+1 || c == a && d == b-1) {
			if(c >= 0 && c < 6 && d >= 0 && d < 7) {
				field.fight(pozicia1, pozicia2, pozicia3, pozicia4);
				//field.printField();
			}
		}
		else {
			System.out.println("zadal si neplatny tah");
			rightMove = false;
		}
	}
	
	public void botInput2(String player) {
		Random random = new Random();
		int a;
		int b;
		int c;
		int d;
		int botLogicDone = 0;
		
		//System.out.println("Na tahu je "+player);
		do {
		//System.out.println("vyber si s ktorym vojakom sa pohnes(zadaj jeho poziciu(riadok)): ");
	
		a = random.nextInt(6);
		//System.out.println("vyber si s ktorym vojakom sa pohnes(zadaj jeho poziciu(stlpec)): ");
		//String pozicia1 = Integer.valueOf(a).toString();
		b = random.nextInt(7);
		//String pozicia2 = Integer.valueOf(b).toString();
		} while (field.checkBot(a, b, player) != true); //viem urobit field.tiles[a][a].getColour(); ?
		
		//System.out.println("vyber si kam sa pohnes(zadaj poziciu(riadok)");
		String pozicia1 = Integer.valueOf(a).toString();
		String pozicia2 = Integer.valueOf(b).toString();
		
		
				
	
		if(botLogicDone != 1) {		
		botLogic("Paper", "Rock");
		botLogicDone = 1;
		}
		
		if(done == false) {
		
		c = random.nextInt(6);
		String pozicia3 = Integer.valueOf(c).toString();
		//System.out.println("vyber si kam sa pohnes(zadaj poziciu(stlpec)");
	
		d = random.nextInt(7);
		String pozicia4 = Integer.valueOf(d).toString();
		if(c == a+1 && d == b || c == a-1 && d == b || c == a && d == b+1 || c == a && d == b-1) {
			if(c >= 0 && c < 6 && d >= 0 && d < 7) {
				if(field.getTile()[c][d].getColour() != "Red") {
					//if(getTile()[c][d].getValue() != "Flag") {
						//if(getTile()[c][d].getValue() != "Trap") {
							field.fight(pozicia1, pozicia2, pozicia3, pozicia4);
							//field.printField();
						//}
						//else {
						//	botInput2(player);
						//}
					//}
					//else {
					//	botInput2(player);
					//}
				}
				else {
					botInput2(player);
				}
			}
			else {
				botInput2(player);
			}
		}
		else {
			//System.out.println("zadal si neplatny tah");
			botInput2(player);
		}
		
//		do {
//			c = random.nextInt(6);
//			d = random.nextInt(7);
//		} while (c != a+1 && d != b || c != a-1 && d != b || c != a && d != b+1 || c != a && d != b-1 && c >= 0 && c < 6 && d >= 0 && d < 7 && field.getTile()[c][d].getColour() == "Red" || c == a && d == b);
//		String pozicia3 = Integer.valueOf(c).toString();
//		String pozicia4 = Integer.valueOf(d).toString();
//		field.fight(pozicia1, pozicia2, pozicia3, pozicia4);
//		field.printField();
	}
	}
	
	public void botLogic(String zbran1, String zbran2) {
		int count = 0;
		int A = (a+1);
		int B = (a-1);
		int C = (b+1);
		int D = (b-1);
		
		if(A >= 0 && A < 6 && B >= 0 && B < 7 && C >= 0 && C < 6 && D >= 0 && D < 7) {
			
			
		if(field.getTile()[a+1][b].getValue().equals("Flag")){
				field.getTile()[a+1][b] = new Tile(1, "Red", "Winner", true, false);
				field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
				done = true;
				//field.setState(GameState.SOLVED);
				return;
		}
		
		if(field.getTile()[a-1][b].getValue().equals("Flag")){
			field.getTile()[a-1][b] = new Tile(1, "Red", "Winner", true, false);
			field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
			done = true;
			//field.setState(GameState.SOLVED);
			return;
	}
		if(field.getTile()[a][b+1].getValue().equals("Flag")){
			field.getTile()[a][b+1] = new Tile(1, "Red", "Winner", true, false);
			field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
			done = true;
			//field.setState(GameState.SOLVED);
			return;
	}
		if(field.getTile()[a][b-1].getValue().equals("Flag")){
			field.getTile()[a-1][b] = new Tile(1, "Red", "Winner", true, false);
			field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
			done = true;
			//field.setState(GameState.SOLVED);
			return;
	}
		
		
		
		
			
		if(field.getTile()[a][b].getValue().equals("Paper") && field.getTile()[a+1][b].getValue().equals("Rock") && 
				field.getTile()[a+1][b].getColour().equals("Blue") && field.getTile()[a+1][b].getHide().equals(false)) {
				field.getTile()[a+1][b] = field.getTile()[a][b];
				field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
				field.getTile()[a+1][b].setHide(false);
				done = true;
				return;
		}
			
		if(field.getTile()[a][b].getValue().equals("Paper") && field.getTile()[a-1][b].getValue().equals("Rock") && 
				field.getTile()[a-1][b].getColour().equals("Blue") && field.getTile()[a-1][b].getHide().equals(false)) {
				field.getTile()[a-1][b] = field.getTile()[a][b];
				field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
				field.getTile()[a-1][b].setHide(false);
				done = true;
				return;
		}
				
		if(field.getTile()[a][b].getValue().equals("Paper") && field.getTile()[a][b+1].getValue().equals("Rock") && 
				field.getTile()[a][b+1].getColour().equals("Blue") && field.getTile()[a][b+1].getHide().equals(false)) {
				field.getTile()[a][b+1] = field.getTile()[a][b];
				field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
				field.getTile()[a][b+1].setHide(false);
				done = true;
				return;
		}		
		
		if(field.getTile()[a][b].getValue().equals("Paper") && field.getTile()[a][b-1].getValue().equals("Rock") && 
				field.getTile()[a][b-1].getColour().equals("Blue") && field.getTile()[a][b-1].getHide().equals(false)) {
				field.getTile()[a][b-1] = field.getTile()[a][b];
				field.getTile()[a][b] = new Tile(0, "temp", "temp", true, true);
				field.getTile()[a][b-1].setHide(false);
				done = true;
				return;
		}
		count++;
		if(count == 1) {
			botLogic("Scissors", "Paper");
		}
		
		if(count == 2) {
			botLogic("Rock", "Scissors");
		}
		
		if(count == 3) {
			return;
		}
		}
	}
}

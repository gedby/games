package sk.gamestudio.game.rps.core;
import java.util.Random;
import java.util.Scanner;
import sk.gamestudio.controller.RPSController;




public class Field {
//mam pole 7x6
//prvy a druhy riadok je poitac cerveny
//predposledny a posledny riadok som ja modry
//obidva timy maju 4x kamen, 4x noznice, 4x papier, 1x pasca, 1x vlajka
//ked sa stretnu vybera sa kamen papier noznice, kto prehra dlazdica sa nastava na null a pocet sa znizi
//po suboji sa zmeni stav dlazdice na B/R
//pocitac ide s random panacikom stale rovno a pyta sa - je tu super? je tu pasca? je tu vlajka? 
	
//mam pole ake velke chcem a pocet lodi kolko chcem 
//nahodne striela.. ak trafi jedno policko, striela aj dookola do kym netrafi prazdne

	private Tile[][] tiles;
	private int rowCount;
	private int columnCount;
	private int RSPcount;
	private GameState state = GameState.PLAYING;
	private String winner;
	private boolean same;
	private boolean rightMove;
	private int blue;
	private int red;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];
		//generate();
}
	
	public void setSame(boolean value) {
		this.same = value;
	}
	
	public boolean getSame() {
		return same;
	}
	
	public void generate() {
		for(int i = 0; i < rowCount; i++) {
			for(int y = 0; y < columnCount; y++) {
				if(i > 3)
					tiles[i][y] = new Tile(0, "Blue", "temp", true, true);
					
				else
					tiles[i][y] =  new Tile(0, "temp", "temp", true, true);
			}
		}
	}

	public GameState getState() {
		return state;
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String colour) {
		this.winner = winner;
	}
	
	public Tile[][] getTile(){
		return tiles;
	}
	
	public void setRSPRed() {
		//nastav random RSP, farbu, cislo
		String colour = "Red";
		String valuee = "Rock";
		Boolean fill = true;
		Boolean hide = true;
		int counter = 0;
		int RSP = (((columnCount * 2) -2) / 3);
		//System.out.println(RSP);
			
		for(int i = 0; i < 2; i++) {
			for(int y = 0; y < columnCount; y++) {
				if(tiles[i][y].getValue().equals("Flag") || tiles[i][y].getValue().equals("Trap")){
					continue;
				}
				if (counter < RSP) {
					valuee = "Rock";
				}
					if (counter >= RSP && counter < (RSP *2)) {
						valuee = "Paper";
					}
						if (counter >= (2 * RSP)) {
							valuee = "Scissors";
						}
				tiles[i][y] = new Tile(counter +1, colour, valuee, fill, hide);
				counter++;
			}
		}
	}
	
	
	public void setRSPBlue() {
		//nastav random RSP, farbu, cislo
		String colour = "Blue";
		String valuee = "Rock";
		Boolean fill = true;
		Boolean hide = true;
		int counter = 0;
		int RSP = (((columnCount * 2) -2) / 3);
		//System.out.println(RSP);
		
		for(int i = rowCount -2; i < rowCount; i++) {
			for(int y = 0; y < columnCount; y++) {
				if(tiles[i][y].getValue().equals("Flag") || tiles[i][y].getValue().equals("Trap")){
					continue;
				}	
				if (counter < RSP) {
					valuee = "Rock";
				}
					if (counter >= RSP && counter < (RSP *2)) {
						valuee = "Paper";
					}
						if (counter >= (2 * RSP)) {
							valuee = "Scissors";
						}
				tiles[i][y] = new Tile(counter +1, colour, valuee, fill, hide);
				counter++;
			}
		}
	}
	
			
	public void printRed() {		
		String value3 = "skuska";
		String value4 = "skuska";
		//String string0 = "    "+value5+"     ";
		String string1 = "   ---------- ";
		String string2 = "  |          |";
		//String string3 = "|   "+value3+"   |";
		//String string4 = "|          |";
		String string5 = "   ---------- ";
	
	for(int I = 0; I < 2; I++) {
		for(int y = 0; y < 7; y++) {
			if(I == 0)
			System.out.printf("        "+y+"     ");
		}
			System.out.println();	
		for(int y = 0; y < 7; y++) {
			System.out.printf(string1);
		}
			System.out.println();	
		for(int y = 0; y < 7; y++) {
			System.out.printf(string2);
		}
			System.out.println();	
			
		for(int Y = 0; Y < columnCount; Y++) {
			if(Y == 0) 
				System.out.printf(""+I+"");
			if(tiles[I][Y].getValue() == "Rock") {
				value3 = "Rock";		
			System.out.printf( " |   "+value3+"   | ");
			}
			if(tiles[I][Y].getValue() == "Paper") {
				value3 = "Paper";
				System.out.printf( " |   "+value3+"  | ");
			}
			if(tiles[I][Y].getValue() == "Scissors") {
				value3 = "Scissors";
				//int a = 10 - value3.length();
				System.out.printf( " | "+value3+" | ");
			}
			if(tiles[I][Y].getValue() == "Flag") {
				value3 = "Flag";
				System.out.printf( " |   "+value3+"   | ");
			}
			if(tiles[I][Y].getValue() == "Trap") {
				value3 = "Trap";
				System.out.printf( " |   "+value3+"   | ");
			}
		}
			
			System.out.println();		
		for(int y = 0; y < 7; y++) {
			tiles[I][y].getNumber();
			if(tiles[I][y].getNumber() >= 10) {
			System.out.printf("  |    "+(tiles[I][y].getNumber())+"    |");
			}
			else
			System.out.printf("  |     "+(tiles[I][y].getNumber())+"    |");
		}
			System.out.println();		
		for(int y = 0; y < 7; y++) {
			System.out.printf(string5);
		}
			System.out.println();
	}
	}
	
	
	public void printBlue() {		
		String value3 = "skuska";
		String value4 = "skuska";
		//String string0 = "    "+value5+"     ";
		String string1 = "   ---------- ";
		String string2 = "  |          |";
		//String string3 = "|   "+value3+"   |";
		//String string4 = "|          |";
		String string5 = "   ---------- ";
	
	for(int I = rowCount -2; I < rowCount; I++) {
		for(int y = 0; y < 7; y++) {
			if(I == rowCount -2)
			System.out.printf("        "+y+"     ");
		}
			System.out.println();	
		for(int y = 0; y < 7; y++) {
			System.out.printf(string1);
		}
			System.out.println();	
		for(int y = 0; y < 7; y++) {
			System.out.printf(string2);
		}
			System.out.println();	
			
		for(int Y = 0; Y < columnCount; Y++) {
			if(Y == 0) 
				System.out.printf(""+I+"");
			if(tiles[I][Y].getValue() == "Rock") {
				value3 = "Rock";		
			System.out.printf( " |   "+value3+"   | ");
			}
			if(tiles[I][Y].getValue() == "Paper") {
				value3 = "Paper";
				System.out.printf( " |   "+value3+"  | ");
			}
			if(tiles[I][Y].getValue() == "Scissors") {
				value3 = "Scissors";
				//int a = 10 - value3.length();
				System.out.printf( " | "+value3+" | ");
			}
			if(tiles[I][Y].getValue() == "Flag") {
				value3 = "Flag";
				System.out.printf( " |   "+value3+"   | ");
			}
			if(tiles[I][Y].getValue() == "Trap") {
				value3 = "Trap";
				System.out.printf( " |   "+value3+"   | ");
			}
			if(tiles[I][Y].getValue() == "temp") {
				//value3 = "Trap";
				System.out.printf( " |          | ");
			}
		}
			
			System.out.println();		
		for(int y = 0; y < 7; y++) {
			tiles[I][y].getNumber();
			if(tiles[I][y].getNumber() >= 10) {
			System.out.printf("  |    "+(tiles[I][y].getNumber())+"    |");
			}
			else
			System.out.printf("  |     "+(tiles[I][y].getNumber())+"    |");
		}
			System.out.println();		
		for(int y = 0; y < 7; y++) {
			System.out.printf(string5);
		}
			System.out.println();
	}
	}
	
	public void shuffleRed() {
	//prejdi pole, zober prvy objekt uloz ho do temp, vygeneruj nahodne od 0 po 7, a uloz  ho do prveho, z temp daj do vygenerovaneho 
		for(int m = 0; m < 10; m++) {
			for(int i = 0; i < 2; i++) {
				for(int y = 0; y < columnCount; y++) {
					
					Tile temp = tiles[i][y];
					Random random = new Random();	
					int b = random.nextInt(columnCount);
					int a = random.nextInt(2);
					
					tiles[i][y] = tiles[a][b];
					tiles[a][b] = temp;
				
				}
			}		
		}
	}
	
	public void shuffleBlue() {
	//prejdi pole, zober prvy objekt uloz ho do temp, vygeneruj nahodne od 0 po 7, a uloz  ho do prveho, z temp daj do vygenerovaneho 
			for(int m = 0; m < 10; m++) {
				for(int i = rowCount -2; i < rowCount; i++) {
					for(int y = 0; y < columnCount; y++) {
						if(tiles[i][y].getValue().equals("Flag") || tiles[i][y].getValue().equals("Trap")) {
							continue;
						}
						Tile temp = tiles[i][y];
						Random random = new Random();	
						int b = random.nextInt(columnCount);
						int a = random.nextInt(2)+4;
						if(tiles[a][b].getValue().equals("Flag") || tiles[a][b].getValue().equals("Trap")) {
							continue;
						}
						tiles[i][y] = tiles[a][b];
						tiles[a][b] = temp;
					
					}
				}		
			}
	}
		
		
	public void flagAndTrap(String riadok, String stlpec, String hodnota, String colour) {
		int row = Integer.parseInt(riadok);
		int column = Integer.parseInt(stlpec);
		tiles[row][column] = new Tile(1, colour, hodnota, true, true);//.setValue(hodnota);
		//System.out.println(tiles[row][column].getValue());
	}
	
	public void flagAndTrapBot(String value) {
		Random random = new Random();
		int row = random.nextInt(2);
		int column = random.nextInt(columnCount);
		if(tiles[row][column].getValue() == "Flag") {
			flagAndTrapBot("Trap");
		}
		else
		tiles[row][column] = new Tile(1, "Red", value, true, true);//.setValue(hodnota);
		//System.out.println(tiles[row][column].getValue());
		
	}
			
	public void setNumberRed() {
	int count = 1;
		for(int i = 0; i < 2; i++) {
			for(int y = 0; y < columnCount; y++) {
				tiles[i][y].setNumber(count);
				count++;
			}
		}
	}
			
	public void setNumberBlue() {
	int count = 1;
		for(int i = rowCount -2; i < rowCount; i++) {
			for(int y = 0; y < columnCount; y++) {
				tiles[i][y].setNumber(count);
				count++;
			}
		}
	}
	
	public void printField() {
		String value3 = "skuska";
		String value4 = "skuska";
		//String string0 = "    "+value5+"     ";
		String string1 = "   ---------- ";
		String string2 = "  |          |";
		//String string3 = "|   "+value3+"   |";
		//String string4 = "|          |";
		String string5 = "   ---------- ";
	
	for(int I = 0; I < rowCount; I++) {
		for(int y = 0; y < columnCount; y++) {
			if(I == 0)
			System.out.printf("        "+y+"     ");
		}
			System.out.println();	
		for(int y = 0; y < columnCount; y++) {
			System.out.printf(string1);
		}
			System.out.println();	
		for(int y = 0; y < columnCount; y++) {
			if(tiles[I][y].getColour() == "Blue" || tiles[I][y].getColour() == "Red" && tiles[I][y].getHide() == false) {
				if(tiles[I][y].getValue() == "Rock") {
				value3 = "Rock";		
				System.out.printf( "  |   "+value3+"   |");
			}
			if(tiles[I][y].getValue() == "Paper") {
				value3 = "Paper";
				System.out.printf( "  |   "+value3+"  |");
			}
			if(tiles[I][y].getValue() == "Scissors") {
				value3 = "Scissors";
				//int a = 10 - value3.length();
				System.out.printf( "  | "+value3+" |");
			}
			if(tiles[I][y].getValue() == "Flag") {
				value3 = "Flag";
				System.out.printf( "  |   "+value3+"   |");
			}
			if(tiles[I][y].getValue() == "Trap") {
				value3 = "Trap";
				System.out.printf( "  |   "+value3+"   |");
			}
				
			}
			else System.out.printf(string2);
		}
			System.out.println();	
			
		for(int Y = 0; Y < columnCount; Y++) {
			if(Y == 0) 
				System.out.printf(""+I+"");
			if(tiles[I][Y].getColour().equals("Red")) {
			System.out.printf(" |    Red   | ");
			}
			if(tiles[I][Y].getColour().equals("Blue")) {
				System.out.printf(" |   Blue   | ");
			}
			if(tiles[I][Y].getColour().equals("temp")) {
				System.out.printf(" |          | ");
			}
//			if(tiles[I][Y].getValue() == "Rock") {
//				value3 = "Rock";		
//				System.out.printf( " |   "+value3+"   | ");
//			}
//			if(tiles[I][Y].getValue() == "Paper") {
//				value3 = "Paper";
//				System.out.printf( " |   "+value3+"  | ");
//			}
//			if(tiles[I][Y].getValue() == "Scissors") {
//				value3 = "Scissors";
//				//int a = 10 - value3.length();
//				System.out.printf( " | "+value3+" | ");
//			}
//			if(tiles[I][Y].getValue() == "Flag") {
//				value3 = "Flag";
//				System.out.printf( " |   "+value3+"   | ");
//			}
//			if(tiles[I][Y].getValue() == "Trap") {
//				value3 = "Trap";
//				System.out.printf( " |   "+value3+"   | ");
//			}
//			if(tiles[I][Y].getValue() == "temp") {
//				value3 = "";
//				System.out.printf( " |   "+value3+"       | ");
//			}
			
		}
			
			System.out.println();		
		for(int y = 0; y < columnCount; y++) {
			
			
			if(tiles[I][y].getNumber() >= 10) {
			System.out.printf("  |    "+(tiles[I][y].getNumber())+"    |");
			}
			if(tiles[I][y].getNumber() < 10 && tiles[I][y].getNumber() > 0){
			System.out.printf("  |     "+(tiles[I][y].getNumber())+"    |");
			}
			if(tiles[I][y].getValue().equals("temp")) {
				System.out.printf("  |          |");
			}
		}
			System.out.println();		
		for(int y = 0; y < 7; y++) {
			System.out.printf(string5);
		}
			System.out.println();
	}
	}
	
	
			
			public void fight(String pozicia1, String pozicia2, String pozicia3, String pozicia4) {
				int i = Integer.parseInt(pozicia1);
				int y = Integer.parseInt(pozicia2);
				int m = Integer.parseInt(pozicia3);
				int n = Integer.parseInt(pozicia4);
				
				if(tiles[m][n].getValue().equals("Flag")) {
				tiles[m][n] = tiles[i][y];
				winner = tiles[i][y].getColour();
				tiles[m][n].setValue("Winner");
				tiles[i][y] = new Tile(0, "temp", "temp", true, true);
				
				state = GameState.SOLVED;
				//printField();
				System.out.println(getWinner()+" WON!");
				//System.exit(0);
				}
				
				
//				for(int a = 0; a < 6; a++) {
//					for(int b = 0; b < 7; b++) {
//						if(tiles[a][b].getValue().equals("Rock") || tiles[a][b].getValue().equals("Paper") || tiles[a][b].getValue().equals("Scissors")){
//							if(tiles[a][b].getColour().equals("Blue")){
//								blue++;
//								
//							}
//								
//							if(tiles[a][b].getColour().equals("Red")){
//								red++;
//								System.out.println(red);
//								
//							}
//						}
//					}
//					
//				}
//				
//				if(red == 11) {
//					state = GameState.SOLVED;
//					setWinner("Blue");
//				}
//				red = 0;
				
				if(tiles[m][n].getValue().equals("Trap")) {
				System.out.println(tiles[i][y].getColour()+" narazil na pascu");
				tiles[i][y] = new Tile(0, "temp", "temp", true, true);
				tiles[m][n].setHide(false);
				}
				
				if(tiles[m][n].getValue().equals("temp")) {
					tiles[m][n] = tiles[i][y];
					tiles[i][y] = new Tile(0, "temp", "temp", true, true);
				}	
				
//				if(tiles[i][y].getValue().equals("Scissors") && tiles[m][n].getValue().equals("Scissors")) {
//					if(tiles[i][y].getColour() == "Blue") {
//						chooseWeapon(m, n, i, y);
//					}
//					else
//						chooseWeapon(i, y, m, n);
//				}
//					
//				if(tiles[i][y].getValue().equals("Rock") && tiles[m][n].getValue().equals("Rock")) {
//					if(tiles[i][y].getColour() == "Blue") {
//						chooseWeapon(m, n, i, y);
//					}
//					else
//						chooseWeapon(i, y, m, n);	
//				}
//				
//				if(tiles[i][y].getValue().equals("Paper") && tiles[m][n].getValue().equals("Paper")) {
//					if(tiles[i][y].getColour() == "Blue") {
//						chooseWeapon(m, n, i, y);
//					}
//					else
//						chooseWeapon(i, y, m, n);	
//				}
				
				
				
	
				//ked uzivatel prehral
				if(tiles[i][y].getValue().equals("Rock") && tiles[m][n].getValue().equals("Paper")) {
					//tiles[i][y] = tiles[m][n];
					tiles[i][y] = new Tile(0, "temp", "temp", true, true);
					System.out.println(tiles[m][n].getColour()+" Vyhral suboj");
					tiles[m][n].setHide(false);
					
				}
				if(tiles[i][y].getValue().equals("Paper") && tiles[m][n].getValue().equals("Scissors")) {
					//tiles[i][y] = tiles[m][n];
					tiles[i][y] = new Tile(0, "temp", "temp", true, true);
					System.out.println(tiles[m][n].getColour()+" Vyhral suboj");
					tiles[m][n].setHide(false);
				}
				if(tiles[i][y].getValue().equals("Scissors") && tiles[m][n].getValue().equals("Rock")) {
					//tiles[i][y] = tiles[m][n];
					tiles[i][y] = new Tile(0, "temp", "temp", true, true);
					System.out.println(tiles[m][n].getColour()+" Vyhral suboj");
					tiles[m][n].setHide(false);
				}
					
				//ak uzivatel vyhral
					if(tiles[i][y].getValue().equals("Paper") && tiles[m][n].getValue().equals("Rock")){
						tiles[m][n] = tiles[i][y];
						tiles[i][y] = new Tile(0, "temp", "temp", true, true);
						System.out.println(tiles[m][n].getColour()+" Vyhral suboj");
						tiles[m][n].setHide(false);
						
					}
					if(tiles[i][y].getValue().equals("Scissors") && tiles[m][n].getValue().equals("Paper")) {
						tiles[m][n] = tiles[i][y];
						tiles[i][y] = new Tile(0, "temp", "temp", true, true);
						System.out.println(tiles[m][n].getColour()+" Vyhral suboj");
						tiles[m][n].setHide(false);
					}
					if(tiles[i][y].getValue().equals("Rock") && tiles[m][n].getValue().equals("Scissors")) {
						tiles[m][n] = tiles[i][y];	
						tiles[i][y] = new Tile(0, "temp", "temp", true, true);
						System.out.println(tiles[m][n].getColour()+" Vyhral suboj");
						tiles[m][n].setHide(false);
					}
					
//					if(tiles[i][y].getValue().equals("Scissors") && tiles[m][n].getValue().equals("Scissors")) {
//						if(tiles[i][y].getColour() == "Blue") {
//							chooseWeapon(m, n, i, y);
//						}
//						else
//							chooseWeapon(i, y, m, n);
//					}
//						
//					if(tiles[i][y].getValue().equals("Rock") && tiles[m][n].getValue().equals("Rock")) {
//						if(tiles[i][y].getColour() == "Blue") {
//							chooseWeapon(m, n, i, y);
//						}
//						else
//							chooseWeapon(i, y, m, n);	
//					}
//					
//					if(tiles[i][y].getValue().equals("Paper") && tiles[m][n].getValue().equals("Paper")) {
//						if(tiles[i][y].getColour() == "Blue") {
//							chooseWeapon(m, n, i, y);
//						}
//						else
//							chooseWeapon(i, y, m, n);	
//					}
					
					
			}
			
			
			public void chooseWeapon(int i, int y) {
				Scanner sc = new Scanner(System.in);
				int volbaCerveny;
				int volbaModry;
				Random random = new Random();
				
				//do {
				//System.out.println("zadaj kamen(R), papier(P), noznice(S)");
				volbaCerveny = random.nextInt(3)+1;				
				System.out.println("zadaj kamen(1), papier(2), noznice(3)");
				//volbaModry = sc.nextInt();
				
				//} while (volbaCerveny == volbaModry);
					
				switch (volbaCerveny) {
				case 1:
					tiles[i][y].setValue("Rock");	
					break;
					
				case 2:
					tiles[i][y].setValue("Paper");	
					break;
					
				case 3:
					tiles[i][y].setValue("Scissors");	
					break;
				}
				
				System.out.println(tiles[i][y].getValue());
				
					//same = true;
				
//				switch (volbaModry) {
//				case 1:
//					tiles[m][n].setValue("Rock");	
//					break;
//						
//				case 2:
//					tiles[m][n].setValue("Paper");	
//					break;
//						
//				case 3:
//					tiles[m][n].setValue("Scissors");	
//					break;	
//				}
				
				
				
			}
			
			public boolean checkPlayer(String pozicia1, String pozicia2, String player) {
				int i = Integer.parseInt(pozicia1);
				int y = Integer.parseInt(pozicia2);
				if(tiles[i][y].getColour().equals(player))
					return true;
				//if(tiles[i][y].getColour() != ("Red"))
				else
					//System.out.println("zadal si zle pole");
					return false;
				//else
				//return false;
			}
			
			public boolean checkBot(int i, int y, String player) {
				if(tiles[i][y].getColour().equals(player) && tiles[i][y].getValue() != "Flag" && tiles[i][y].getValue() != "Trap")
					return true;
				//if(tiles[i][y].getColour() != ("Red"))
				else
					//System.out.println("pc zadal si zle pole");
					return false;
				//else
				//return false;
			}
			
			
			public void botInput2(String player) {
				Random random = new Random();
				int a;
				int b;
				int c;
				int d;
				
				
				//System.out.println("Na tahu je "+player);
				do {
				//System.out.println("vyber si s ktorym vojakom sa pohnes(zadaj jeho poziciu(riadok)): ");
			
				a = random.nextInt(6);
				//System.out.println("vyber si s ktorym vojakom sa pohnes(zadaj jeho poziciu(stlpec)): ");
				//String pozicia1 = Integer.valueOf(a).toString();
				b = random.nextInt(7);
				//String pozicia2 = Integer.valueOf(b).toString();
				} while (checkBot(a, b, player) != true); //viem urobit field.tiles[a][a].getColour(); ?
				
				//System.out.println("vyber si kam sa pohnes(zadaj poziciu(riadok)");
				String pozicia1 = Integer.valueOf(a).toString();
				String pozicia2 = Integer.valueOf(b).toString();
				c = random.nextInt(6);
				String pozicia3 = Integer.valueOf(c).toString();
				//System.out.println("vyber si kam sa pohnes(zadaj poziciu(stlpec)");
			
				d = random.nextInt(7);
				String pozicia4 = Integer.valueOf(d).toString();
				if(c == a+1 && d == b || c == a-1 && d == b || c == a && d == b+1 || c == a && d == b-1) {
					if(c >= 0 && c < 6 && d >= 0 && d < 7) {
						if(getTile()[c][d].getColour() != "Red") {
							//if(getTile()[c][d].getValue() != "Flag") {
								//if(getTile()[c][d].getValue() != "Trap") {
									fight(pozicia1, pozicia2, pozicia3, pozicia4);
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
				
//				do {
//					c = random.nextInt(6);
//					d = random.nextInt(7);
//				} while (c != a+1 && d != b || c != a-1 && d != b || c != a && d != b+1 || c != a && d != b-1 && c >= 0 && c < 6 && d >= 0 && d < 7 && field.getTile()[c][d].getColour() == "Red" || c == a && d == b);
//				String pozicia3 = Integer.valueOf(c).toString();
//				String pozicia4 = Integer.valueOf(d).toString();
//				field.fight(pozicia1, pozicia2, pozicia3, pozicia4);
//				field.printField();
			}
			
			
//			public int getScore() {
//				return (int)(RPSController.getClick() * (System.currentTimeMillis()));
//			}
			
			
			
			
			
			
			
			
}
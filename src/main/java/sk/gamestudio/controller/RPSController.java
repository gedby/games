package sk.gamestudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.gamestudio.entity.Score;
import sk.gamestudio.game.rps.consoleui.ConsoleUI;
import sk.gamestudio.game.rps.core.*;
import sk.gamestudio.service.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class RPSController {
	@Autowired
	private PlayerController playerController;

	private Field field;
	private int click = 0;
	private int score = 10000;
	private ConsoleUI ui;
	private int e;
	private int f;
	private int g;
	private int h;
	private int row;
	private int column;
	private boolean firstClick = false;
	
	@Autowired
	private ScoreService scoreService;

	@RequestMapping("/rps")
	public String rps() {
		field = new Field(6, 7);
		field.generate();
		ui = new ConsoleUI(field);
		ui.setBot();
		click = 0;
		return "rps";
	}
	
	public int getClick(){
		return click;
	}
	
	public List<Score> getTopScores() {
		return scoreService.getTopScores("rps");
	}

	
	@RequestMapping("/rps/action1")
	public String action1(String a, String b) {
		int c = Integer.parseInt(a);
		int d = Integer.parseInt(b);
		if(click == 1 && field.getTile()[c][d].getColour() == "Blue") {
			field.getTile()[c][d].setValue("Trap");
			field.setRSPBlue();
			field.shuffleBlue();
			field.setNumberBlue();
			click = 2;
			
		}
		
		if(click == 0 && field.getTile()[c][d].getColour() == "Blue"){
			field.getTile()[c][d].setValue("Flag");
			click = 1;
		}
		return "rps";
	}
	
	
	@RequestMapping("/rps/action2")
	public String action2(String a, String b) {
		e = Integer.parseInt(a);
		f = Integer.parseInt(b);
		firstClick = true;
		return "rps";
	}
	
	@RequestMapping("/rps/action3")
	public String action3(String a, String b) {
		g = Integer.parseInt(a);
		h = Integer.parseInt(b);
		
		if(field.getTile()[e][f].getValue().equals(field.getTile()[g][h].getValue())) {
			field.setSame(true);
			}
		else {
			field.setSame(false);
			ui.userInput2("Blue", e, f, g, h);
			if(ui.getMove() == true) {
				click++;
				score--;
				firstClick = false;
		}
		if (field.getState() == GameState.SOLVED && playerController.getLoggedPlayer() != null) {
				scoreService.addScore(new Score(playerController.getLoggedPlayer().getName(), "rps", score));
		}
		}
		firstClick = false;
		return "rps";
		//jeho tah
		//return "rps";
	}
	
	@RequestMapping("/rps/action4")
	public String action4(String value) {
		field.getTile()[e][f].setValue(value);
		field.chooseWeapon(g, h);
		if(field.getTile()[e][f].getValue().equals(field.getTile()[g][h].getValue())) {
			field.setSame(true);
		}
		else {
			field.setSame(false);
			ui.userInput2("Blue", e, f, g, h);
			System.out.println(field.getTile()[g][h].getColour());
			this.row = g;
			this.column = h;
			
		}
		return "rps";
	}
	
//	@RequestMapping("/rps/action5")
//	public String action5(String value) {
//		ui.botInput2(value);
//		click++;
//		return "rps";
//	}
		
	
	public String getHtmlField() {
		StringBuilder sb = new StringBuilder();
		
//		sb.append("<p><a href='" + String.format("/rps/action5?value=Red") + "'>");
//		sb.append("<H1 style=\"font-family:Broadway;font-size:30px;color:#71b300;text-align:right\" id=\"nadpis\">Bot turn</H1>");
		if(field.getState() == GameState.SOLVED)
			sb.append("<H1 style=\"font-family:Broadway;font-size:30px;color:#71b300;text-align:center\" id=\"nadpis\">'"+ String.format(field.getWinner()) +" WON!</H1>");
		if(click >= 2) {
			if((click % 2) == 0) {
				sb.append("<img id='red' src='/images/rps/vlajkaRed.jpg'>");
			}
			else
				sb.append("<img id='red'src='/images/rps/vlajkaBlue.jpg'>");
			}
		
		if(field.getSame() == true) { 
			sb.append("<H1 style=\"font-family:Broadway;font-size:30px;color:#71b300;text-align:right\" id=\"nadpis\">Choose your weapon:</H1>");
			sb.append("<p><a href='" + String.format("/rps/action4?value=Rock") + "'>");
			sb.append("<br><img id='vyber' src='/images/rps/Rock.jpg'>");
			sb.append("<a href='" + String.format("/rps/action4?value=Paper") + "'>");
			sb.append("<img id='vyber' src='/images/rps/Paper.jpg'>");
			sb.append("<a href='" + String.format("/rps/action4?value=Scissors") + "'>");
			sb.append("<img id='vyber' src='/images/rps/Scissors.jpg'></p>");
			
		}
		if(click < 2) 
		sb.append("<H1 style=\"font-family:Broadway;font-size:30px;color:#71b300;margin-left:+200px;\" id=\"nadpis\">Choose flag and trap:</H1>");
		sb.append("<table id='rpsTable' class='field'>\n");
		for (this.row = 0; row < 6; row++) {
			sb.append("<tr>\n");
			for (this.column = 0; column < 7; column++) {
				sb.append("<td>");
				if(click < 2) {
					if(field.getTile()[row][column].getColour() == "Red") 
						sb.append("<img src='/images/rps/Red.jpg'>");
					if(field.getTile()[row][column].getColour() == "Blue") {
						sb.append("<a href='" + String.format("/rps/action1?a=%s&b=%s", row, column) + "'>");
						sb.append("<img src='/images/rps/Blue.jpg'>");
					}
					if(field.getTile()[row][column].getColour() == "temp")
						sb.append("<p></p>");
				}
				
				if(click >= 2) {
					if((click % 2) == 0) {
						if(field.getState() == GameState.PLAYING) {
//						try
//					{
//						Thread.sleep(2000);
//					}
//					catch(InterruptedException ex)
//					{
//						Thread.currentThread().interrupt();
//					}
						ui.botInput2("Red");
						
						click++;
						//getHtmlField();
						
						}
					}
						
					
					if(field.getTile()[row][column].getColour().equals("Blue")) {
						if (field.getState() == GameState.PLAYING) 
						if(field.getTile()[row][column].getValue() != "Flag" && field.getTile()[row][column].getValue() != "Trap")
						if(firstClick == false)
							
						sb.append("<a href='" + String.format("/rps/action2?a=%s&b=%s", row, column) + "'>");
						if (field.getTile()[row][column].getValue().equals("Rock"))
							sb.append("<img src='/images/rps/BlueRock.jpg'>");
				
						if (field.getTile()[row][column].getValue().equals("Paper"))
							sb.append("<img src='/images/rps/BluePaper.jpg'>");
					
						if (field.getTile()[row][column].getValue().equals("Scissors"))
							sb.append("<img src='/images/rps/BlueScissors.jpg'>");
						
						if (field.getTile()[row][column].getValue().equals("Flag"))
							sb.append("<img src='/images/rps/BlueFlag.jpg'>");
						
						if (field.getTile()[row][column].getValue().equals("Trap"))
							sb.append("<img src='/images/rps/BlueTrap.jpg'>");
						
							if (field.getTile()[row][column].getValue().equals("Winner"))
								sb.append("<img src='/images/rps/BlueWon.jpg'>");
					}
					
					if(field.getTile()[row][column].getColour() == "Red") {
						if (field.getState() == GameState.PLAYING && firstClick == true) 
							if(row == e+1 && column == f || row == e-1 && column == f || row == e && column == f+1 || row == e && column == f-1)
								
						sb.append("<a href='" + String.format("/rps/action3?a=%s&b=%s", row, column) + "'>");
						if(field.getTile()[row][column].getHide() == false) {
							if (field.getTile()[row][column].getValue() == "Rock")
								sb.append("<img src='/images/rps/RedRock.jpg'>");
					
							if (field.getTile()[row][column].getValue() == "Paper")
									sb.append("<img src='/images/rps/RedPaper.jpg'>");
						
							if (field.getTile()[row][column].getValue() == "Scissors")
									sb.append("<img src='/images/rps/RedScissors.jpg'>");
							
							if (field.getTile()[row][column].getValue() == "Flag")
								sb.append("<img src='/images/rps/RedFlag.jpg'>");
							
							if (field.getTile()[row][column].getValue() == "Trap")
								sb.append("<img src='/images/rps/RedTrap.jpg'>");
							
							
							if (field.getTile()[row][column].getValue().equals("Winner"))
								sb.append("<img src='/images/rps/RedWon.jpg'>");
						}
						else 
							sb.append("<img src='/images/rps/Red.jpg'>");
					}
					
	
					if(field.getTile()[row][column].getColour() == "temp" && firstClick == true) {
						if(row == e+1 && column == f || row == e-1 && column == f || row == e && column == f+1 || row == e && column == f-1)
							sb.append("<a href='" + String.format("/rps/action3?a=%s&b=%s", row, column) + "'>");
							sb.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>");
					}
							
					
				}
				sb.append("</a>");
				sb.append("</td>\n");
			}
			sb.append("</tr>\n");
		}
		sb.append("</table>\n");
	
//	if(field.getSame() == true) { 
//		sb.append("<a href='" + String.format("/rps/action4?value=Rock") + "'>");
//		sb.append("<img src='/images/rps/Rock.jpg'>");
//		sb.append("<a href='" + String.format("/rps/action4?value=Paper") + "'>");
//		sb.append("<img src='/images/rps/Paper.jpg'>");
//		sb.append("<a href='" + String.format("/rps/action4?value=Scissors") + "'>");
//		sb.append("<img src='/images/rps/Scissors.jpg'>");
//		
//	}
		return sb.toString();
	}
}

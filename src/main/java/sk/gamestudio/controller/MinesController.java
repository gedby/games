package sk.gamestudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.gamestudio.entity.Score;
import sk.gamestudio.game.mines.core.Clue;
import sk.gamestudio.game.mines.core.Field;
import sk.gamestudio.game.mines.core.GameState;
import sk.gamestudio.game.mines.core.Tile;
import sk.gamestudio.service.ScoreService;
import sk.gamestudio.service.ScoreServiceJDBC;
import sk.gamestudio.service.ScoreServiceJPA;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesController {
	private static final String GAME_NAME = "mines";

	@Autowired
	private PlayerController playerController;

	@Autowired
	private ScoreService scoreService;

	private Field field;

	private boolean marking;

	private int size = 9;

	@RequestMapping("/mines")
	public String mines() {
		System.out.println(">>>>>>>>>> " + scoreService);
		field = new Field(size, size, 1);
		return GAME_NAME;
	}

	@RequestMapping("/mines/inc")
	public String vacsie() {
		size++;
		mines();
		return GAME_NAME;
	}

	@RequestMapping("/mines/dec")
	public String mensie() {
		size--;
		mines();
		return GAME_NAME;
	}

	@RequestMapping("/mines/action")
	public String action(int row, int column) {
		if (field.getState() == GameState.PLAYING) {
			if (marking)
				field.markTile(row, column);
			else {
				field.openTile(row, column);
				if (field.getState() == GameState.SOLVED && playerController.getLoggedPlayer() != null) {
					scoreService.addScore(
							new Score(playerController.getLoggedPlayer().getName(), GAME_NAME, field.getScore()));
				}
			}
		}
		return GAME_NAME;
	}

	@RequestMapping("/mines/mark")
	public String mark() {
		marking = !marking;
		return GAME_NAME;
	}

	public boolean isMarking() {
		return marking;
	}

	public String getState() {
		return field.getState().toString();
	}

	public List<Score> getTopScores() {
		return scoreService.getTopScores(GAME_NAME);
	}
	
	public String getHtmlField() {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>\n");

		for (int row = 0; row < field.getRowCount(); row++) {
			sb.append("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				sb.append("<td>");
				Tile tile = field.getTile(row, column);
				sb.append("<a href='" + String.format("/mines/action?row=%s&column=%s", row, column) + "'>");
				sb.append("<img src='/images/mines/" + getImageName(tile) + ".png'>");
				sb.append("</a>");
				sb.append("</td>\n");
			}
			sb.append("</tr>\n");
		}

		sb.append("</table>\n");
		return sb.toString();
	}

	private String getImageName(Tile tile) {
		switch (tile.getState()) {
		case CLOSED:
			return "closed";
		case MARKED:
			return "marked";
		case OPENED:
			if (tile instanceof Clue)
				return "open" + ((Clue) tile).getValue();
			else
				return "mine";
		}
		throw new IllegalArgumentException("Not supported tile " + tile);
	}
}

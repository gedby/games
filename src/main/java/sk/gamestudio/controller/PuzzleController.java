package sk.gamestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.gamestudio.game.npuzzle.core.Field;
import sk.gamestudio.game.npuzzle.core.Tile;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
	@Autowired
	private PlayerController playerController;

	private Field field;

	@RequestMapping("/puzzle")
	public String puzzle() {
		field = new Field(4, 4);
		return "puzzle";
	}

	@RequestMapping("/puzzle/action")
	public String action(int tile) {
		field.move(tile);
		return "puzzle";
	}

	public boolean getState() {
		return field.isSolved();
	}

	public String getHtmlField() {
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='field'>\n");

		for (int row = 0; row < field.getRowCount(); row++) {
			sb.append("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				sb.append("<td>");
				Tile tile = field.getTile(row, column);
				if (tile != null) {
					sb.append("<a href='" + String.format("/puzzle/action?tile=%s", tile.getValue()) + "'>");
					//sb.append(tile.getValue());
					sb.append("<img src='/images/puzzle/img" + tile.getValue() + ".jpg'>");
					sb.append("</a>");
				}
				sb.append("</td>\n");
			}
			sb.append("</tr>\n");
		}

		sb.append("</table>\n");
		return sb.toString();
	}
}

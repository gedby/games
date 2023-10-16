package sk.gamestudio.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sk.gamestudio.entity.Score;

public class ScoreServiceJDBC implements ScoreService {
	private static final String URL = "jdbc:derby://localhost/gamestudio;create=true";
	private static final String CREATE = "CREATE TABLE score (player VARCHAR(64) NOT NULL, game VARCHAR(64) NOT NULL, value INT NOT NULL)";
	private static final String SELECT = "SELECT player, game, value FROM score WHERE game = ? ORDER BY value DESC FETCH FIRST 10 ROWS ONLY";
	private static final String INSERT = "INSERT INTO score (player, game, value) VALUES (?, ?, ?)";

	@Override
	public void addScore(Score score) {
		//create();
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setString(1, score.getPlayer());
			statement.setString(2, score.getGame());
			statement.setInt(3, score.getValue());
			statement.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Error registering player", e);
		}
	}

	@Override
	public List<Score> getTopScores(String game) {
		//create();
		
		List<Score> scores = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection.prepareStatement(SELECT)) {
			statement.setString(1, game);
			try (ResultSet rs = statement.executeQuery()) {
				while(rs.next()) {
					Score score = new Score(rs.getString(1), rs.getString(2), rs.getInt(3));
					scores.add(score);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error logging player", e);
		}
		return scores;
	}

	private void create() {
		try (Connection connection = DriverManager.getConnection(URL);
				Statement statement = connection.createStatement()) {
			statement.executeUpdate(CREATE);
		} catch (Exception e) {
			System.out.println("Error creating table SCORE, may already exists");
		}
	}
}

package sk.gamestudio.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import sk.gamestudio.entity.Player;

public class PlayerServiceJDBC implements PlayerService {
	private static final String URL = "jdbc:derby://localhost/gamestudio;create=true";
	private static final String CREATE = "CREATE TABLE player (name VARCHAR(64) PRIMARY KEY, passwd VARCHAR(64) NOT NULL)";
	private static final String SELECT = "SELECT name, passwd FROM player WHERE name = ? AND passwd = ?";
	private static final String INSERT = "INSERT INTO player (name, passwd) VALUES (?, ?)";

	@Override
	public Player login(String name, String password) {
		//create();
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection.prepareStatement(SELECT)) {
			statement.setString(1, name);
			statement.setString(2, password);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next())
					return new Player(name, password);
				else
					return null;
			}
		} catch (Exception e) {
			throw new RuntimeException("Error logging player", e);
		}
	}

	@Override
	public void register(Player player) {
		//create();
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setString(1, player.getName());
			statement.setString(2, player.getPasswd());
			statement.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Error registering player", e);
		}
	}

	private void create() {
		try (Connection connection = DriverManager.getConnection(URL);
				Statement statement = connection.createStatement()) {
			statement.executeUpdate(CREATE);
		} catch (Exception e) {
			System.out.println("Error creating table PLAYER, may already exists");
		}
	}
}

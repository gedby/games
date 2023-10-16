package sk.gamestudio.game.npuzzle.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Field implements Serializable {
	private static final String PUZZLE_SAVEFILE = "puzzle.bin";

	private final int rowCount;

	private final int columnCount;

	private final Tile[][] tiles;

	private int emptyRow;

	private int emptyColumn;
	
	private boolean shuffling;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];

		generate();
		shuffle();
	}

	private void generate() {
		int index = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				tiles[row][column] = new Tile(index);
				index++;
			}
		}
		emptyRow = rowCount - 1;
		emptyColumn = columnCount - 1;
		tiles[emptyRow][emptyColumn] = null;
	}

	private void shuffle() {
		shuffling = true;
		Random random = new Random();
		for (int i = 0; i < 1;) {
			if (move(random.nextInt(rowCount * columnCount - 1) + 1))
				i++;
		}
		shuffling = false;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public boolean move(int tileNumber) {
		if (shuffling || !isSolved()) {
			if (!exchangeWithEmpty(tileNumber, emptyRow - 1, emptyColumn))
				if (!exchangeWithEmpty(tileNumber, emptyRow + 1, emptyColumn))
					if (!exchangeWithEmpty(tileNumber, emptyRow, emptyColumn - 1))
						return exchangeWithEmpty(tileNumber, emptyRow, emptyColumn + 1);
			return true;
		}
		return false;
	}

	private boolean exchangeWithEmpty(int tileNumber, int row, int column) {
		if (row >= 0 && row < rowCount && column >= 0 && column < columnCount
				&& tiles[row][column].getValue() == tileNumber) {
			tiles[emptyRow][emptyColumn] = tiles[row][column];
			tiles[row][column] = null;
			emptyRow = row;
			emptyColumn = column;
			return true;
		}
		return false;
	}

	public boolean isSolved() {
		int index = 1;
		if (tiles[rowCount - 1][columnCount - 1] != null)
			return false;

		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				Tile tile = tiles[row][column];
				if (tile != null && tile.getValue() != index)
					return false;
				index++;
			}
		}
		return true;
	}

	public void save() throws IOException {
		try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(PUZZLE_SAVEFILE))) {
			ous.writeObject(this);
		}
	}

	public static Field load() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUZZLE_SAVEFILE))) {
			return (Field) ois.readObject();
		} catch (Exception e) {
			return null;
		}
	}
}

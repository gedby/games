package sk.gamestudio.game.rps.core;


public class Tile {
	public int number;
	public String colour;
	public String value;
	public Boolean fill;
	public Boolean hide;
	
	public Tile(int number, String colour, String value, Boolean fill, Boolean hide) {
		this.number = number;
		this.colour = colour;
		this.value = value;
		this.fill = fill;
		this.hide = hide;
	}

	public int getNumber() {
		return number;
	}

	public String getColour() {
		return colour;
	}

	public String getValue() {
		return value;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getFill() {
		return fill;
	}

	public void setFill(Boolean fill) {
		this.fill = fill;
	}

	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	
	
	
}

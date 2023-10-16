package sk.gamestudio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hrac")
public class Player {
	@Id
	private String name;

	@Column(name = "heslo", nullable = false)
	private String passwd;

	public Player() {
	}

	public Player(String name, String passwd) {
		this.name = name;
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", passwd=" + passwd + "]";
	}
}

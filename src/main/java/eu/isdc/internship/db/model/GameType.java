package eu.isdc.internship.db.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "GAME_TYPES")
public class GameType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GAME_TYPE_ID", nullable = false)
	private Long game_type_id;
	
	@Column(name = "M", nullable = false)
	private int m;
	
	@Column(name = "N", nullable = false)
	private int n;
	
	@OneToMany(mappedBy = "gameType",fetch=FetchType.LAZY)
	// 1-n pentru Game
	@Cascade(value = CascadeType.ALL)
	private List<Game> games;

	@OneToMany(mappedBy = "gameType",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu AvailableBT
	@Cascade(value = CascadeType.ALL)
	private List<AvailableBattleship> availableBTs;
	
	public GameType() { }
	public GameType(int m, int n) {
		this.m = m;
		this.n = n;
	}
	
	public Long getGame_type_id() {
		return game_type_id;
	}

	public void setGame_type_id(Long game_type_id) {
		this.game_type_id = game_type_id;
	}
	
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	/**
	 * @return the availableBTs
	 */
	public List<AvailableBattleship> getAvailableBTs() {
		return availableBTs;
	}

	/**
	 * @param availableBTs the availableBTs to set
	 */
	public void setAvailableBTs(List<AvailableBattleship> availableBTs) {
		this.availableBTs = availableBTs;
	}
}

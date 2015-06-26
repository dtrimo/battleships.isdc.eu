package eu.isdc.internship.db.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "GAMES")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GAME_ID", nullable = false)
	private Long game_id;

	@Temporal(TemporalType.DATE)
	@Column(name = "PLAYING_DATE", nullable = false, length = 10)
	private Date date;

	@Column(name = "M", nullable = false)
	private int m;
	
	@Column(name = "N", nullable = false)
	private int n;

	@OneToMany(mappedBy = "game",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu StartConfig
	@Cascade(value = CascadeType.ALL)
	private List<StartConfig> startConfigs;
	
	@OneToMany(mappedBy = "game",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu AvailableBT
	@Cascade(value = CascadeType.ALL)
	private List<AvailableBattleship> availableBTs;
	
	@OneToMany(mappedBy = "game",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu Moves
	@Cascade(value = CascadeType.ALL)
	private List<Move> moves;


	public Game(){}
	public Game(Date d, int m, int n){
		this.date=d;
		this.m=m;
		this.n=n;
		
	}
	/**
	 * @return the game_id
	 */
	public Long getGame_id() {
		return game_id;
	}

	/**
	 * @param game_id the game_id to set
	 */
	public void setGame_id(Long game_id) {
		this.game_id = game_id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the startConfigs
	 */
	public List<StartConfig> getStartConfigs() {
		return startConfigs;
	}

	/**
	 * @param startConfigs the startConfigs to set
	 */
	public void setStartConfigs(List<StartConfig> startConfigs) {
		this.startConfigs = startConfigs;
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

	/**
	 * @return the moves
	 */
	public List<Move> getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	
	

}

package eu.isdc.internship.db.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne //n-1 cu GameType
	@JoinColumn(name = "game_type_id")
	private GameType gameType;

	@OneToMany(mappedBy = "game",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu StartConfig
	@Cascade(value = CascadeType.ALL)
	private List<StartConfig> startConfigs;
	
	@OneToMany(mappedBy = "game",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu Moves
	@Cascade(value = CascadeType.ALL)
	private List<Move> moves;

	public Game(){}
	public Game(Date d){
		this.date=d;		
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
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}	
}

package eu.isdc.internship.db.model;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "START_CONFIG")
public class StartConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STARTCONFIG_ID", nullable = false)
	private Long startConfig_id;

	@OneToMany(mappedBy = "startConfig",fetch=FetchType.LAZY) // 1-n cu BTPositions
	@Cascade(value = CascadeType.ALL)
	private List<BattleshipPosition> BT_Positions;

	@ManyToOne //n-1 cu Games
	@JoinColumn(name = "game_id")
	private Game game;

	@OneToMany(mappedBy = "startConfig",fetch=FetchType.LAZY)	// 1-n cu Moves
	@Cascade(value = CascadeType.ALL)
	private List<Move> move;	
	
	@ManyToOne //n-1 cu Users
	@JoinColumn(name = "user_id")
	private User user;
	
	public StartConfig(){}

	public List<BattleshipPosition> getBT_Positions() {
		return BT_Positions;
	}

	public void setBT_Positions(List<BattleshipPosition> bT_Positions) {
		BT_Positions = bT_Positions;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Move> getMove() {
		return move;
	}

	public void setMove(List<Move> move) {
		this.move = move;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the startConfig_id
	 */
	public Long getStartConfig_id() {
		return startConfig_id;
	}

	/**
	 * @param startConfig_id
	 *            the startConfig_id to set
	 */
	public void setStartConfig_id(Long startConfig_id) {
		this.startConfig_id = startConfig_id;
	}

}

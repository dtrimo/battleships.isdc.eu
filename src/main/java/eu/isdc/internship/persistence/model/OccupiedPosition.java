package eu.isdc.internship.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class OccupiedPosition. Represents a position occupied by a cell of a
 * battleship placed by a user on a board.
 */
@Entity
@Table(name = "OCCUPIED_POSITIONS")
public class OccupiedPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "occupied_position_id", nullable = false)
	private Long occupiedPositionId;

	@Column(name = "x", nullable = false)
	private int x;

	@Column(name = "y", nullable = false)
	private int y;

	@ManyToOne
	@JoinColumn(name = "battleship_position_id")
	private BattleshipPosition battleshipPosition;

	public Long getOccupiedPositionId() {
		return occupiedPositionId;
	}

	public void setOccupiedPositionId(Long occupiedPositionId) {
		this.occupiedPositionId = occupiedPositionId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BattleshipPosition getBattleshipPosition() {
		return battleshipPosition;
	}

	public void setBattleshipPosition(BattleshipPosition battleshipPosition) {
		this.battleshipPosition = battleshipPosition;
	}

}

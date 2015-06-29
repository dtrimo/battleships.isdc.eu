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
@Table(name = "AVAILABLE_BATTLESHIPS")
public class AvailableBattleship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AV_BT_ID", nullable = false)
	private Long Av_BT_id;

	@Column(name = "BATTLESHIP_COUNT")
	private int count;

	@ManyToOne //n-1 cu BT_Model 
	@JoinColumn(name = "model_id")
	private BattleshipModel model;

	@OneToMany(mappedBy = "av_BT",fetch=FetchType.LAZY) //1-n cu BTPositions
	@Cascade(value = CascadeType.ALL)
	private List<BattleshipPosition> BT_Positions;
	
	@ManyToOne //n-1 cu Games
	@JoinColumn(name = "game_id")
	private Game game;
	
	public AvailableBattleship() {}
	
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

	public BattleshipModel getModel() {
		return model;
	}
	public void setModel(BattleshipModel model) {
		this.model = model;
	}
	/**
	 * @return the av_BT_id
	 */
	public Long getAv_BT_id() {
		return Av_BT_id;
	}

	/**
	 * @param av_BT_id
	 *            the av_BT_id to set
	 */
	public void setAv_BT_id(Long av_BT_id) {
		Av_BT_id = av_BT_id;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

}

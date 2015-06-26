package eu.isdc.internship.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_STATS")
public class Statistics {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATS_ID", nullable = false)
	private Long stats_id;
	
	@OneToOne
	// 1-1 cu Users
	@JoinColumn(name = "USER_ID", referencedColumnName = "user_id")
	private User user;
	
	@Column(name = "NROFWINS")
	private int nrOfWins;

	@Column(name = "NROFPLAYEDGAMES")
	private int nrOfPlayedGames;

	@Column(name = "NROFROUNDSTOWIN")
	private int nrOfRoundsToWin;

	@Column(name = "NROFROUNDSTOLOSE")
	private int nrOfRundsToLose;
	
	public Statistics() {}
	public Statistics(int wins, int played, int roundsWin, int roundsLose) {
		this.nrOfWins = wins;
		this.nrOfPlayedGames = played;
		this.nrOfRoundsToWin = roundsWin;
		this.nrOfRundsToLose = roundsLose;
	}

	public User getUser() {
		return user;
	}
	
	public Long getStats_id() {
		return stats_id;
	}

	public void setStats_id(Long stats_id) {
		this.stats_id = stats_id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNrOfWins() {
		return nrOfWins;
	}

	public void setNrOfWins(int nrOfWins) {
		this.nrOfWins = nrOfWins;
	}

	public int getNrOfPlayedGames() {
		return nrOfPlayedGames;
	}

	public void setNrOfPlayedGames(int nrOfPlayedGames) {
		this.nrOfPlayedGames = nrOfPlayedGames;
	}

	public int getNrOfRoundsToWin() {
		return nrOfRoundsToWin;
	}

	public void setNrOfRoundsToWin(int nrOfRoundsToWin) {
		this.nrOfRoundsToWin = nrOfRoundsToWin;
	}

	public int getNrOfRundsToLose() {
		return nrOfRundsToLose;
	}

	public void setNrOfRundsToLose(int nrOfRundsToLose) {
		this.nrOfRundsToLose = nrOfRundsToLose;
	}
}

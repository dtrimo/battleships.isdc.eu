package eu.isdc.internship.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "USER_STATISTICS")
public class Statistics {

	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id", unique = true, nullable = false)
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	private Long user_id;

	@Column(name = "NROFWINS")
	private int nrOfWins;

	@Column(name = "NROFPLAYEDGAMES")
	private int nrOfPlayedGames;

	@Column(name = "NROFROUNDSTOWIN")
	private int nrOfRoundsToWin;

	@Column(name = "NROFROUNDSTOLOSE")
	private int nrOfRundsToLose;

	@OneToOne
	// 1-1 cu Users
	@PrimaryKeyJoinColumn
	private User user;

	public Statistics(){}
	public Statistics(int nrW,int nrP,int nrRW, int nrRL){
		this.nrOfWins=nrW;
		this.nrOfPlayedGames=nrP;
		this.nrOfRoundsToWin=nrRW;
		this.nrOfRundsToLose=nrRL;
	}
	


	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public User getUser() {
		return user;
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

	// @OneToOne
	// @PrimaryKeyJoinColumn
	public User getUserId() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
	}

}

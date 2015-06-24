package eu.isdc.internship.db.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.isdc.internship.db.model.User;

public class StatisticsDTO {
	private Long user_id;

	private int nrOfWins;

	private int nrOfPlayedGames;

	private int nrOfRoundsToWin;

	private int nrOfRundsToLose;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

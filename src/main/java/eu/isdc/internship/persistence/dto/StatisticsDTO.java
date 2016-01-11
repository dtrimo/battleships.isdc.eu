package eu.isdc.internship.db.dto;

public class StatisticsDTO {
	private Long stats_id;
	
	private UserDTO user;

	private int nrOfWins;

	private int nrOfPlayedGames;

	private int nrOfRoundsToWin;

	private int nrOfRundsToLose;

	public Long getStats_id() {
		return stats_id;
	}

	public void setStats_id(Long stats_id) {
		this.stats_id = stats_id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
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

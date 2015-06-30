package eu.isdc.internship.db.dto;

import java.util.Date;

public class UserDTO {

	private Long user_id;
	private String name;
	private String password;
	private Date birthDate;
	private StatisticsDTO statistics;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public StatisticsDTO getStatistics() {
		return statistics;
	}

	public void setStatistics(StatisticsDTO statistics) {
		this.statistics = statistics;
	}
}

package eu.isdc.internship.persistence.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class UserDTO.
 */
public class UserDTO {

	private Long userId;
	private String name;
	@JsonIgnore
	private String password;
	private Date birthDate;
	@JsonIgnore
	private StatisticsDTO statistics;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate
	 *            the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the statistics.
	 *
	 * @return the statistics
	 */
	public StatisticsDTO getStatistics() {
		return statistics;
	}

	/**
	 * Sets the statistics.
	 *
	 * @param statistics
	 *            the new statistics
	 */
	public void setStatistics(StatisticsDTO statistics) {
		this.statistics = statistics;
	}
}

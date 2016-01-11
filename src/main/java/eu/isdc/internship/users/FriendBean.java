package eu.isdc.internship.users;

import java.util.Date;

/**
 * The Class FriendBean.
 */
public class FriendBean {

	private Long userId;
	private String name;
	private Date birthDate;
	private Boolean online;
	
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
	 * @param userId the new user id
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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * Gets the online.
	 *
	 * @return the online
	 */
	public Boolean getOnline() {
		return online;
	}
	
	/**
	 * Sets the online.
	 *
	 * @param online the new online
	 */
	public void setOnline(Boolean online) {
		this.online = online;
	}
	
}
